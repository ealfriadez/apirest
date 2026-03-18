package pe.edu.unfv.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unfv.apirest.models.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUserId(Long idUser);
}
