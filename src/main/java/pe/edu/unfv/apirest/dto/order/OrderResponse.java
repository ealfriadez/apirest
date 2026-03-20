package pe.edu.unfv.apirest.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pe.edu.unfv.apirest.dto.address.AddressResponse;
import pe.edu.unfv.apirest.dto.user.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {

    private Long id;
    private String status;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    private AddressResponse address;
    private UserResponse user;
    private List<OrderHasProductResponse> orderHasProducts;
}
