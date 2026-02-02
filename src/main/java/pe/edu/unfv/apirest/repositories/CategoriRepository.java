package pe.edu.unfv.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unfv.apirest.models.Category;

public interface CategoriRepository extends JpaRepository<Category, Long> {
}
