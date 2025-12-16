package pe.edu.unfv.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unfv.apirest.models.Id.UserRoleId;
import pe.edu.unfv.apirest.models.UserHasRoles;

public interface UserHasRolesRepository extends JpaRepository<UserHasRoles, UserRoleId> {
}
