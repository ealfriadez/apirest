package pe.edu.unfv.apirest.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pe.edu.unfv.apirest.dto.product.ProductResponse;

import java.time.LocalDateTime;

@Data
public class OrderHasProductResponse {

    private ProductResponse product;
    private Long quantity;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
