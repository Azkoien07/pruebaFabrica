package com.prueba_fabrica.prueba_fabrica.implementation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import com.prueba_fabrica.prueba_fabrica.entity.Department;
@Service
public interface DepartmentExcelServiceImpl {
    ByteArrayInputStream generateExcelReport(List<Department> departments) throws IOException;
}

