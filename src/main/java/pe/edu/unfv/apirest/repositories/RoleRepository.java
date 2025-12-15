package pe.edu.unfv.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unfv.apirest.models.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
    boolean existsByName(String name);
}
