package com.firedevit.services;

import com.firedevit.exceptions.ModelException;
import com.firedevit.exceptions.RequestException;
import com.firedevit.json.UsuarioForm;
import com.firedevit.json.UsuarioResponse;
import com.firedevit.json.mapper.UsuarioResponseMapper;
import com.firedevit.model.Usuario;
import com.firedevit.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UsuarioService {

    private @Autowired UsuarioRepository repository;

    public static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado.";

    public Optional<UsuarioResponse> atualizaDados(UsuarioForm usuario) {
        try {
            Optional<Usuario> optionalUsuario = repository.findById(usuario.getIdUsuario());
            if (optionalUsuario.isPresent()) {
                if (usuario.getSenha().length() >= 8) {
                    Usuario usuarioAtualizado = optionalUsuario.get();
                    usuarioAtualizado.setNome(usuario.getNome());
                    usuarioAtualizado.setEmail(usuario.getEmail());
                    usuarioAtualizado.setSenha(usuario.getSenha());
                    usuarioAtualizado.setUserName(usuario.getUserName());
                    return Optional.of(usuarioAtualizado).map(UsuarioResponseMapper::fromEntityToResponse);
                }
                throw new ModelException("A senha deve ser maior que 8 caracteres.");
            }
            throw new ResourceNotFoundException(USUARIO_NAO_ENCONTRADO);
        } catch (ResourceNotFoundException exception) {
            log.error(USUARIO_NAO_ENCONTRADO);
            throw new ResourceNotFoundException(USUARIO_NAO_ENCONTRADO);
        } catch (ModelException exception) {
            log.error("A senha deve ser maior que 8 caracteres.");
            throw new ModelException(exception.getMessage());
        } catch (Exception e) {
            log.error("Erro ao atualizar dados de usuário.");
            throw new RequestException("Erro ao atualizar dados de usuário.");
        }
    }

    public void cadastraUsuario(UsuarioForm usuario) {
        try {
            Optional<Usuario> optionalUsuario = repository.findByEmail(usuario.getEmail());
            if (optionalUsuario.isEmpty()) {
                Usuario usuarioParaCadastrar = Usuario.builder()
                        .idUsuario(usuario.getIdUsuario())
                        .nome(usuario.getNome())
                        .userName(usuario.getUserName())
                        .email(usuario.getEmail())
                        .senha(usuario.getSenha())
                        .build();
                repository.save(usuarioParaCadastrar);
            } else {
                throw new RequestException("Email em uso para outro usuário.");
            }
        } catch (Exception e) {
            log.error("Erro ao cadastrar de usuário.");
            throw new RequestException("Erro ao cadastrar de usuário.");
        }
    }

    public List<Usuario> getAllUsuarios() {
        try {
            List<Usuario> lista = repository.findAll();

            if (lista.isEmpty()) {
                return Collections.emptyList();
            } else {
                return lista;
            }
        } catch (Exception e) {
            log.error("Erro ao buscar todos os usuários.");
            throw new RequestException("Erro ao buscar todos os usuários.");
        }
    }

    public Optional<UsuarioResponse> getuserById(long id) {
        try {
            Optional<Usuario> optionalUsuario = repository.findById(id);
            if (optionalUsuario.isPresent()) {
                return optionalUsuario.map(UsuarioResponseMapper::fromEntityToResponse);
            } else {
                throw new ResourceNotFoundException(USUARIO_NAO_ENCONTRADO);
            }
        } catch (ResourceNotFoundException exception) {
            log.error(USUARIO_NAO_ENCONTRADO);
            throw new ResourceNotFoundException(USUARIO_NAO_ENCONTRADO);
        } catch (Exception e) {
            log.error("Erro ao buscar usuário por id.");
            throw new RequestException("Erro ao buscar usuário por id.");
        }
    }

    public Optional<UsuarioResponse> getUserByNome(String nome) {
        try {
            Optional<Usuario> optionalUsuario = repository.findByNomeContainingIgnoreCase(nome);
            if (optionalUsuario.isPresent()) {
                return optionalUsuario.map(UsuarioResponseMapper::fromEntityToResponse);
            } else {
                throw new ResourceNotFoundException(USUARIO_NAO_ENCONTRADO);
            }
        } catch (ResourceNotFoundException exception) {
            log.error(USUARIO_NAO_ENCONTRADO);
            throw new ResourceNotFoundException(exception.getMessage());
        } catch (Exception e) {
            log.error("Erro ao buscar usuário por nome.");
            throw new RequestException("Erro ao buscar usuário por nome.");
        }

    }

    public List<Usuario> getUsuariosByNome(String nome) {
        try {
            List<Usuario> lista = repository.findUsersByNomeContainingIgnoreCase(nome);

            if (lista.isEmpty()) {
                return Collections.emptyList();
            } else {
                return lista;
            }
        } catch (Exception e) {
            log.error("Erro ao buscar usuários por nome.");
            throw new RequestException("Erro ao buscar usuários por nome.");
        }

    }

    public String deletarUsuarioById(long id) {
        try {
            Optional<Usuario> usuario = repository.findById(id);
            if (usuario.isPresent()) {
                repository.deleteById(id);
                return "Usuário deletado";
            } else {
                throw new ResourceNotFoundException(USUARIO_NAO_ENCONTRADO);
            }
        } catch (ResourceNotFoundException exception) {
            log.error(USUARIO_NAO_ENCONTRADO);
            throw new ResourceNotFoundException(USUARIO_NAO_ENCONTRADO);
        } catch (Exception e) {
            log.error("Erro ao deletar usuário.");
            throw new RequestException("Erro ao deletar usuário.");
        }

    }
}
