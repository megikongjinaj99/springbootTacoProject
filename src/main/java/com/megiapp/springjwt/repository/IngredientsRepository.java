package com.megiapp.springjwt.repository;

import com.megiapp.springjwt.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredient, Integer> {

    Ingredient findIngredientsById(Integer id);
}
