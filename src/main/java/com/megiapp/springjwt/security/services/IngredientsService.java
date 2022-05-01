package com.megiapp.springjwt.security.services;

import com.megiapp.springjwt.dto.IngredientDto;
import com.megiapp.springjwt.models.Ingredient;
import com.megiapp.springjwt.repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientsService {
    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public IngredientDto addIngredient(IngredientDto ingredientDto) {
        Ingredient ingredient = new Ingredient();

        ingredient.setName(ingredientDto.getName());
        ingredient.setPrice(ingredientDto.getPrice());
        ingredient.setType(ingredientDto.getType());
        ingredientsRepository.save(ingredient);

        ingredientDto.setId(ingredient.getId());
        return ingredientDto;
    }

    public List<IngredientDto> findAllIngredients() {
        List<Ingredient> ingredientList = ingredientsRepository.findAll();

        List<IngredientDto> ingredientDtoList = ingredientList.stream()
                .map(ingredient -> new IngredientDto(ingredient))
                .collect(Collectors.toList());

        return ingredientDtoList;

    }

    public IngredientDto findIngredientsById(Integer id) {
        Ingredient ingredient = ingredientsRepository.findIngredientsById(id);
        IngredientDto ingredientDto = new IngredientDto(ingredient);
        return ingredientDto;
    }

    public Ingredient updateIngredients(Ingredient ingredients) {
        return ingredientsRepository.save(ingredients);
    }

    public void deleteIngredients(Integer id) {
        ingredientsRepository.deleteById(id);
    }
}