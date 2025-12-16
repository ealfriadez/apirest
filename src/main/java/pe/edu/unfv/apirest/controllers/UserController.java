package pe.edu.unfv.apirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.unfv.apirest.dto.user.CreateUserRequest;
import pe.edu.unfv.apirest.dto.user.CreateUserResponse;
import pe.edu.unfv.apirest.dto.user.LoginRequest;
import pe.edu.unfv.apirest.dto.user.LoginResponse;
import pe.edu.unfv.apirest.models.User;
import pe.edu.unfv.apirest.services.UserService;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //GET - OBTENER
    //POST - CREAR
    //PUT - ACTUALIZAR
    //DELETE - ELIMINAR

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> create(@RequestBody CreateUserRequest request){
        CreateUserResponse user = userService.create(request);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        try{
            CreateUserResponse response = userService.findById(id);
            return ResponseEntity.ok(response);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", e.getMessage(),
                    "statusCode", HttpStatus.NOT_FOUND.value()
            ));
        }
    }
}
