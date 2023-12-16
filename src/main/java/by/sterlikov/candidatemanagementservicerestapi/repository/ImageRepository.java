package by.sterlikov.candidatemanagementservicerestapi.repository;

import by.sterlikov.candidatemanagementservicerestapi.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByCourierId(Long courierId);

    Optional<Image> findByUserId(Long userId);

    Optional<Image> findByProductId(Long productId);
}
