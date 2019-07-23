package ru.relex.restaurant.service;

import ru.relex.restaurant.service.DTO.HistoryDto;

public interface IHistoryService {
  public HistoryDto nextStatus(HistoryDto historyDto);
}
