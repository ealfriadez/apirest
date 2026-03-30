package pe.edu.unfv.apirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.unfv.apirest.dto.payment_mercadopago.CheckoutResponse;
import pe.edu.unfv.apirest.services.PaymentMercadoPagoService;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentMercadoPAgoController {

    @Autowired
    private PaymentMercadoPagoService paymentMercadoPagoService;

    @PostMapping("/create")
    public ResponseEntity<?> create(){
        try{
            CheckoutResponse response = paymentMercadoPagoService.createCheckoutPreference();
            return ResponseEntity.ok(response);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "message", e.getMessage(),
                    "statusCode", HttpStatus.BAD_REQUEST.value()
            ));
        }
    }

    @GetMapping("/failure")
    public ResponseEntity<?> failure(@RequestParam Map<String, String> queryParams){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "message", "La transaccion fallo: " + queryParams.toString(),
                "statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value()
        ));
    }

    @GetMapping("/pending")
    public ResponseEntity<?> pending(@RequestParam Map<String, String> queryParams){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Map.of(
                "message", "Pago pendiente: " + queryParams.toString(),
                "statusCode", HttpStatus.ACCEPTED.value()
        ));
    }
}
