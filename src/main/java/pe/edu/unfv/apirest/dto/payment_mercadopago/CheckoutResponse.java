package pe.edu.unfv.apirest.dto.payment_mercadopago;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutResponse {

    @JsonProperty("init_point")
    private String iniPoint;
}
