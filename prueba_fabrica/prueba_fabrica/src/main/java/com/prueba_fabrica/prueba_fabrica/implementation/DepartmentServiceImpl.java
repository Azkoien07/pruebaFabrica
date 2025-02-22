package com.prueba_fabrica.prueba_fabrica.implementation;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.prueba_fabrica.prueba_fabrica.entity.Department;

@Service
public interface DepartmentServiceImpl {
    // Métodos Crud
    public List<Department> getAllDepartments();

    public Optional<Department> getDepartmentById(Long id);

    public Department saveDepartment(Department department);

    public Optional <Department> updateDepartment (Long id, Department departmentDetails);

    public void deleteDepartment(Long id);
} 