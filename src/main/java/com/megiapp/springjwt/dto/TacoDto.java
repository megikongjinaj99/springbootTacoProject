package com.megiapp.springjwt.dto;

import com.megiapp.springjwt.models.Order;
import com.megiapp.springjwt.models.Taco;
import com.megiapp.springjwt.models.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TacoDto {
    private Integer id;

    private LocalDate createdAt;

    private String name;

    private Set<IngredientDto> ingredients = new HashSet<>();

    private Set<User> u_users= new HashSet<>();

    private Set<Order> orders= new HashSet<>();

    public TacoDto() {
    }

    public TacoDto(Taco taco) {
        this.id = taco.getId();
      this.createdAt = taco.getCreatedAt();
        this.name = taco.getName();
        this.ingredients = taco.getIngredients().stream()
           .map(ingredient -> new IngredientDto(ingredient))
           .collect(Collectors.toSet());

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<User> getU_users() {
        return u_users;
    }

    public void setU_users(Set<User> u_users) {
        this.u_users = u_users;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
