package ru.relex.restaurant.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.restaurant.service.IIngredientPartService;
import ru.relex.restaurant.service.DTO.IngredientDto;
import ru.relex.restaurant.service.DTO.IngredientPartDto;

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

    @GetMapping
    public List<IngredientPartDto> listIngredientParts(){
        return ingredientPartService.listIngredientParts();
    }

    @GetMapping("/{id}")
    public IngredientPartDto oneIngredientPart(@PathVariable("id") int id){
        return ingredientPartService.findOneById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void createPart(@RequestBody IngredientPartDto dto){
        ingredientPartService.createIngredientPart(dto);
    }

    @DeleteMapping("/{id}")
    public void deletePart(@PathVariable("id") int id){
        ingredientPartService.deleteIngredientPart(id);
    }
}