package pl.sii.springtraining.demo.component;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitialController {

  @GetMapping
  public String initialMethod() {
    return "Hello World!";
  }
}
