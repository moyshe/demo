package pl.sii.springtraining.demo.component;

import static org.springframework.util.StringUtils.isEmpty;

import java.util.List;
import org.springframework.stereotype.Service;
import pl.sii.springtraining.demo.model.Employee;

@Service
public class EmployeeService {

  private final EmployeeRepository repository;
  private final EmailService emailService;
  public EmployeeService(EmployeeRepository repository, EmailService emailService) {
    this.repository = repository;
    this.emailService = emailService;
  }

  public Employee getById(Long id) {
    return repository.getOne(id);
  }

  public List<Employee> find(String name, String department) {
    if (isEmpty(name) && isEmpty(department)) {
      return repository.findAll();
    }
    if (!isEmpty(name)) {
      if (!isEmpty(department)) {
        return repository.findAllByNameAndDepartment(name, department);
      }
      return repository.findAllByName(name);
    }
    return repository.findAllByDepartment(department);
  }

  public Employee create(Employee newEmployee) {
    Employee createdEmployee = repository.save(newEmployee);
    emailService.employeeCreated(createdEmployee.getId(), createdEmployee.getName(), createdEmployee.getDepartment());
    return createdEmployee;
  }

  public Employee update(Employee updatedEmployee) {
    Employee myEmployee = repository.getOne(updatedEmployee.getId());
    myEmployee.setName(updatedEmployee.getName());
    myEmployee.setDepartment(updatedEmployee.getDepartment());
    return repository.save(myEmployee);
  }

  public void delete(Employee employee) {
    repository.delete(employee);
  }
}
