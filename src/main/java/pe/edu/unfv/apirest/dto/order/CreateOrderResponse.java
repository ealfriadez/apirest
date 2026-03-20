package pe.edu.unfv.apirest.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateOrderResponse {

    private Long id;
    @JsonProperty("id_user")
    private Long idUser;

    @JsonProperty("id_address")
    private Long idAddress;
    private String status;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
