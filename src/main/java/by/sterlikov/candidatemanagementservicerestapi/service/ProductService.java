package by.sterlikov.candidatemanagementservicerestapi.service;

import by.sterlikov.candidatemanagementservicerestapi.exception.ProductNotFoundException;
import by.sterlikov.candidatemanagementservicerestapi.exception.StoreNotFoundException;
import by.sterlikov.candidatemanagementservicerestapi.model.Product;
import by.sterlikov.candidatemanagementservicerestapi.model.Store;
import by.sterlikov.candidatemanagementservicerestapi.repository.ProductRepository;
import by.sterlikov.candidatemanagementservicerestapi.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    public static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;


    public Product create(Product product) {
        LOG.info("Saving product " + product.getName());
        return productRepository.save(product);
    }

    public List<Product> readAll() {
        return productRepository.findAll();
    }

    public List<Product> readByCategoryId(Long id){
        return productRepository.findByCategoryId(id);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
