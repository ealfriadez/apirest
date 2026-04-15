package pe.edu.unfv.apirest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public class PaymentMercadoPagoViewController {

    @GetMapping("/success")
    public String success(){
        return "redirect";
    }
}
