package pe.edu.unfv.apirest.dto.category.mapper;

import org.springframework.stereotype.Component;
import pe.edu.unfv.apirest.config.APIConfig;
import pe.edu.unfv.apirest.dto.category.CategoryResponse;
import pe.edu.unfv.apirest.models.Category;

@Component
public class CategoryMapper {

    public CategoryResponse toCategoryResponse(Category category){
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        response.setCreatedAt(category.getCreatedAt());
        response.setUpdatedAt(category.getUpdatedAt());

        if(category.getImage() != null){
            String imageUrl = APIConfig.BASE_URL + category.getImage();
            response.setImage(imageUrl);
        }
        return response;
    }
}
