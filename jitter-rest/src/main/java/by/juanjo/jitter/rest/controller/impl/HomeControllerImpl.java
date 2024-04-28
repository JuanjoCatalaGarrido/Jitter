package by.juanjo.jitter.rest.controller.impl;

import by.juanjo.jitter.rest.controller.HomeController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class HomeControllerImpl implements HomeController {

  @Override
  @GetMapping
  public String serveSPA() {
    return "index";
  }
}
