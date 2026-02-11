package pe.edu.unfv.apirest.dto.product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateProductRequest {

    private String name;
    private String description;
    private Double price;
    private MultipartFile[] files;
}
