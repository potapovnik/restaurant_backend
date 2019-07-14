package ru.relex.restaurant.web.api;


import org.apache.tomcat.jni.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.restaurant.service.DTO.UserDto;
import ru.relex.restaurant.service.impl.UserService;

@RestController
@RequestMapping(value =
        "/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public UserDto getById(@PathVariable("id") int id) {
        UserDto userDto = userService.getById(id);
        if (userDto == null) {
            return null;
        }
        return userDto;
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto userDto) {
        UserDto updatedUser = userService.update(userDto);
        if (updatedUser == null){
            return null;
        }
        return updatedUser;
    }

    @PostMapping
    public boolean insert(@RequestBody UserDto userDto){
      return userService.insert(userDto);
    }
    @DeleteMapping("{id}")
    public boolean deleteById(@PathVariable("id") int id){
        return userService.deleteById(id);
    }
}
