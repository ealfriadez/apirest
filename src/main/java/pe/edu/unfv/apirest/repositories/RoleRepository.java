package pe.edu.unfv.apirest.repositories;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unfv.apirest.models.Role;
import pe.edu.unfv.apirest.models.UserHasRoles;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, String> {

    boolean existsByName(String name);

    List<Role> findAllByUserHasRoles_User_Id(Long idUser);

}
