package by.sterlikov.candidatemanagementservicerestapi.service;

import by.sterlikov.candidatemanagementservicerestapi.exception.StoreNotFoundException;
import by.sterlikov.candidatemanagementservicerestapi.model.Product;
import by.sterlikov.candidatemanagementservicerestapi.model.Store;
import by.sterlikov.candidatemanagementservicerestapi.repository.ProductRepository;
import by.sterlikov.candidatemanagementservicerestapi.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public static final Logger LOG = LoggerFactory.getLogger(StoreService.class);

    public Store addProductToStore(Long storeId, Product productToAdd) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store with id " + storeId + " not found"));
        Product product = productRepository.save(productToAdd);

        LOG.info("Saving product " + productToAdd.getName() + "in store " + store.getName());

        List<Product> currentProducts = store.getProducts();
        currentProducts.add(product);
        store.setProducts(currentProducts);

       return storeRepository.save(store);
    }

    public Store createOrUpdateStore(Store store) {
        return storeRepository.save(store);
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public void deleteStoreById(Long id) {
        storeRepository.deleteById(id);
    }
}
