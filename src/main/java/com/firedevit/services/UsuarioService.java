package com.firedevit.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firedevit.model.Usuario;
import com.firedevit.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private @Autowired UsuarioRepository repository;
	
	public Optional<Usuario> atualizaDados(Usuario usuario){
		return repository.findById(usuario.getIdUsuario()).map(resp->{
			resp.setNome(usuario.getNome());
			resp.setEmail(usuario.getEmail());
			resp.setSenha(usuario.getSenha());
			resp.setUserName(usuario.getUserName());
			return Optional.ofNullable(repository.save(resp));
		}).orElseGet(()->{
			return Optional.empty();
		});
	}
	
	public Optional<Object> verificaExistente(Usuario usuario){
		return repository.findByEmail(usuario.getEmail()).map(usuarioExistente ->{
			return Optional.empty();
		}).orElseGet(()->{
			usuario.setSenha(usuario.getSenha());
			return Optional.ofNullable(repository.save(usuario));
		});
	}
}
