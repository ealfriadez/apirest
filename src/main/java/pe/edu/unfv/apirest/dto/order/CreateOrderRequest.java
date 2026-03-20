package pe.edu.unfv.apirest.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    @JsonProperty("id_user")
    private Long IdUser;

    @JsonProperty("id_address")
    private Long idAddress;

    private String status;
    private List<ProductQuantity> products;

    @Data
    public static class ProductQuantity{
        private Long id;
        private Long quantity;
    }
}
