package ru.relex.restaurant.web.api;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.restaurant.service.DTO.OrdersDto;
import ru.relex.restaurant.service.impl.OrdersService;

import java.util.List;

@RestController
@RequestMapping(value =
    "/orders")
public class OrdersController {
  private final OrdersService ordersService;

  public OrdersController(OrdersService ordersService) {
    this.ordersService = ordersService;
  }

  @GetMapping("{id}")
  public OrdersDto getByid(@PathVariable("id") int id) {
    OrdersDto ordersDto = ordersService.getById(id);
    if (ordersDto == null) {
      return null;
    }
    return ordersDto;
  }

  @GetMapping("/getAll")
  public List<OrdersDto> getAll() {
    return ordersService.getAll();
  }

  @PostMapping
  public boolean insert(@RequestBody OrdersDto ordersDto) {
    boolean IsDone = ordersService.insert(ordersDto);
    return IsDone;

  }

  @PutMapping
  public OrdersDto update(@PathVariable OrdersDto ordersDto) {
    OrdersDto updatedOrders = ordersService.update(ordersDto);
    if (updatedOrders == null) {
      return null;
    }
    return updatedOrders;
  }
}
