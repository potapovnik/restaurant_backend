package ru.relex.restaurant.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.restaurant.service.IIngredientService;
import ru.relex.restaurant.service.DTO.IngredientDto;

import java.util.List;

@RestController
@RequestMapping(path = "/ingredients",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class IngredientController {
    private final IIngredientService ingredientService;

    public IngredientController(IIngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createIngredient(@RequestBody IngredientDto ingredientDto) {
        ingredientService.createIngredient(ingredientDto);
    }

    @GetMapping
    public List<IngredientDto> listIngredients() {
        return ingredientService.listIngredients();
    }

    @DeleteMapping("/{id}")
    public void removeIngredient(@PathVariable("id") int id) {
        ingredientService.deleteIngredient(id);
    }
}
