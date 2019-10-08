package ru.relex.restaurant.web.api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class GeneralSocket {

  @MessageMapping("/hello")
  @SendTo("restaurant/goodbay")
  public Message message(){
    return new Message("hi bro");
  }
}
