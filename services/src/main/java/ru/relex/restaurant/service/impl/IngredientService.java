package ru.relex.restaurant.service.impl;

import org.springframework.stereotype.Service;
import ru.relex.restaurant.db.JpaRepository.IngredientRepository;
import ru.relex.restaurant.db.entity.Ingredient;
import ru.relex.restaurant.service.IIngredientService;
import ru.relex.restaurant.service.DTO.IngredientDto;
import ru.relex.restaurant.service.mapper.IIngredientMapstruct;

import java.util.List;
import java.util.Set;

@Service
public class IngredientService implements IIngredientService {
    public IngredientService(final IngredientRepository repository,final IIngredientMapstruct mapstruct) {
        this.repository = repository;
        this.mapstruct = mapstruct;
    }
    private final IngredientRepository repository;
    private final IIngredientMapstruct mapstruct;


    @Override
    public void createIngredient(IngredientDto ingredientDto) {
        repository.save(mapstruct.fromDto(ingredientDto));
    }

    @Override
    public List<IngredientDto> listIngredients() {
        return mapstruct.toDto(repository.findAll());
    }

    @Override
    public void updateIngredient(IngredientDto ingredientDto) {

    }

    @Override
    public void deleteIngredient(Integer id) {
        repository.deleteById(id);
    }
}
