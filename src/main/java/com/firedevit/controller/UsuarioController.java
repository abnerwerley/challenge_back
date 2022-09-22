package com.firedevit.controller;

import com.firedevit.json.UsuarioForm;
import com.firedevit.json.UsuarioResponse;
import com.firedevit.model.Usuario;
import com.firedevit.repository.UsuarioRepository;
import com.firedevit.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    private @Autowired UsuarioRepository repository;
    private @Autowired UsuarioService service;


    @GetMapping("/")
    public List<Usuario> getAll() {
        return service.getAllUsuarios();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<UsuarioResponse> getById(@PathVariable long id) {
        return service.getuserById(id);
    }

    @GetMapping("/in/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<UsuarioResponse> getByNome(@PathVariable String nome) {
        return service.getUserByNome(nome);
    }

    @GetMapping("/lista/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> getUsuariosByName(@PathVariable String nome) {
        return service.getUsuariosByNome(nome);
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void cadastrar(@RequestBody UsuarioForm usuario) {
        service.cadastraUsuario(usuario);
    }

    @PutMapping("/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public Optional<UsuarioResponse> atualizar(@RequestBody UsuarioForm usuario) {
        return service.atualizaDados(usuario);
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaUsuario(@PathVariable long id) {
        service.deletarUsuarioById(id);
    }
}
