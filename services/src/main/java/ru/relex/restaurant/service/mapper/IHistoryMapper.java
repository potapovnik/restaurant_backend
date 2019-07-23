package ru.relex.restaurant.service.mapper;

import org.mapstruct.Mapper;
import ru.relex.restaurant.db.entity.History;
import ru.relex.restaurant.service.DTO.HistoryDto;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IHistoryMapper {
  HistoryDto toDto(History history);

  History fromDto(HistoryDto historyDto);

  List<HistoryDto> toDto(List<History> historyList);

  List<History> fromDto(List<HistoryDto> historyDtos);
}

