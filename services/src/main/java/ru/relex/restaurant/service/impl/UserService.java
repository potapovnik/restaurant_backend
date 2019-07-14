package ru.relex.restaurant.service.impl;

import org.springframework.stereotype.Service;
import ru.relex.restaurant.db.JpaRepository.UsersRepository;
import ru.relex.restaurant.db.entity.Users;
import ru.relex.restaurant.service.DTO.UserDto;
import ru.relex.restaurant.service.IUserService;
import ru.relex.restaurant.service.mapper.IUserMapper;

@Service
public class UserService implements IUserService {
    private final UsersRepository usersRepository;
    private final IUserMapper userMapper;

    public UserService(UsersRepository usersRepository, IUserMapper userMapper) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto getById(int id) {
        Users user = usersRepository.findById(id).get();
        return userMapper.toDto(user);

    }

    @Override
    public boolean deleteById(int id) {
        Users user = usersRepository.findById(id).get();
        if (user == null) {
            return false;
        }
        usersRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean insert(UserDto userDto) {
        Users user = userMapper.fromDto(userDto);
        Users newUser = usersRepository.save(user);
        if (newUser == null) {
            return false;
        }
        return true;
    }

    @Override
    public UserDto update(UserDto userDto) {
        Users users = userMapper.fromDto(userDto);
        Users userForUpdate = usersRepository.findById(users.getId()).get();
        if (userForUpdate == null) {
            return null;
        }
        userForUpdate = users;
        Users updatedUser = usersRepository.save(userForUpdate);
        return userMapper.toDto(updatedUser);

    }
}
