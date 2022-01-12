package com.firedevit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import com.firedevit.model.Usuario;
import com.firedevit.repository.UsuarioRepository;
import com.firedevit.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

	private @Autowired UsuarioRepository repository;
	private @Autowired UsuarioService service;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> GetAll(){
		List<Usuario> lista = repository.findAll();

		if (lista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(lista);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp)).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Id inexistente, insira um id válido");
		});
	}
	
	@GetMapping("/in/{nome}")
	public ResponseEntity<Usuario> getByNome(@PathVariable String nome){
		return repository.findByNomeContainingIgnoreCase(nome).map(resp ->ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/lista/{nome}")
	public ResponseEntity<List<Usuario>> getUsuariosByName(@PathVariable String nome){
		List<Usuario> lista = repository.findUsersByNomeContainingIgnoreCase(nome);

		if (lista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(lista);
		}
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Object> cadastrar(@RequestBody Usuario usuario){
		return service.verificaExistente(usuario).map(resp -> ResponseEntity.status(201).body(resp))
				.orElseThrow(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"Esse e-mail já foi cadastrado, tente outro.");
				});
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario){
		return service.atualizaDados(usuario).map(resp -> ResponseEntity.status(201).body(resp))
				.orElseThrow(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Necessário um id válido para alterar.");
				});
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Object> deletaUsuario(@PathVariable long id) {
				return repository.findById(id).map(resp -> { //acha usuario pelo id
					repository.deleteById(id); //deleta usuario com o id inserido
					return ResponseEntity.status(200).build(); //status 200, deletado
				}).orElse(ResponseEntity.status(400).build()); //status 400, bad request
	}
}
