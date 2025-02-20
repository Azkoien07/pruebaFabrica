package com.prueba_fabrica.prueba_fabrica.controller;

import com.prueba_fabrica.prueba_fabrica.entity.Department;
import com.prueba_fabrica.prueba_fabrica.entity.Employee;
import com.prueba_fabrica.prueba_fabrica.service.EmployeeService;
import com.prueba_fabrica.prueba_fabrica.service.DepartmentService; 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empleados")
public class EmployeeViewController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService; 

    // Constructor con ambas dependencias
    public EmployeeViewController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    // Mostrar lista de empleados
    @GetMapping
    public String mostrarEmpleados(Model model) {
        List<Employee> empleados = employeeService.getAllEmployees();
        model.addAttribute("empleados", empleados.isEmpty() ? null : empleados);
        return "empleados"; 
    }

    // Mostrar formulario para crear un nuevo empleado
    @GetMapping("/nuevo")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departamentos", departmentService.getAllDepartments()); // Agregar departamentos al modelo
        return "crear-empleado"; 
    }

    // Guardar nuevo empleado
    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute Employee employee, @RequestParam Long departmentId) {
        Optional<Department> departamento = departmentService.getDepartmentById(departmentId);
        departamento.ifPresent(employee::setDepartment);
        employeeService.saveEmployee(employee);
        return "redirect:/empleados";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Optional<Employee> empleado = employeeService.getEmployeeById(id);
        if (empleado.isPresent()) {
            model.addAttribute("employee", empleado.get());
            model.addAttribute("departamentos", departmentService.getAllDepartments()); 
            return "editar-empleado";
        }
        return "redirect:/empleados";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarEmpleado(@PathVariable Long id, @ModelAttribute Employee employee, @RequestParam Long departmentId) {
    try {
        // Obtener el departamento usando el método getDepartmentById
        Optional<Department> optionalDepartment = departmentService.getDepartmentById(departmentId);
        
        if (optionalDepartment.isPresent()) {
            employee.setDepartment(optionalDepartment.get());
            employeeService.updateEmployee(id, employee);
            return "redirect:/empleados";
        } else {
            
            return "redirect:/empleados?error=departmentNotFound";
        }
    } catch (Exception e) {
        // Manejo de errores
        return "redirect:/empleados?error=true";
    }
}

    // Eliminar empleado
    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/empleados";
    }
}
