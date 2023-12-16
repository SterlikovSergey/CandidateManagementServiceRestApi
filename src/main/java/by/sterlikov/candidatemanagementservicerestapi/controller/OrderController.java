package by.sterlikov.candidatemanagementservicerestapi.controller;

import by.sterlikov.candidatemanagementservicerestapi.model.Order;
import by.sterlikov.candidatemanagementservicerestapi.model.enums.OrderStatus;
import by.sterlikov.candidatemanagementservicerestapi.repository.viev.OrderView;
import by.sterlikov.candidatemanagementservicerestapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/{id}")
    public ResponseEntity<Order
            > getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/all/create")
    public ResponseEntity<List<OrderView>> getAllOrdersByStatusCreate() {
        OrderStatus status = OrderStatus.CREATED;
        List<OrderView> orders = orderService.getAllOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Order> createOrder(@PathVariable ("id") Long userId) {
        Order savedOrder = orderService.createOrder(userId);
        return ResponseEntity.ok(savedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}
