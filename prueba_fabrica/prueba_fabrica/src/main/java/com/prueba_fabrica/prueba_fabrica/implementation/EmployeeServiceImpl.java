package com.prueba_fabrica.prueba_fabrica.implementation;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.prueba_fabrica.prueba_fabrica.entity.Employee;

@Service
public interface EmployeeServiceImpl {

    // Metodos Crud
    public List<Employee>getAllEmployees();

    public Optional<Employee> getEmployeeById(long id);

    Employee saveEmployee(Employee e);

    Optional <Employee> updateEmployee(Long id, Employee employeeDetails);

    public void deleteEmployee(long id);
}