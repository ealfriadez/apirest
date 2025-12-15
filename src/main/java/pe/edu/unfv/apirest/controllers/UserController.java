package pe.edu.unfv.apirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.unfv.apirest.dto.user.CreateUserRequest;
import pe.edu.unfv.apirest.models.User;
import pe.edu.unfv.apirest.services.UserService;

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
    public ResponseEntity<User> create(@RequestBody CreateUserRequest request){
        User user = userService.create(request);
        return ResponseEntity.ok(user);
    }
}
