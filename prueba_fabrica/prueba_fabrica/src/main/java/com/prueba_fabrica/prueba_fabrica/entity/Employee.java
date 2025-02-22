package com.prueba_fabrica.prueba_fabrica.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

// Tabla empleados
@Entity
@Data
@Table(name = "employees")

public class Employee {
    public static enum DocumentoTipo {
        RC,
        TI,
        CC,
        CE
    }
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Columnas de la Tabla
    @Column(name = "employees_id", nullable = false)
    private Long employeesId;

    @Enumerated(EnumType.STRING)
    @Column(name = "documento_tipo", nullable = false, length = 2)
    private DocumentoTipo documentoTipo;

    @Column(name = "documento_numero", nullable = false, length = 20)
    private String documentoNumero;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", length = 100)
    private String apellidos;

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "ciudad", length = 50)
    private String ciudad;

    @Column(name = "direccion", length = 200)
    private String direccion;

    @Column(name = "correo_electronico", length = 100)
    private String correoElectronico;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora_crea", updatable = false)
    private Date fechaHoraCrea = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora_modifica")
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

}