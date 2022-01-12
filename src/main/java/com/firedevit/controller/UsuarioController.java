package com.firedevit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firedevit.model.Usuario;
import com.firedevit.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

	private @Autowired UsuarioRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/in/{userName}")
	public ResponseEntity<Usuario> getByUserName(@PathVariable String nome){
		return repository.findByNomeContainingIgnoreCase(nome).map(resp ->ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
		return ResponseEntity.ok(repository.save(usuario));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario){
		return ResponseEntity.ok(repository.save(usuario));
	}
	
	@DeleteMapping("/deletar/{id}")
	public void deletaUsuario(@PathVariable long id) {
		repository.deleteById(id);
	}
}
