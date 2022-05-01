package com.megiapp.springjwt.payload.response;

import com.megiapp.springjwt.models.Order;

import java.util.List;

public class OrderHistoryResponse {
    private double total;
    private List<Order> orders;

    public OrderHistoryResponse(double total, List<Order> orders) {
        this.total = total;
        this.orders = orders;
    }

    public OrderHistoryResponse() {
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}


