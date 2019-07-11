package ru.relex.restaurant.web.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.restaurant.web.model.ValueWrapper;
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
    public ValueWrapper<String> sayHello(@RequestParam("name") String name) {
        return new ValueWrapper<>(service.sayHello(name));
    }

}
