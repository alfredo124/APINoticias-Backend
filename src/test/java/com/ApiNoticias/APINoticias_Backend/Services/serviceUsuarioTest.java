package com.ApiNoticias.APINoticias_Backend.Services;

import com.ApiNoticias.APINoticias_Backend.Modelo.Usuario;
import com.ApiNoticias.APINoticias_Backend.Repositorio.UsuarioRepositorio;
import com.ApiNoticias.APINoticias_Backend.Services.serviceUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Clase de pruebas unitarias para la clase {@link serviceUsuario}.
 * Esta clase verifica los diferentes casos de uso relacionados con la
 * obtención, creación y modificación de usuarios utilizando mocks.
 */
class serviceUsuarioTest {

    @Mock
    private UsuarioRepositorio repositorio;

    @InjectMocks
    private serviceUsuario service;

    /**
     * Configura el entorno de pruebas y inicializa los mocks antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Caso 1: Verifica que se obtengan todos los usuarios exitosamente.
     */
    @Test
    void testGetallUsuarioSuccessful() {
        Usuario usuario = new Usuario(1L, "Juan", "Pérez", "Gómez", "juan@example.com", "123456789", "Tarjeta", "123456789012", "qwe");
        when(repositorio.getAllUsuarios()).thenReturn(Arrays.asList(usuario));

        List<Usuario> usuarios = service.getallUsuario();

        assertEquals(1, usuarios.size());
        assertEquals("Juan", usuarios.get(0).getName());
        verify(repositorio, times(1)).getAllUsuarios();
    }

    /**
     * Caso 2: Verifica que se obtenga una lista vacía cuando no hay usuarios en la base de datos.
     */
    @Test
    void testGetallUsuarioEmptyResult() {
        when(repositorio.getAllUsuarios()).thenReturn(Collections.emptyList());

        List<Usuario> usuarios = service.getallUsuario();

        assertTrue(usuarios.isEmpty());
        verify(repositorio, times(1)).getAllUsuarios();
    }

    /**
     * Caso 3: Verifica la creación exitosa de un usuario.
     */
    @Test
    void testSetUsuarioSuccessful() {
        Usuario newUsuario = new Usuario("Juan", "Pérez", "Gómez", "juan@example.com", "123456789", "Tarjeta", "123456789012", "qwe");
        when(repositorio.createUsuario(newUsuario)).thenReturn(1L);

        long id = service.setUsuario(newUsuario);

        assertEquals(1L, id);
        verify(repositorio, times(1)).createUsuario(newUsuario);
    }

    /**
     * Caso 4: Verifica la creación de un usuario cuando los valores son nulos.
     */
    @Test
    void testSetUsuarioWithNullValues() {
        Usuario newUsuario = new Usuario(null, null, null, null, null, null, null, null);
        when(repositorio.createUsuario(newUsuario)).thenReturn(2L);

        long id = service.setUsuario(newUsuario);

        assertEquals(2L, id);
        verify(repositorio, times(1)).createUsuario(newUsuario);
    }

    /**
     * Caso 5: Verifica la modificación exitosa de un usuario.
     */
    @Test
    void testUpdateUsuarioSuccessful() {
        Usuario usuarioModificado = new Usuario(1L, "Carlos", "Gómez", "López", "carlos@example.com", "987654321", "Paypal", "098765432109", "qwe");

        assertDoesNotThrow(() -> service.updateUsuarui(usuarioModificado));
        verify(repositorio, times(1)).modificarUsuario(usuarioModificado);
    }

    /**
     * Caso 6: Verifica la modificación de un usuario con valores parciales.
     */
    @Test
    void testUpdateUsuarioWithPartialValues() {
        Usuario usuarioModificado = new Usuario(1L, "Carlos", null, null, "carlos@example.com", null, null, null, "qwe");

        assertDoesNotThrow(() -> service.updateUsuarui(usuarioModificado));
        verify(repositorio, times(1)).modificarUsuario(usuarioModificado);
    }

    /**
     * Caso 7: Verifica que se lanza una excepción cuando ocurre un error al obtener los usuarios.
     */
    @Test
    void testGetallUsuarioThrowsError() {
        when(repositorio.getAllUsuarios()).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> service.getallUsuario());

        assertEquals("Database error", exception.getMessage());
        verify(repositorio, times(1)).getAllUsuarios();
    }

    /**
     * Caso 8: Verifica que se lanza una excepción cuando ocurre un error al crear un usuario.
     */
    @Test
    void testSetUsuarioThrowsError() {
        Usuario newUsuario = new Usuario("Error", "Test", null, null, null, null, null, null);
        when(repositorio.createUsuario(newUsuario)).thenThrow(new RuntimeException("Insert failed"));

        Exception exception = assertThrows(RuntimeException.class, () -> service.setUsuario(newUsuario));

        assertEquals("Insert failed", exception.getMessage());
        verify(repositorio, times(1)).createUsuario(newUsuario);
    }
}
