package com.api.rest.pruebas_unitarias_spring_boot.repository;

import com.api.rest.pruebas_unitarias_spring_boot.model.Empleado;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

// Sirve para probar componentes solamente de la capa de persistencia, solo buscará las
// anotaciones Entity y a los Respositoy
@DataJpaTest
public class EmpleadoRepositoryTests {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    private Empleado empleado;

    //Antes de cada elemento
    @BeforeEach
    void setup() {
        empleado = Empleado.builder()
                .nombre("Elias")
                .apellido("Moran")
                .email("elias@gmail.com")
                .build();
    }

    @DisplayName("Test para guardar un empleado")
    @Test
    void testGuardarEmpleado() {
        // Metodología BDD:
        // given - dado o condición previa o configuración
        // Dado que a este empleado
        Empleado empleado1 = Empleado.builder()
                .nombre("Pepe")
                .apellido("Lopez")
                .email("p12@gmail.com")
                .build();

        // when - acción o el comportamiento que vamos a probar
        //Cuando yo llame al metodo save del repositorio y guarde al empleado
        Empleado empleadoGuardado = empleadoRepository.save(empleado1);

        // then - verificar las salidas
        // Entonces el valor no es nulo y el id es mayor que 0
        assertThat(empleadoGuardado).isNotNull();
        assertThat(empleadoGuardado.getId()).isGreaterThan(0);

    }

    @DisplayName("Test para listar a los empleados")
    @Test
    void testListarEmpleados() {
        // Given
        // Dado que los empleados se han guardado
        Empleado empleado1 = Empleado.builder()
                .nombre("Julen")
                .apellido("Oliva")
                .email("j2@gmail.com")
                .build();
        empleadoRepository.save(empleado1);
        empleadoRepository.save(empleado);

        // When
        // Cuando listo los empleado
        List<Empleado> listEmpleados = empleadoRepository.findAll();

        // Then
        // Entonces la lista es no nula y el tamaño de la lista es de 2
        assertThat(listEmpleados).isNotNull();
        assertThat(listEmpleados.size()).isEqualTo(2);
    }

    @DisplayName("Test para obtener un empleado por Id")
     @Test
    void testObtenerEmpleadoPorId() {
        // Given - Dado que guardamos un empleado
        empleadoRepository.save(empleado);

        // When - Cuando buscamos el empleado por el id
        Empleado empleadoBD = empleadoRepository.findById(empleado.getId()).get();

        // Then - Entonces el empleadoBD debe ser no nulo.
        assertThat(empleadoBD).isNotNull();
    }

    @DisplayName("Test para actualizar un empleado")
    @Test
    void testActualizarEmpleado() {
        // Given - Dado que tengo un empleado en el repositorio
        empleadoRepository.save(empleado);
        // When - Cuando obtendo el empleado por el id, y modifico sus atributos y los guardo
        Empleado empleadoGuardado = empleadoRepository.findById(empleado.getId()).get();

        empleadoGuardado.setEmail("c34@gmail.com");
        empleadoGuardado.setNombre("Juan");
        empleadoGuardado.setApellido("Urcia");
        Empleado empleadoActualizado = empleadoRepository.save(empleadoGuardado);

        // Then - El empleado cambió sus datos
        assertThat(empleadoActualizado.getEmail()).isEqualTo("c34@gmail.com");
        assertThat(empleadoActualizado.getNombre()).isEqualTo("Juan");
    }

    @DisplayName("Tests para eliminar un empleado")
    @Test
    void testEliminarEmpleado() {
        // Given - Dado que tenemos a un empleado
        empleadoRepository.save(empleado);

        // When - Cuando eliminamos al empleado y lo buscamos por su id
        empleadoRepository.deleteById(empleado.getId());
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(empleado.getId());

        // Then - Entonces nos resultará el objeto vacio
        assertThat(empleadoOptional).isEmpty();

    }
}
