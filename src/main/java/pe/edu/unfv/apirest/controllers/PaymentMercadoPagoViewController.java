package pe.edu.unfv.apirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.edu.unfv.apirest.dto.payment_mercadopago.CheckoutResponse;
import pe.edu.unfv.apirest.services.PaymentMercadoPagoService;

import java.util.Map;

@Controller
@RequestMapping("/payment")
public class PaymentMercadoPagoViewController {

    @GetMapping("/success")
    public String success(){
        return "redirect";
    }
}
