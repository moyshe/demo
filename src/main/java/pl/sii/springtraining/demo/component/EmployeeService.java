package pl.sii.springtraining.demo.component;

import static org.springframework.util.StringUtils.isEmpty;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sii.springtraining.demo.model.Employee;

@Service
public class EmployeeService {

  private final EmployeeRepository repository;
  public EmployeeService(EmployeeRepository repository) {
    this.repository = repository;
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
    return repository.save(newEmployee);
  }

  @Transactional(readOnly = true)
  public Employee update(Employee updatedEmployee) {
    Employee myEmployee = repository.getOne(updatedEmployee.getId());
    myEmployee.setName(updatedEmployee.getName());
    myEmployee.setDepartment(updatedEmployee.getDepartment());
    return repository.save(myEmployee);
  }

  @Transactional(rollbackFor = RuntimeException.class)
  public void delete(Employee employee) {
    repository.delete(employee);
    throw new RuntimeException("Failure");
  }
}
