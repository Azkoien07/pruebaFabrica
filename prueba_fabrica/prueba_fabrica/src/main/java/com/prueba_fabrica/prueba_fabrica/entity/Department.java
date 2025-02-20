package com.prueba_fabrica.prueba_fabrica.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

// Tabla Departamento
@Entity
@Data
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Columnas de la tabla
    @Column(name = "department_id", nullable = false) 
    private Long departmentId;

    @Column(name = "department_codigo", nullable = false, length = 6)
    private String departmentCodigo;

    @Column(name = "department_nombre", nullable = false, length = 100)
    private String departmentNombre;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora_crea", updatable = false, nullable = false)
    private Date fechaHoraCrea = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora_modifica", nullable = false)
    private Date fechaHoraModifica = new Date();

    @PrePersist
    protected void onCreate() {
    fechaHoraCrea = new Date();
    fechaHoraModifica = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
    fechaHoraModifica = new Date();
    }

    // Constructor Vacio
    public Department(){

    }

    // Constructor con párametros
    public Department(String departmentCodigo, String departmentNombre) {
        this.departmentCodigo = departmentCodigo;
        this.departmentNombre = departmentNombre;
    }
}