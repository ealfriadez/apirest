package pe.edu.unfv.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unfv.apirest.models.Id.OrderHasProductId;
import pe.edu.unfv.apirest.models.OrderHasProducts;

public interface OrderHasProductsRepository extends JpaRepository<OrderHasProducts, OrderHasProductId> {
}
