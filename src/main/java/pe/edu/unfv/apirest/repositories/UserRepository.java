package pe.edu.unfv.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unfv.apirest.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
