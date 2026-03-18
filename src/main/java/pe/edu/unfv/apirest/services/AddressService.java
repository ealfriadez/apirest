package pe.edu.unfv.apirest.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.unfv.apirest.dto.address.AddressResponse;
import pe.edu.unfv.apirest.dto.address.CreateAddressRequest;
import pe.edu.unfv.apirest.dto.address.UpdateAddressRequest;
import pe.edu.unfv.apirest.dto.address.mapper.AddressMapper;
import pe.edu.unfv.apirest.models.Address;
import pe.edu.unfv.apirest.models.User;
import pe.edu.unfv.apirest.repositories.AddressRepository;
import pe.edu.unfv.apirest.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.addressMapper = addressMapper;
    }

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

    @Transactional
    public List<AddressResponse> findByUserId(Long idUser){
        List<Address> addresses = addressRepository.findByUserId(idUser);
        return addresses.stream().map(addressMapper::toAddressResponse).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long idUser){
        Address address = addressRepository.findById(idUser).orElseThrow(
                ()-> new RuntimeException("La dirección no existe")
        );
        addressRepository.delete(address);
    }

    @Transactional
    public AddressResponse update(Long idUser, UpdateAddressRequest request){
        Address address = addressRepository.findById(idUser).orElseThrow(
                ()-> new RuntimeException("La dirección no existe")
        );

        address.setAddress(request.getAddress());
        address.setNeighborhood(request.getNeighborhood());

        Address updateAddress = addressRepository.save(address);
        return addressMapper.toAddressResponse(updateAddress);
    }
}
