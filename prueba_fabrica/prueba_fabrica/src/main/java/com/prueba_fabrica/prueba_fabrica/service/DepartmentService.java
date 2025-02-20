package com.prueba_fabrica.prueba_fabrica.service;

import java.util.List;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prueba_fabrica.prueba_fabrica.entity.Department;
import com.prueba_fabrica.prueba_fabrica.repository.DepartmentRepository;
import com.prueba_fabrica.prueba_fabrica.implementation.DepartmentServiceImpl;


@Service
public class DepartmentService implements DepartmentServiceImpl {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department saveDepartment(Department department) {
    return departmentRepository.save(department);
    }
    
    @Override
    public Optional<Department> updateDepartment(Long id, Department departmentDetails) {
        return departmentRepository.findById(id).map(department -> {
            department.setDepartmentNombre(departmentDetails.getDepartmentNombre()); 
            department.setDepartmentCodigo(departmentDetails.getDepartmentCodigo()); 
            department.setFechaHoraModifica(new Date()); 
            return departmentRepository.save(department);
        });
    }
    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
    
}