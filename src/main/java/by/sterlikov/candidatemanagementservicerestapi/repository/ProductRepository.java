package by.sterlikov.candidatemanagementservicerestapi.repository;

import by.sterlikov.candidatemanagementservicerestapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long id);

    Optional<Product> findProductById(Long id);

    Optional<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findAllByNameContainingIgnoreCase(String fragment);
}
