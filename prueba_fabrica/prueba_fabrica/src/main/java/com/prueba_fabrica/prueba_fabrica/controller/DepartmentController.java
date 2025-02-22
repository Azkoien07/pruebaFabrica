package com.prueba_fabrica.prueba_fabrica.controller;

import com.prueba_fabrica.prueba_fabrica.entity.Department;
import com.prueba_fabrica.prueba_fabrica.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments") 
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Obtener todos los departamentos
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // Obtener un departamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado con id: " + id));
        return ResponseEntity.ok(department);
    }

    // Crear un nuevo departamento
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    //Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        return departmentService.updateDepartment(id, departmentDetails)
            .map(ResponseEntity::ok) 
            .orElseGet(() -> ResponseEntity.notFound().build()); 
    }
    
    // Eliminar un departamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}