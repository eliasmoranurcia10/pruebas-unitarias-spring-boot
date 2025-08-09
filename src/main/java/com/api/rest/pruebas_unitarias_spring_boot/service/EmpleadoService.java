package com.api.rest.pruebas_unitarias_spring_boot.service;

import com.api.rest.pruebas_unitarias_spring_boot.model.Empleado;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface EmpleadoService {
    Empleado saveEmpleado(Empleado empleado);
    List<Empleado> getAllEmpleados();
    Optional<Empleado> getEmpleadoById(long id);
    Empleado updateEmpleado(Empleado empleadoActualizado);
    void deleteEmpleado(long id);
}
