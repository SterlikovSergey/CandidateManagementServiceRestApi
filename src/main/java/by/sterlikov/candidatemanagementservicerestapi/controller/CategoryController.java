package by.sterlikov.candidatemanagementservicerestapi.controller;

import by.sterlikov.candidatemanagementservicerestapi.dto.CategoryDto;
import by.sterlikov.candidatemanagementservicerestapi.mapper.CategoryMapper;
import by.sterlikov.candidatemanagementservicerestapi.model.Category;
import by.sterlikov.candidatemanagementservicerestapi.service.CategoryService;
import by.sterlikov.candidatemanagementservicerestapi.validations.ResponseErrorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final ResponseErrorValidator responseErrorValidator;

    @GetMapping
    public ResponseEntity<List<Category>> readAll() {
        return new ResponseEntity<>(categoryService.readAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CategoryDto dto, BindingResult result) {
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(result);
        if(!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        return new ResponseEntity<>(categoryService.create(categoryMapper.categoryDtoToCategory(dto)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return HttpStatus.OK;
    }

}
