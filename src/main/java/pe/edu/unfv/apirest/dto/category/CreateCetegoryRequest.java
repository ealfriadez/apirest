package pe.edu.unfv.apirest.dto.category;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateCetegoryRequest {

    private String name;
    private String description;
    private MultipartFile file;
}
