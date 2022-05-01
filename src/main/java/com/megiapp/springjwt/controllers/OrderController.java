package com.megiapp.springjwt.controllers;

import com.megiapp.springjwt.enums.EOrder;
import com.megiapp.springjwt.exception.InvalidRequestsParameters;
import com.megiapp.springjwt.models.Order;
import com.megiapp.springjwt.models.Taco;
import com.megiapp.springjwt.payload.response.OrderHistoryResponse;
import com.megiapp.springjwt.security.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order>  getOrderById (@PathVariable("id") Integer id) {
        Order orders = orderService.findOrderById(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
       Order newOrder = orderService.addOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Taco> deleteOrder(@PathVariable("id") Integer id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateStatus/{id}")
   public ResponseEntity<?> updateStatus(@PathVariable Integer id, @RequestBody String newStatus,
                                         @CurrentSecurityContext(expression = "authentication?.name") String username) {
        log.info("Received request to view Order associated with current user");
        try {
            boolean updated = orderService.updateOrderStatus(id, EOrder.valueOf(newStatus));
            if (updated) {
                log.info("Order Status updated successfully");
                return new ResponseEntity<>("Order Status updated successfully", HttpStatus.OK);
            }
            log.warn("No orders found.");
            return new ResponseEntity<>("Could not find any Orders in the Database", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("Something went wrong while updating Order Status, see Logs.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//na duhet vetem name nga authentication data
    @GetMapping("/getOrderHistory")
    public ResponseEntity<?> getOrdersHistory(@CurrentSecurityContext(expression = "authentication?.name")
            String username) throws InvalidRequestsParameters {
        log.info("Received request to view user's Order History");
        try {
            OrderHistoryResponse ordersHistory = orderService.getAllClientOrders(username);
            if (ordersHistory != null) {
                log.info("Found Orders History. Showing...");
                return new ResponseEntity<>(ordersHistory, HttpStatus.OK);
            }
            return new ResponseEntity<>("Could not find any Orders associated with current user in the Database", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("Something went wrong while retrieving Orders for the History creation, see Logs.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}