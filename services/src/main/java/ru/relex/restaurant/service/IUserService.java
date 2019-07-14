package ru.relex.restaurant.service;


import ru.relex.restaurant.service.DTO.UserDto;

public interface IUserService {
    public UserDto getById(int id);

    public boolean deleteById(int id);

    public boolean insert(UserDto userDto);

    public UserDto update(UserDto userDto);
}
