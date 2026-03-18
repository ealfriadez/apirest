package pe.edu.unfv.apirest.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.unfv.apirest.dto.address.AddressResponse;
import pe.edu.unfv.apirest.dto.address.CreateAddressRequest;
import pe.edu.unfv.apirest.dto.address.UpdateAddressRequest;
import pe.edu.unfv.apirest.dto.category.CategoryResponse;
import pe.edu.unfv.apirest.dto.category.CreateCetegoryRequest;
import pe.edu.unfv.apirest.services.AddressService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateAddressRequest request){
        try{
            AddressResponse response = addressService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "message", e.getMessage(),
                    "statusCode", HttpStatus.BAD_REQUEST.value()
            ));
        }
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<?> update(@PathVariable Long idUser, @RequestBody UpdateAddressRequest request){
        try{
            AddressResponse response = addressService.update(idUser, request);
            return ResponseEntity.ok(true);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "message", e.getMessage(),
                    "statusCode", HttpStatus.BAD_REQUEST.value()
            ));
        }
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<?> findByUserId(@PathVariable Long idUser){
        try{
            List<AddressResponse> response = addressService.findByUserId(idUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "message", e.getMessage(),
                    "statusCode", HttpStatus.BAD_REQUEST.value()
            ));
        }
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> delete(@PathVariable Long idUser){
        try{
            addressService.delete(idUser);
            return ResponseEntity.ok(true);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "message", e.getMessage(),
                    "statusCode", HttpStatus.BAD_REQUEST.value()
            ));
        }
    }
}
