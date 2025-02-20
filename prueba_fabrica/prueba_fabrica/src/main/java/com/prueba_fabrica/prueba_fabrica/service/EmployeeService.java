package com.prueba_fabrica.prueba_fabrica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prueba_fabrica.prueba_fabrica.entity.Employee;
import com.prueba_fabrica.prueba_fabrica.repository.EmployeeRepository;

import java.util.Date;
import java.util.List;
import com.prueba_fabrica.prueba_fabrica.implementation.EmployeeServiceImpl;
import java.util.Optional;


@Service
public class EmployeeService implements EmployeeServiceImpl {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Listar 
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Por ID
    @Override
    public Optional <Employee> getEmployeeById(long id) {
        return employeeRepository.findById(id);
    }

    // Guardar
    @Override
    public Employee saveEmployee(Employee employee) {
    return employeeRepository.save(employee);
    }

    // Actualizar
    @Override
    public Optional<Employee> updateEmployee(Long id, Employee employeeDetails) {
    return employeeRepository.findById(id).map(employee -> {
        employee.setDocumentoTipo(employeeDetails.getDocumentoTipo());
        employee.setDocumentoNumero(employeeDetails.getDocumentoNumero());
        employee.setNombres(employeeDetails.getNombres());
        employee.setApellidos(employeeDetails.getApellidos());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setCiudad(employeeDetails.getCiudad());
        employee.setDireccion(employeeDetails.getDireccion());
        employee.setCorreoElectronico(employeeDetails.getCorreoElectronico());
        employee.setTelefono(employeeDetails.getTelefono());
        employee.setFechaHoraModifica(new Date());

        Employee updatedEmployee = employeeRepository.save(employee);
        return Optional.of(updatedEmployee);  // Envolver en Optional
    }).orElse(Optional.empty());
}


    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }
}
