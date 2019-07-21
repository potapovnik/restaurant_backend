package ru.relex.restaurant.web.api;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.restaurant.service.DTO.CookOrdersDto;
import ru.relex.restaurant.service.DTO.WaiterOrdersDto;
import ru.relex.restaurant.service.impl.CookOrdersService;

import java.util.List;

@RestController
@RequestMapping(value = "/cookOrders")
public class CookOrdersController {
  private final CookOrdersService cookOrdersService;

  public CookOrdersController(CookOrdersService cookOrdersService) {
    this.cookOrdersService = cookOrdersService;
  }

  @GetMapping("{id}")
  public CookOrdersDto getByid(@PathVariable("id") int id) {
    CookOrdersDto ordersDto = cookOrdersService.getById(id);
    if (ordersDto == null) {
      return null;
    }
    return ordersDto;
  }

  @GetMapping("/getAll")
  public List<CookOrdersDto> getAll() {
    return cookOrdersService.getAll();
  }

  @PostMapping
  public boolean insert(@RequestBody CookOrdersDto cookOrdersDto) {
    boolean isDone = cookOrdersService.insert(cookOrdersDto);
    return isDone;

  }

  @GetMapping("getAllById/{id}")
  public List<CookOrdersDto> getAllById(@PathVariable("id") int id) {
    return cookOrdersService.getAllById(id);
  }

  @GetMapping("getAllByIdUser/{id}")
  public List<CookOrdersDto> getAllByIdUser(@PathVariable("id") int id) {
    return cookOrdersService.getAllByIdUser(id);
  }

  @PutMapping
  public CookOrdersDto update(@RequestBody CookOrdersDto cookOrdersDto) {
    CookOrdersDto updatedOrders = cookOrdersService.update(cookOrdersDto);
    if (updatedOrders == null) {
      return null;
    }
    return updatedOrders;
  }
}
