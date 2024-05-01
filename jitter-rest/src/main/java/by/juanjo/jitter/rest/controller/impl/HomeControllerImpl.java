package by.juanjo.jitter.rest.controller.impl;

import by.juanjo.jitter.rest.controller.HomeController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllerImpl implements HomeController {

  @Override
  @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
  public String serveSPA() {
    return "index.html";
  }
}
