package com.megiapp.springjwt.repository;

import com.megiapp.springjwt.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderById(Integer id);
    @Modifying
    @Query(value = "UPDATE orders SET order_status = ?1 WHERE o.id >=1", nativeQuery = true)
    void updateStatus(String newStatus, Integer id);

    @Query(value = "SELECT * FROM orders WHERE user_id = 20", nativeQuery = true)
    List<Order> findAllClientOrders(Integer id);



//    void updateStatus(String toString, Integer orderId);
}