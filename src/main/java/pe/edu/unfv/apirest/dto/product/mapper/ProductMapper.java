package pe.edu.unfv.apirest.dto.product.mapper;

import org.springframework.stereotype.Component;
import pe.edu.unfv.apirest.config.APIConfig;
import pe.edu.unfv.apirest.dto.product.ProductResponse;
import pe.edu.unfv.apirest.models.Product;

@Component
public class ProductMapper {

    public ProductResponse toProductResponse(Product product){

        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());
        response.setIdCategory(product.getCategory().getId());

        if(product.getImage1() != null) response.setImage1(APIConfig.BASE_URL + product.getImage1());
        if(product.getImage2() != null) response.setImage2(APIConfig.BASE_URL + product.getImage2());

        return response;
    }
}
