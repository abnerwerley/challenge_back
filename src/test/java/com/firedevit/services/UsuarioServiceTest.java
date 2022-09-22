package com.firedevit.services;

import com.firedevit.exceptions.RequestException;
import com.firedevit.json.UsuarioForm;
import com.firedevit.json.UsuarioResponse;
import com.firedevit.model.Usuario;
import com.firedevit.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import javax.validation.ConstraintViolationException;
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

    private static final String EMAIL_SEM_ARROBA = "joaogmail.com";

    public static final String EMAIL_SEM_NOME = "@gmail.com";

    public static final String EMAIL_SEM_PONTO = "joao@gmail";

    public static final String SENHA = "senhaforte123";

    private static final String SENHA_CURTA = "1234567";

    public static final String ERRO_AO_ATUALIZAR_USUARIO_MENSAGEM = "Erro ao atualizar dados de usuário.";

    public static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado.";

    @Test
    void testAtualizaDados() {
        doReturn(getOptionalUsuario()).when(repository).findById(ID);
        Optional<UsuarioResponse> usuarioAtualizado = service.atualizaDados(getUsuarioParaAtualizar());

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
        assertEquals(ERRO_AO_ATUALIZAR_USUARIO_MENSAGEM, exception.getMessage());
    }

    @Test
    void testAtualizaDadosEmailSemArroba() {
        doThrow(ConstraintViolationException.class).when(repository).findById(ID);
        Exception exception = assertThrows(Exception.class, () -> service.atualizaDados(getUsuarioComEmailSemArroba()));
        assertNotNull(exception);
        assertEquals(ERRO_AO_ATUALIZAR_USUARIO_MENSAGEM, exception.getMessage());
    }

    @Test
    void testAtualizaDadosEmailSemNome() {
        doThrow(ConstraintViolationException.class).when(repository).findById(ID);
        Exception exception = assertThrows(Exception.class, () -> service.atualizaDados(getUsuarioComEmailSemNome()));
        assertNotNull(exception);
        assertEquals(ERRO_AO_ATUALIZAR_USUARIO_MENSAGEM, exception.getMessage());
    }

    @Test
    void testAtualizaDadosEmailSemPontoCom() {
        doThrow(ConstraintViolationException.class).when(repository).findById(ID);
        Exception exception = assertThrows(Exception.class, () -> service.atualizaDados(getUsuarioComEmailSemPontoCom()));
        assertNotNull(exception);
        assertEquals(ERRO_AO_ATUALIZAR_USUARIO_MENSAGEM, exception.getMessage());
    }

    @Test
    void testGetuserById() {
        doReturn(getOptionalUsuario()).when(repository).findById(ID);
        Optional<UsuarioResponse> usuario = service.getuserById(ID);

        assertNotNull(usuario);
        verify(repository).findById(ID);
    }

    @Test
    void testGetuserByIdInexistente() {
        doThrow(ResourceNotFoundException.class).when(repository).findById(ID);
        Exception exception = assertThrows(Exception.class, () -> service.getuserById(ID));

        assertNotNull(exception);
    }

    @Test
    void testGetUsraByIdException() {
        doThrow(RequestException.class).when(repository).findById(ID);
        Exception exception = assertThrows(Exception.class, () -> service.getuserById(ID));

        assertNotNull(exception);
        assertEquals("Erro ao buscar usuário por id.", exception.getMessage());
    }

    @Test
    void testGetUserByNome() {
        doReturn(getOptionalUsuario()).when(repository).findByNomeContainingIgnoreCase(NOME);
        Optional<UsuarioResponse> usuarioResponse = service.getUserByNome(NOME);

        assertNotNull(usuarioResponse);
        verify(repository).findByNomeContainingIgnoreCase(NOME);
    }

    @Test
    void testGetUserByNomResourceNotFoundException() {
        Exception exception = assertThrows(Exception.class, () -> service.getUserByNome(NOME));
        assertNotNull(exception);
        //todo dois logs e ResourceNotFoundExcepiton não sendo lançada
    }

    @Test
    void testGetUserByNomeException(){
        Exception exception = assertThrows(RequestException.class, () -> service.getUserByNome(NOME));
        assertNotNull(exception);
        assertEquals("Erro ao buscar usuário por nome.", exception.getMessage());
    }

    @Test
    void testDeletarUsuarioById(){
        doReturn(getOptionalUsuario()).when(repository).findById(ID);
        service.deletarUsuarioById(ID);
    }

    @Test
    void testDeletarUsuarioByIdResourceNotFoundException(){
        doThrow(ResourceNotFoundException.class).when(repository).findById(ID);
        Exception exception = assertThrows(Exception.class, () -> service.deletarUsuarioById(ID));
        assertNotNull(exception);
    }

    @Test
    void testDeletarUsuarioByIdException(){
        doThrow(RequestException.class).when(repository).findById(ID);
        Exception exception = assertThrows(Exception.class, () -> service.deletarUsuarioById(ID));

        assertNotNull(exception);
        assertEquals("Erro ao deletar usuário.", exception.getMessage());
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

    private UsuarioForm getUsuarioForm() {
        return UsuarioForm.builder()
                .idUsuario(ID)
                .nome(NOME)
                .email(EMAIL)
                .userName(USERNAME)
                .senha(SENHA)
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
                .senha(SENHA_CURTA)
                .build();
    }

    private UsuarioForm getUsuarioComEmailSemArroba() {
        return UsuarioForm.builder()
                .idUsuario(ID)
                .email(EMAIL_SEM_ARROBA)
                .build();
    }

    private UsuarioForm getUsuarioComEmailSemNome() {
        return UsuarioForm.builder()
                .idUsuario(ID)
                .email(EMAIL_SEM_NOME)
                .build();
    }

    private UsuarioForm getUsuarioComEmailSemPontoCom() {
        return UsuarioForm.builder()
                .idUsuario(ID)
                .email(EMAIL_SEM_PONTO)
                .build();
    }
}
