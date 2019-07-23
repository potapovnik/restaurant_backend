package ru.relex.restaurant.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.relex.restaurant.db.JpaRepository.HistoryRepository;
import ru.relex.restaurant.db.entity.History;
import ru.relex.restaurant.service.DTO.HistoryDto;
import ru.relex.restaurant.service.IHistoryService;
import ru.relex.restaurant.service.mapper.IHistoryMapper;
import ru.relex.restaurant.service.utils.StatusEnum;

import java.util.Date;

@Service
public class HistoryService implements IHistoryService {
  private final IHistoryMapper historyMapper;
  private final HistoryRepository historyRepository;

  public HistoryService(IHistoryMapper historyMapper, HistoryRepository historyRepository) {
    this.historyMapper = historyMapper;
    this.historyRepository = historyRepository;
  }

  @Override
  @Transactional
  public HistoryDto nextStatus(HistoryDto historyDto) {
    History historyForSave = historyMapper.fromDto(historyDto);
    historyForSave.setTime(new Date());
    History savedHistory = historyRepository.save(historyForSave);
    if (historyDto.getCook_id()!= null){
      historyForSave.setUser_id(historyDto.getCook_id());
      historyForSave.setTime(new Date());
      historyForSave.setStatus_id(StatusEnum.CookAssigned.getStatusId());
      savedHistory = historyRepository.save(historyForSave);
    }
    return historyMapper.toDto(savedHistory);
  }
}
