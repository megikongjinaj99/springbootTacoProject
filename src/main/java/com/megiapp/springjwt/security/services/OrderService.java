package com.megiapp.springjwt.security.services;

import com.megiapp.springjwt.enums.EOrder;
import com.megiapp.springjwt.exception.InvalidRequestsParameters;
import com.megiapp.springjwt.models.Order;
import com.megiapp.springjwt.models.Taco;
import com.megiapp.springjwt.models.User;
import com.megiapp.springjwt.payload.response.OrderHistoryResponse;
import com.megiapp.springjwt.repository.OrderRepository;
import com.megiapp.springjwt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    //A Logger object is used to log messages for a specific system or application component. Loggers are normally named,
    // using a hierarchical dot-separated namespace. Logger names can be arbitrary strings, but
    // they should normally be based on the package name or class name of the logged component, such as java.net or javax.
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    EmailService emailService;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Order> findAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public Order addOrder(Order order) {
        order.setType(EOrder.CREATED);
        return orderRepository.save(order);
    }

    public Order findOrderById(Integer id) {
        return orderRepository.findOrderById(id);
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    public boolean updateOrderStatus(Integer id, EOrder newStatus) {
        log.info(String.format("Received Request to update status for order %s", id));
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            Optional<User> user = userRepository.findById(order.get().getU_users().getId());
            if (user.isPresent()) {
                if (newStatus.equals(EOrder.CREATED)) {
                    order.get().setType(newStatus);
                    orderRepository.save(order.get());
                    log.info(String.format("Updated Order Status to %s", newStatus));
                    emailService.informClientAboutOrderStatusUpdate(order.get().getId(), user.get().getEmail(), newStatus);
                    log.info("Informed Client about Status update");
                    return true;
                }
                orderRepository.updateStatus(newStatus.toString(), id);
                log.info(String.format("Updated Order Status to %s", newStatus));
                return true;
            };
        }
        return false;
    }

    private double calculateOrderTotal(List<Taco> tacos) {
        double total = 0.0;
        for (Taco taco : tacos)
            total += taco.getPrice();
        return total;
    }

    public OrderHistoryResponse getAllClientOrders(String username) throws InvalidRequestsParameters {
        if (username==null)
            throw new InvalidRequestsParameters("Username cannot be Null or Empty");
        // Get the ID Of current User
        try {
            Optional<User> user = userRepository.findByUsername(username.trim());
            if (user.isPresent()) {
                List<Order> orders = orderRepository.findAllClientOrders(user.get().getId());
                //to get the number of elements in this list
                //nese ne list ka me shume se nje order
                if (orders.size() > 0) {
                    log.info("Found Orders belonging to user");
                    double totalPriceOfOrders = 0.0;
                    for (Order order : orders)
                        totalPriceOfOrders += order.getTotal();
                    log.info("Delivering Orders History");
                    return new OrderHistoryResponse(totalPriceOfOrders, orders);
                }}

                } catch(Exception e){
                    log.error(e.getMessage());
                }
                return null;
            }


        }

