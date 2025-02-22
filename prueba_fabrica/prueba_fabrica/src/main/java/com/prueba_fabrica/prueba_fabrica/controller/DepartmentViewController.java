package com.prueba_fabrica.prueba_fabrica.controller;

import com.prueba_fabrica.prueba_fabrica.entity.Department;
import com.prueba_fabrica.prueba_fabrica.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import com.prueba_fabrica.prueba_fabrica.service.DepartmentExcelService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Controller
@RequestMapping("/departamentos")
public class DepartmentViewController {

    private final DepartmentService departmentService;

    // Inyección de dependencias
    @Autowired
    public DepartmentViewController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    private DepartmentExcelService departmentExcelService;

    // Mostrar lista de departamentos
    @GetMapping
    public String mostrarDepartamentos(Model model) {
        List<Department> departamentos = departmentService.getAllDepartments();
        model.addAttribute("departamentos", departamentos.isEmpty() ? null : departamentos);
        return "departamentos"; 
    }

    // Mostrar formulario para crear un nuevo departamento
    @GetMapping("/nuevo")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("department", new Department());
        return "crear-departamento"; 
    }

    // Guardar nuevo departamento
    @PostMapping("/guardar")
    public String guardarDepartamento(@ModelAttribute Department department) {
        departmentService.saveDepartment(department);
        return "redirect:/departamentos";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Optional<Department> departamento = departmentService.getDepartmentById(id);
        if (departamento.isPresent()) {
            model.addAttribute("department", departamento.get());
            return "editar-departamento";
        }
        return "redirect:/departamentos";
    }

    // Actualizar departamento
    @PostMapping("/actualizar/{id}")
    public String actualizarDepartamento(@PathVariable Long id, @ModelAttribute Department department) {
        try {
            departmentService.updateDepartment(id, department);
            return "redirect:/departamentos";
        } catch (Exception e) {
            // Manejo de errores
            return "redirect:/departamentos?error=true";
        }
    }

    // Eliminar departamento
    @GetMapping("/eliminar/{id}")
    public String eliminarDepartamento(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departamentos";
    }


    // Reporte Excel
    @GetMapping("/reporte-excel/{id}")
    public ResponseEntity<byte[]> descargarReporteExcel(@PathVariable Long id) {
    try {
        ByteArrayInputStream excelStream = departmentExcelService.generateExcelReport(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=departamento_" + id + ".xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelStream.readAllBytes());
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}