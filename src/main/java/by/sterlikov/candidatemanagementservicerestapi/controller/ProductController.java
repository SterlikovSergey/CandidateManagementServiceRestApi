package by.sterlikov.candidatemanagementservicerestapi.controller;

import by.sterlikov.candidatemanagementservicerestapi.dto.ProductDto;
import by.sterlikov.candidatemanagementservicerestapi.mapper.ProductMapper;
import by.sterlikov.candidatemanagementservicerestapi.model.Product;
import by.sterlikov.candidatemanagementservicerestapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;


    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDto dto) {
        return new ResponseEntity<>(productService.create(productMapper.productDtoToProduct(dto)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> readAll() {
        return new ResponseEntity<>(productService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> readAllByCategory(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.readByCategoryId(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return HttpStatus.OK;
    }

}
