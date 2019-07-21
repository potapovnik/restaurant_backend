package ru.relex.restaurant.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.restaurant.service.DTO.IngredientsWithTotalCount;
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
  public IngredientsWithTotalCount listIngredients(
      @RequestParam(name = "pageIndex", required = false, defaultValue = "0") int pageIndex,
      @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
      @RequestParam(name = "sortedBy", required = false, defaultValue = "id") String sortedBy,
      @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir) {
    return ingredientService.listIngredients(pageIndex, pageSize, sortDir, sortedBy);
  }

  @GetMapping("/missing")
  public List<IngredientDto> listMissingIngredients() {
    return ingredientService.getMissingIngredients(dishService.listDishesInMenu());
  }

  @PutMapping("/reduce/{id}/{delta}")
  public boolean reduceIngredientAmount(@PathVariable("id") int id, @PathVariable("delta") double delta) {
    return ingredientService.reduceAmountOfIngredient(id, delta);
  }

  @GetMapping("/summary/{id}")
  public Double summaryAmountOfIngredient(@PathVariable("id") int id) {
    return ingredientService.summaryAmountOfIngredient(id);
  }

  @DeleteMapping("/{id}")
  public void removeIngredient(@PathVariable("id") int id) {
    ingredientService.deleteIngredient(id);
  }


}
