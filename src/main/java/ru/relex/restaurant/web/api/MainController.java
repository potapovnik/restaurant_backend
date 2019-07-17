package ru.relex.restaurant.web.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.restaurant.service.DTO.RoleDTO;
import ru.relex.restaurant.service.ITestService;

@RestController
@RequestMapping(value =
    "/hello", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MainController {


  private final ITestService service;

  public MainController(ITestService service) {
    this.service = service;
  }


  @GetMapping
  public RoleDTO getByID(@RequestParam("id") int id) {
    RoleDTO roleDTO = null;
    if (roleDTO == null) {
      return null;//изменить на ошибку
    }
    return roleDTO;
  }

}
