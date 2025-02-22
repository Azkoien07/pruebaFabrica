package com.prueba_fabrica.prueba_fabrica.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.prueba_fabrica.prueba_fabrica.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository <Department, Long> {

    
} 