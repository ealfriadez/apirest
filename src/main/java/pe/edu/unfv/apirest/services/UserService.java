package pe.edu.unfv.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.unfv.apirest.dto.role.RoleDTO;
import pe.edu.unfv.apirest.dto.user.*;
import pe.edu.unfv.apirest.dto.user.mapper.UserMapper;
import pe.edu.unfv.apirest.models.Role;
import pe.edu.unfv.apirest.models.User;
import pe.edu.unfv.apirest.models.UserHasRoles;
import pe.edu.unfv.apirest.repositories.RoleRepository;
import pe.edu.unfv.apirest.repositories.UserHasRolesRepository;
import pe.edu.unfv.apirest.repositories.UserRepository;
import pe.edu.unfv.apirest.utils.JwtUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public LoginResponse create(CreateUserRequest request){

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

        UserHasRoles userHasRoles = new UserHasRoles(savedUser, clientRole);
        userHasRolesRepository.save(userHasRoles);

        String token = jwtUtil.generateToken(user);
        List<Role> roles = roleRepository.findAllByUserHasRoles_User_Id(savedUser.getId());

        LoginResponse response = new LoginResponse();
        response.setToken("Bearer " + token);
        response.setUser(userMapper.toUserResponse(user, roles));

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

        LoginResponse response = new LoginResponse();
        response.setToken("Bearer " + token);
        response.setUser(userMapper.toUserResponse(user, roles));

        return response;
    }

    @Transactional
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario no esta registrado"));

        List<Role> roles = roleRepository.findAllByUserHasRoles_User_Id(user.getId());

        return userMapper.toUserResponse(user, roles);
    }

    @Transactional
    public UserResponse updateUserWithImage(Long id, UpdateUserRequest request) throws IOException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario no esta registrado"));

        if(request.getName() != null){
            user.setName(request.getName());
        }
        if(request.getLastname() != null) {
            user.setLastname(request.getLastname());
        }
        if(request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        if(request.getFile() != null && !request.getFile().isEmpty()) {
            String uploadDir = "uploads/users/" + user.getId();
            String filename = request.getFile().getOriginalFilename();
            String filePath = Paths.get(uploadDir, filename).toString();

            Files.createDirectories(Paths.get(uploadDir));
            Files.copy(request.getFile().getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            user.setImage("/" + filePath.replace("\\","/"));
        }

        userRepository.save(user);

        List<Role> roles = roleRepository.findAllByUserHasRoles_User_Id(user.getId());

        return userMapper.toUserResponse(user, roles);
    }
}
