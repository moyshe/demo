package pl.sii.springtraining.demo.component;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sii.springtraining.demo.model.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  private final EmployeeService service;
  public EmployeeController(EmployeeService service) {
    this.service = service;
  }

  @GetMapping("/info")
  public String getInfo() {
    return "This is an endpoint to manipulate employees data.";
  }

  @GetMapping("/{id}")
  public Employee getById(@PathVariable Long id) {
    return service.getById(id);
  }

  @GetMapping("")
  public List<Employee> find(@RequestParam(required = false) String name, @RequestParam(required = false) String department) {
    return service.find(name, department);
  }

  @PostMapping("")
  public Employee create(@RequestBody Employee employee) {
    return service.create(employee);
  }

  @PutMapping("")
  public Employee update(@RequestBody Employee employee) {
    return service.update(employee);
  }

  @DeleteMapping("")
  public HttpStatus delete(@RequestBody Employee employee) {
    service.delete(employee);
    return HttpStatus.OK;
  }
}
