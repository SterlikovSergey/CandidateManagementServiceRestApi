package by.sterlikov.candidatemanagementservicerestapi.mapper;

import by.sterlikov.candidatemanagementservicerestapi.dto.CategoryDto;
import by.sterlikov.candidatemanagementservicerestapi.model.Category;
import org.springframework.stereotype.Component;
@Component
public class CategoryMapper {
    public Category categoryDtoToCategory(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}
