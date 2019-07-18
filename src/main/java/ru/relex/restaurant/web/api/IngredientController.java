package ru.relex.restaurant.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.relex.restaurant.service.IDishService;
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
  private final IDishService dishService;

  public IngredientController(IIngredientService ingredientService, IDishService dishService) {
    this.ingredientService = ingredientService;
    this.dishService = dishService;
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

  @GetMapping("/missing")
  public List<IngredientDto> listMissingIngredients() {
    return ingredientService.getMissingIngredients(dishService.listDishesInMenu());
  }

  @DeleteMapping("/{id}")
  public void removeIngredient(@PathVariable("id") int id) {
    ingredientService.deleteIngredient(id);
  }
}
