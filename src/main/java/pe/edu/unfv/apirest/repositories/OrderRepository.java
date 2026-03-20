package pe.edu.unfv.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unfv.apirest.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
