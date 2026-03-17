package pe.edu.unfv.apirest.dto.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressResponse {

    private Long id;

    @JsonProperty("id_user")
    private Long idUser;

    private String  address;
    private String neighborhood;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
