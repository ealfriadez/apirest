package pe.edu.unfv.apirest.models;

import jakarta.persistence.*;
import lombok.Data;
import pe.edu.unfv.apirest.models.Id.UserRoleId;

@Data
@Entity
@Table(name = "user_has_roles")
public class UserHasRoles {

    @EmbeddedId
    private UserRoleId id = new UserRoleId();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "id_rol")
    private Role role;
}
