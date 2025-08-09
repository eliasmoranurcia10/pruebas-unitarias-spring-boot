package com.api.rest.pruebas_unitarias_spring_boot.service;

import com.api.rest.pruebas_unitarias_spring_boot.repository.EmpleadoRepository;
import com.api.rest.pruebas_unitarias_spring_boot.service.impl.EmpleadoServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// Cargar extensiones de Unit
@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTests {

    // Mock crea un simulacro
    @Mock
    private EmpleadoRepository empleadoRepository;

    // InjectMocks crea una instancia de la clase e inyecta los simulacros que se crean con el Mock
    @InjectMocks
    private EmpleadoServiceImpl empleadoService;
}
