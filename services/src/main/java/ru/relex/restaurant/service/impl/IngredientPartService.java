package ru.relex.restaurant.service.impl;

import org.springframework.stereotype.Service;
import ru.relex.restaurant.db.JpaRepository.IngredientPartRepository;
import ru.relex.restaurant.service.IIngredientPartService;
import ru.relex.restaurant.service.DTO.IngredientPartDto;
import ru.relex.restaurant.service.mapper.IIngredientPartMapper;

import java.util.List;

@Service
public class IngredientPartService implements IIngredientPartService {
    private final IIngredientPartMapper mapper;
    private final IngredientPartRepository repository;

    public IngredientPartService(IIngredientPartMapper mapper, IngredientPartRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<IngredientPartDto> listIngredientParts() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public IngredientPartDto findOneById(int id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public void createIngredientPart(IngredientPartDto dto) {
        repository.save(mapper.fromDto(dto));
    }

    @Override
    public void updateIngredientPart(IngredientPartDto dto) {

    }

    @Override
    public void deleteIngredientPart(int id) {
        repository.deleteById(id);
    }
}
