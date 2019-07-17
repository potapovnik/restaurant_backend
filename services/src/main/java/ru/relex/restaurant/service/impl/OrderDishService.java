package ru.relex.restaurant.service.impl;

import org.springframework.stereotype.Service;
import ru.relex.restaurant.db.JpaRepository.OrderDishRepository;
import ru.relex.restaurant.db.entity.OrderDish;
import ru.relex.restaurant.service.DTO.OrderDishDto;
import ru.relex.restaurant.service.IOrderDishService;
import ru.relex.restaurant.service.mapper.IOrderDishMapper;

import java.util.List;

@Service
public class OrderDishService implements IOrderDishService {
    private final IOrderDishMapper orderDishMapper;
    private final OrderDishRepository orderDishRepository;

    public OrderDishService(IOrderDishMapper orderDishMapper, OrderDishRepository orderDishRepository) {
        this.orderDishMapper = orderDishMapper;
        this.orderDishRepository = orderDishRepository;
    }

    @Override
    public boolean insert(List<OrderDishDto> orderDishesDto) {
        List<OrderDish> orderDishList = orderDishMapper.fromDto(orderDishesDto);
        boolean isOk = true;
        OrderDish savedOrderDish;
        for (OrderDish orderDish : orderDishList) {
            savedOrderDish = orderDishRepository.save(orderDish);
            if (savedOrderDish == null) {
                return false;
            }
        }
        return true;
    }
}
