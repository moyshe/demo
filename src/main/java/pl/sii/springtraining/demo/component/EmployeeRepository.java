package pl.sii.springtraining.demo.component;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.sii.springtraining.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  List<Employee> findAllByName(String name);
  List<Employee> findAllByDepartment(String department);
  List<Employee> findAllByNameAndDepartment(String name, String department);
}
