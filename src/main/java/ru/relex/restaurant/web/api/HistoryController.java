package ru.relex.restaurant.web.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.restaurant.service.DTO.HistoryDto;
import ru.relex.restaurant.service.IHistoryService;

@RestController
@RequestMapping(path = "/history",
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class HistoryController {
  private final IHistoryService historyService;

  public HistoryController(IHistoryService historyService) {
    this.historyService = historyService;
  }

  @PostMapping()
  public HistoryDto nextStatus(@RequestBody HistoryDto history){
    return historyService.nextStatus(history);
  }
}
