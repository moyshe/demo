package pl.sii.springtraining.demo.component;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sii.springtraining.demo.model.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  @GetMapping("/info")
  public String getInfo() {
    return "This is an endpoint to manipulate employees data.";
  }

  @GetMapping("/{id}")
  public Employee getEmployeeById(@PathVariable Long id) {
    return new Employee(id, "Test Name", "Test Department");
  }

  @GetMapping("")
  public Employee getEmployee(@RequestParam String name, @RequestParam String department) {
    return new Employee(0L, name, department);
  }
}
