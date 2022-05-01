package com.megiapp.springjwt.controllers;

import com.megiapp.springjwt.dto.IngredientDto;
import com.megiapp.springjwt.models.Ingredient;
import com.megiapp.springjwt.security.services.IngredientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ingredients")
public class IngredientsController {

    private IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping
    public ResponseEntity<List<IngredientDto>> getAllTacos() {
        List<IngredientDto> ingredientDto = ingredientsService.findAllIngredients();
        return new ResponseEntity<>(ingredientDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto>  getIngredientsById (@PathVariable("id") Integer id) {
        IngredientDto ingredient = ingredientsService.findIngredientsById(id);
        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IngredientDto> addIngredients(@RequestBody IngredientDto ingredient) {
        IngredientDto newIngredients = ingredientsService.addIngredient(ingredient);
        return new ResponseEntity<>(newIngredients, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Ingredient> updateIngredients(@RequestBody Ingredient ingredients) {
        Ingredient updateIngredients = ingredientsService.updateIngredients(ingredients);
        return new ResponseEntity<>(updateIngredients, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> deleteIngredients(@PathVariable("id") Integer id) {
        ingredientsService.deleteIngredients(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}