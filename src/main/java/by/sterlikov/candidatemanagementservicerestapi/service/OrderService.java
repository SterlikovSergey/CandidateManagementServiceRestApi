package by.sterlikov.candidatemanagementservicerestapi.service;

import by.sterlikov.candidatemanagementservicerestapi.exception.UserNotFoundException;
import by.sterlikov.candidatemanagementservicerestapi.model.Order;
import by.sterlikov.candidatemanagementservicerestapi.model.Product;
import by.sterlikov.candidatemanagementservicerestapi.model.User;
import by.sterlikov.candidatemanagementservicerestapi.model.enums.OrderStatus;
import by.sterlikov.candidatemanagementservicerestapi.repository.OrderRepository;
import by.sterlikov.candidatemanagementservicerestapi.repository.viev.OrderView;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    public Order createOrder(Long id) {
        User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("User " +
                "not found with id " + id));

        LocalDateTime currentDateTime = LocalDateTime.now();
        Order newOrder = Order.builder()
                .createdOrder(currentDateTime)
                .orderStatus(OrderStatus.CREATED)
                .user(user)
                .build();

        return orderRepository.save(newOrder);
    }

    public Order addProductToOrder(Long orderId, Product product) {
/*        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            // Обработка ошибки - заказ не найден
            // Например, выброс исключения или возврат null или Optional.empty()
        }

        Order order = optionalOrder.get();
        List<Product> existingProducts = order.getProducts();
        existingProducts.add(product);

        // Обновляем поля заказа, например, общую стоимость и количество товаров
        BigDecimal totalCost = calculateTotalCost(existingProducts); // Подсчитываем общую стоимость товаров
        int totalItems = existingProducts.size(); // Получаем общее количество товаров

        order.setTotalCost(totalCost);
        order.setTotalItems(totalItems);

        return orderRepository.save(order);*/
        return null;
    }

    private BigDecimal calculateTotalCost(List<Product> products) {
        // Логика для подсчета общей стоимости товаров
        // Например, можно пройти по всем товарам и сложить их цены
        // Возвращаемая сумма будет общей стоимостью
        return null;
    }

    public Order createOrUpdateOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<OrderView> getAllOrdersByStatus(OrderStatus status) {
        return orderRepository.findAllByOrderStatusOrderByCreatedOrderDesc(status);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
