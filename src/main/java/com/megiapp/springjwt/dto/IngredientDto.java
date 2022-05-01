package com.megiapp.springjwt.dto;

import com.megiapp.springjwt.models.Ingredient;

import com.megiapp.springjwt.enums.Type;

public class IngredientDto {
    private Integer id;

    private Float price;

    private String name;

    private Type type;

    public IngredientDto() {
    }

    public IngredientDto(Ingredient ingredient) {
       this.price = ingredient.getPrice();
       this.name = ingredient.getName();
       this.type = ingredient.getType();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}


