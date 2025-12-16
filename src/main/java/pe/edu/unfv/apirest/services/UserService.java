package pe.edu.unfv.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.unfv.apirest.dto.role.RoleDTO;
import pe.edu.unfv.apirest.dto.user.CreateUserRequest;
import pe.edu.unfv.apirest.dto.user.CreateUserResponse;
import pe.edu.unfv.apirest.dto.user.LoginRequest;
import pe.edu.unfv.apirest.dto.user.LoginResponse;
import pe.edu.unfv.apirest.models.Role;
import pe.edu.unfv.apirest.models.User;
import pe.edu.unfv.apirest.models.UserHasRoles;
import pe.edu.unfv.apirest.repositories.RoleRepository;
import pe.edu.unfv.apirest.repositories.UserHasRolesRepository;
import pe.edu.unfv.apirest.repositories.UserRepository;
import pe.edu.unfv.apirest.utils.JwtUtil;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserHasRolesRepository userHasRolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public CreateUserResponse create(CreateUserRequest request){

        if(userRepository.existsByEmail(request.email)){
            throw new RuntimeException("El email ya existe");
        }

        User user = new User();
        user.setName(request.name);
        user.setLastname(request.lastname);
        user.setPhone(request.phone);
        user.setEmail(request.email);
        String encryptedPassword = passwordEncoder.encode(request.password);
        user.setPassword(encryptedPassword);

        User savedUser = userRepository.save(user);
        Role clientRole = roleRepository.findById("CLIENT").orElseThrow(
                () -> new RuntimeException("El rol del cliente no existe")
        );

        userRepository.save(user);

        UserHasRoles userHasRoles = new UserHasRoles(savedUser, clientRole);
        userHasRolesRepository.save(userHasRoles);

        CreateUserResponse response = new CreateUserResponse();
        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setLastname(savedUser.getLastname());
        response.setEmail(savedUser.getEmail());
        response.setImage(savedUser.getImage());
        response.setPhone(savedUser.getPhone());

        List<Role> roles = roleRepository.findAllByUserHasRoles_User_Id(savedUser.getId());
        List<RoleDTO> roleDTOS = roles.stream()
                .map(role -> new RoleDTO(role.getId(), role.getName(), role.getImage(), role.getRoute()))
                .toList();

        response.setRoles(roleDTOS);

        return response;
    }

    @Transactional
    public LoginResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("El email o password son invalidos"));
                if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
                    throw new RuntimeException("El email o password son invalidos");
                }
        String token = jwtUtil.generateToken(user);

        List<Role> roles = roleRepository.findAllByUserHasRoles_User_Id(user.getId());
        List<RoleDTO> roleDTOS = roles.stream()
                .map(role -> new RoleDTO(role.getId(), role.getName(), role.getImage(), role.getRoute()))
                .toList();

        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setId(user.getId());
        createUserResponse.setName(user.getName());
        createUserResponse.setLastname(user.getLastname());
        createUserResponse.setEmail(user.getEmail());
        createUserResponse.setImage(user.getImage());
        createUserResponse.setPhone(user.getPhone());
        createUserResponse.setRoles(roleDTOS);

        LoginResponse response = new LoginResponse();
        response.setToken("Bearer " + token);
        response.setUser(createUserResponse);

        return response;
    }
}
