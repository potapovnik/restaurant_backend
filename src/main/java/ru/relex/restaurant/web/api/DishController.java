package ru.relex.restaurant.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.restaurant.service.DTO.DishDto;
import ru.relex.restaurant.service.IDishService;

import java.util.List;

@RestController
@RequestMapping(path = "/dishes",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DishController {
    private final IDishService dishService;

    public DishController(IDishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createIngredient(@RequestBody DishDto dishDto) {
        dishService.createDish(dishDto);
    }

    @GetMapping
    public List<DishDto> listDishesAllTime() {
        return dishService.listDishesAllTime();
    }



}
