package pe.edu.unfv.apirest.dto.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateAddressRequest {

    @JsonProperty("id_user")
    private Long idUser;

    private String  address;
    private String neighborhood;
}

