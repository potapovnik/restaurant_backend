package ru.relex.restaurant.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.relex.restaurant.service.DTO.DishDto;
import ru.relex.restaurant.service.DTO.DishIngredientDto;
import ru.relex.restaurant.service.DTO.DishesWithTotalCount;
import ru.relex.restaurant.service.DishIngredientIdService;
import ru.relex.restaurant.service.IDishIngredientService;
import ru.relex.restaurant.service.IDishService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/dishes",
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DishController {
  private final IDishService dishService;
  private final IDishIngredientService dishIngredientService;

  public DishController(IDishService dishService, IDishIngredientService dishIngredientService) {
    this.dishService = dishService;
    this.dishIngredientService = dishIngredientService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createIngredient(@RequestBody DishDto dishDto) {
    dishService.createDish(dishDto);
  }

  @GetMapping
  public DishesWithTotalCount listDishesAllTime(
      @RequestParam(name = "pageIndex", required = false, defaultValue = "0") int pageIndex,
      @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
      @RequestParam(name = "sortedBy", required = false, defaultValue = "id") String sortedBy,
      @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir,
      @RequestParam(name = "filter", required = false, defaultValue = "") String filter

  ) {
    return dishService.listDishesAllTime(pageIndex, pageSize, sortDir, sortedBy, filter);
  }

  @GetMapping("/inmenu")
  public List<DishDto> listDishesInMenu() {
    return dishService.listDishesInMenu();
  }

  @PostMapping("/consist")
  @ResponseStatus(HttpStatus.CREATED)
  public void createDishIngredient(@RequestBody DishIngredientDto dishIngDto) {
    dishIngredientService.createDishIngredient(dishIngDto);
  }

  @GetMapping("/consist")
  public List<DishIngredientDto> listDishIngr() {
    return dishIngredientService.listDishIngredients();
  }

  @DeleteMapping("/consist/{dishId}/{ingId}")
  public void deleteDishIngredient(@PathVariable("dishId") int dishId, @PathVariable("ingId") int ingId) {
    DishIngredientIdService tempId = new DishIngredientIdService();
    tempId.setDishId(dishId);
    tempId.setIngredientId(ingId);
    dishIngredientService.deleteDishIngredient(tempId);
  }
}
