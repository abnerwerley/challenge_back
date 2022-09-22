package com.firedevit.services;

import com.firedevit.exceptions.RequestException;
import com.firedevit.json.UsuarioForm;
import com.firedevit.model.Usuario;
import com.firedevit.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService service;

    @Mock
    private UsuarioRepository repository;

    public static final Long ID = 1L;

    public static final String NOME = "Joãozinho";

    public static final String NOME_PARA_ATUALIZAR = "João da Silva";

    public static final String USERNAME = "JoaozinhoSoldier";

    public static final String EMAIL = "joao@gmail.com";

    public static final String SENHA = "senhaforte123";

    @Test
    void testAtualizaDados() {
        doReturn(getOptionalUsuario()).when(repository).findById(ID);
        Optional<Usuario> usuarioAtualizado = service.atualizaDados(getUsuarioParaAtualizar());
        assertNotNull(usuarioAtualizado);
        assertEquals(usuarioAtualizado.get().getNome(), getUsuarioParaAtualizar().getNome());
        verify(repository).findById(ID);
    }

    @Test
    void testAtualizaDadosIdInexistente() {
        doThrow(ResourceNotFoundException.class).when(repository).findById(ID);
        Exception exception = assertThrows(Exception.class, () -> service.atualizaDados(getUsuarioParaAtualizar()));
        assertNotNull(exception);
    }

    @Test
    void testAtualizaDadosUsuarioComSenhaCurta() {
        doThrow(RequestException.class).when(repository).findById(ID);
        Exception exception = assertThrows(Exception.class, () -> service.atualizaDados(getUsuarioSenhaCurta()));
        assertNotNull(exception);
    }

    @Test
    void testAtualizaDadosException() {
        doThrow(RequestException.class).when(repository).findById(ID);
        Exception exception = assertThrows(Exception.class, () -> service.atualizaDados(getUsuarioSenhaCurta()));
        assertNotNull(exception);
    }

    private Optional<Usuario> getOptionalUsuario() {
        return Optional.of(Usuario.builder()
                .idUsuario(ID)
                .email(EMAIL)
                .nome(NOME)
                .senha(SENHA)
                .userName(USERNAME)
                .build());
    }

    private Usuario getUsuario() {
        return Usuario.builder()
                .idUsuario(ID)
                .email(EMAIL)
                .nome(NOME)
                .senha(SENHA)
                .userName(USERNAME)
                .build();
    }

    private UsuarioForm getUsuarioParaAtualizar() {
        return UsuarioForm.builder()
                .idUsuario(ID)
                .email(EMAIL)
                .nome(NOME_PARA_ATUALIZAR)
                .senha(SENHA)
                .userName(USERNAME)
                .build();
    }

    private UsuarioForm getUsuarioSenhaCurta() {
        return UsuarioForm.builder()
                .idUsuario(ID)
                .senha("12345")
                .build();
    }
}
