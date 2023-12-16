package by.sterlikov.candidatemanagementservicerestapi.repository.viev;

import by.sterlikov.candidatemanagementservicerestapi.model.Product;
import by.sterlikov.candidatemanagementservicerestapi.model.User;
import by.sterlikov.candidatemanagementservicerestapi.model.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderView {

    LocalDateTime getCreatedOrder();
    OrderStatus getOrderStatus();
    List<Product> getProducts();
    String getUsername();


}
