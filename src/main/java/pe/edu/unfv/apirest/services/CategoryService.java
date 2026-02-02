package pe.edu.unfv.apirest.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.unfv.apirest.dto.category.CategoryResponse;
import pe.edu.unfv.apirest.dto.category.CreateCetegoryRequest;
import pe.edu.unfv.apirest.dto.category.mapper.CategoryMapper;
import pe.edu.unfv.apirest.models.Category;
import pe.edu.unfv.apirest.repositories.CategoriRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Slf4j
@Service
public class CategoryService {

    @Autowired
    private CategoriRepository categoriRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional
    public CategoryResponse create(CreateCetegoryRequest request) throws IOException {

        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category categorySaved = categoriRepository.save(category);

        if(request.getFile() != null && !request.getFile().isEmpty()){
            String uploadDir = "uploads/categories/" + categorySaved.getId();
            String filename = request.getFile().getOriginalFilename();
            String filePath = Paths.get(uploadDir, filename).toString();

            Files.createDirectories(Paths.get(uploadDir));
            Files.copy(request.getFile().getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            categorySaved.setImage("/" + filePath.replace("\\", "/"));
            categoriRepository.save(categorySaved);
        }
        return categoryMapper.toCategoryResponse(category);
    }

    @Transactional
    public CategoryResponse update(Long id, CreateCetegoryRequest request) throws IOException {

        Category category = categoriRepository.findById(id).orElseThrow(
                () -> new RuntimeException("La categoria no existe")
        );

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        if(request.getFile() != null && !request.getFile().isEmpty()){

            if(category.getImage() != null){
                Path previusImagePath = Paths.get("." + category.getImage());
                if(Files.exists(previusImagePath)){
                    Files.delete(previusImagePath);
                }
            }

            String uploadDir = "uploads/categories/" + category.getId();
            String filename = request.getFile().getOriginalFilename();
            String filePath = Paths.get(uploadDir, filename).toString();

            Files.createDirectories(Paths.get(uploadDir));
            Files.copy(request.getFile().getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            category.setImage("/" + filePath.replace("\\", "/"));
            categoriRepository.save(category);
        }

        Category updateCategory = categoriRepository.save(category);
        return categoryMapper.toCategoryResponse(category);
    }

    @Transactional
    public List<CategoryResponse> findAll(){
        List<Category> categories = categoriRepository.findAll();
        return categories.stream().map(category -> {
            return categoryMapper.toCategoryResponse(category);
        }).toList();
    }

    @Transactional
    public void delete(Long id){
        Category category = categoriRepository.findById(id).orElseThrow(
                () -> new RuntimeException("La categoria no existe")
        );
        categoriRepository.delete(category);
    }
}
