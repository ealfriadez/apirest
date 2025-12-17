package pe.edu.unfv.apirest.dto.user.mapper;

import org.springframework.stereotype.Component;
import pe.edu.unfv.apirest.config.APIConfig;
import pe.edu.unfv.apirest.dto.role.RoleDTO;
import pe.edu.unfv.apirest.dto.user.UserResponse;
import pe.edu.unfv.apirest.models.Role;
import pe.edu.unfv.apirest.models.User;

import java.util.List;

@Component
public class UserMapper {

    public UserResponse toUserResponse(User user, List<Role> roles){

        List<RoleDTO> roleDTOS = roles.stream()
                .map(role -> new RoleDTO(role.getId(), role.getName(), role.getImage(), role.getRoute()))
                .toList();

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setLastname(user.getLastname());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRoles(roleDTOS);

        if(user.getImage() != null){
            String imageUrl = APIConfig.BASE_URL + user.getImage();
            response.setImage(imageUrl);
        }
        return response;
    }
}
