package pe.edu.unfv.apirest.dto.address.mapper;

import org.springframework.stereotype.Component;
import pe.edu.unfv.apirest.dto.address.AddressResponse;
import pe.edu.unfv.apirest.models.Address;

@Component
public class AddressMapper {

    public AddressResponse toAddressResponse(Address address){
        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setAddress(address.getAddress());
        response.setIdUser(address.getUser().getId());
        response.setNeighborhood(address.getNeighborhood());
        response.setCreatedAt(address.getCreatedAt());
        response.setUpdatedAt(address.getUpdatedAt());
        return response;
    }
}
