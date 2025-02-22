package com.prueba_fabrica.prueba_fabrica.service;

import com.prueba_fabrica.prueba_fabrica.entity.Department;
import com.prueba_fabrica.prueba_fabrica.repository.DepartmentRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class DepartmentExcelService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentExcelService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public ByteArrayInputStream generateExcelReport(Long departmentId) throws IOException {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);

        if (!departmentOptional.isPresent()) {
            throw new IOException("Departamento no encontrado");
        }

        Department dep = departmentOptional.get();

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Departamento");

            // Crear columnas para los datos
            Row headerRow = sheet.createRow(0);
            String[] columns = { "ID", "Nombre", "Código", "Fecha de Modificación" };
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                CellStyle headerCellStyle = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                headerCellStyle.setFont(font);
                cell.setCellStyle(headerCellStyle);
            }

            // recibir los datos del departamento
            Row row = sheet.createRow(1);
            row.createCell(0).setCellValue(dep.getDepartmentId());
            row.createCell(1).setCellValue(dep.getDepartmentNombre());
            row.createCell(2).setCellValue(dep.getDepartmentCodigo());
            row.createCell(3).setCellValue(dep.getFechaHoraModifica().toString());

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
