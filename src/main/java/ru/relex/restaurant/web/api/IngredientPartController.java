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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.relex.restaurant.service.DTO.IngredientPartFullDto;
import ru.relex.restaurant.service.IIngredientPartService;
import ru.relex.restaurant.service.DTO.IngredientDto;
import ru.relex.restaurant.service.DTO.IngredientPartDto;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path = "/ingredientparts",
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class IngredientPartController {
  private final IIngredientPartService ingredientPartService;

  public IngredientPartController(IIngredientPartService ingredientPartService) {
    this.ingredientPartService = ingredientPartService;
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  private void createPart(@RequestBody IngredientPartFullDto dto) {
    ingredientPartService.createIngredientPart(dto);
  }

  @PutMapping("/reduce")
  public boolean reduceAmountOfIngredient(@RequestParam(name = "ingredientId", required = true) Integer ingredientId,
                                          @RequestParam(name = "delta", required = true) Double delta) {
    return ingredientPartService.reduceAmountOfIngredient(ingredientId, delta);
  }

  @GetMapping("/summaryVolume")
  public Double summaryVolumeOfAllIngredients() {
    return ingredientPartService.summaryVolumeOfAllIngredients();
  }


  @DeleteMapping("/{id}")
  public void deletePart(@PathVariable("id") int id) {
    ingredientPartService.deleteIngredientPart(id);
  }

  @GetMapping("/summary/{id}")
  public Double summaryAmountOfIngredient(@PathVariable("id") int id) {
    return ingredientPartService.summaryAmountOfIngredient(id);
  }
}
