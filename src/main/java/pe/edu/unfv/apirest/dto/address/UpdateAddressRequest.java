package pe.edu.unfv.apirest.dto.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateAddressRequest {

    private String  address;
    private String neighborhood;
}

