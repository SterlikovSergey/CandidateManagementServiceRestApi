package by.sterlikov.candidatemanagementservicerestapi.controller;

import by.sterlikov.candidatemanagementservicerestapi.dto.ProductDto;
import by.sterlikov.candidatemanagementservicerestapi.dto.StoreDto;
import by.sterlikov.candidatemanagementservicerestapi.mapper.ProductMapper;
import by.sterlikov.candidatemanagementservicerestapi.mapper.StoredMapper;
import by.sterlikov.candidatemanagementservicerestapi.model.Store;
import by.sterlikov.candidatemanagementservicerestapi.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    private final StoredMapper storedMapper;

    private final ProductMapper productMapper;



    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        Store store = storeService.getStoreById(id);
        return ResponseEntity.ok(store);
    }

    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> stores = storeService.getAllStores();
        return ResponseEntity.ok(stores);
    }

    @PostMapping
    public ResponseEntity<Store> createOrUpdateStore(@RequestBody StoreDto store) {
        Store savedStore = storeService.createOrUpdateStore(storedMapper.storeDtoToStore(store));
        return ResponseEntity.ok(savedStore);
    }

    @PostMapping("/{storeId}/add-product")
    public ResponseEntity<Store> addProductToStore(
            @PathVariable Long storeId,
            @RequestBody ProductDto dto
            ) {
        Store updatedStore = storeService.addProductToStore(storeId,
                productMapper.productDtoToProduct(dto));
        return ResponseEntity.ok(updatedStore);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStoreById(@PathVariable Long id) {
        storeService.deleteStoreById(id);
        return ResponseEntity.noContent().build();
    }
}
