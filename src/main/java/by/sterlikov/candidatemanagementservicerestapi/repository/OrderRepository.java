package by.sterlikov.candidatemanagementservicerestapi.repository;

import by.sterlikov.candidatemanagementservicerestapi.model.Courier;
import by.sterlikov.candidatemanagementservicerestapi.model.Order;
import by.sterlikov.candidatemanagementservicerestapi.model.enums.OrderStatus;
import by.sterlikov.candidatemanagementservicerestapi.repository.viev.OrderView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT o.*, u.username " +
            "FROM Orders o " +
            "JOIN Users u ON o.user_id = u.id " +
            "WHERE o.order_status = ?1 " +
            "ORDER BY o.created_order DESC",
            nativeQuery = true)
    List<OrderView> findAllByOrderStatusOrderByCreatedOrderDesc(OrderStatus status);

    Optional<Order> findOrderById(Long id);

    Optional<Order> findOrderByCourier(Courier courier);

/*    List<Order> findOrderByOrderStatusOrderByCreatedOrderDesc(String status);*/


}
