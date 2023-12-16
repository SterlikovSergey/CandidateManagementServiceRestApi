package by.sterlikov.candidatemanagementservicerestapi.mapper;

import by.sterlikov.candidatemanagementservicerestapi.dto.ProductDto;
import by.sterlikov.candidatemanagementservicerestapi.model.Category;
import by.sterlikov.candidatemanagementservicerestapi.model.Product;
import by.sterlikov.candidatemanagementservicerestapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryService categoryService;

    public Product productDtoToProduct(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setAmount(dto.getAmount());
        product.setPrice(dto.getPrice());
        Category category = categoryService.readById(dto.getCategoryId());
        product.setCategory(category);
        return product;
    }
}
