package ru.relex.restaurant.service.impl;

import org.springframework.stereotype.Service;
import ru.relex.restaurant.db.JpaRepository.IngredientPartRepository;
import ru.relex.restaurant.service.IIngredientPartService;
import ru.relex.restaurant.service.DTO.IngredientDto;
import ru.relex.restaurant.service.DTO.IngredientPartDto;
import ru.relex.restaurant.service.mapper.IIngredientPartMapstruct;

import java.util.List;

@Service
public class IngredientPartService implements IIngredientPartService {
    private final IIngredientPartMapstruct mapstruct;
    private final IngredientPartRepository repository;

    public IngredientPartService(IIngredientPartMapstruct mapstruct, IngredientPartRepository repository) {
        this.mapstruct = mapstruct;
        this.repository = repository;
    }

    @Override
    public List<IngredientPartDto> listIngredientParts() {
        return mapstruct.toDto(repository.findAll());
    }

    @Override
    public IngredientPartDto findOneById(int id) {
        return mapstruct.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public void createIngredientPart(IngredientPartDto dto) {
        repository.save(mapstruct.fromDto(dto));
    }

    @Override
    public void updateIngredientPart(IngredientPartDto dto) {

    }

    @Override
    public void deleteIngredientPart(int id) {
        repository.deleteById(id);
    }
}
