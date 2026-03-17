package pe.edu.unfv.apirest.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.unfv.apirest.dto.address.AddressResponse;
import pe.edu.unfv.apirest.dto.address.CreateAddressRequest;
import pe.edu.unfv.apirest.dto.address.mapper.AddressMapper;
import pe.edu.unfv.apirest.models.Address;
import pe.edu.unfv.apirest.models.User;
import pe.edu.unfv.apirest.repositories.AddressRepository;
import pe.edu.unfv.apirest.repositories.UserRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressMapper addressMapper;

    @Transactional
    public AddressResponse create(CreateAddressRequest request){
        User user = userRepository.findById(request.getIdUser()).orElseThrow(
                ()-> new RuntimeException("El usuario no existe")
        );

        Address address = new Address();
        address.setUser(user);
        address.setAddress(request.getAddress());
        address.setNeighborhood(request.getNeighborhood());

        Address addressSaved = addressRepository.save(address);
        return addressMapper.toAddressResponse(addressSaved);
    }
}
