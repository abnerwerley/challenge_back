package com.firedevit.repository;

import com.firedevit.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByNomeContainingIgnoreCase(String nome);

    public List<Usuario> findUsersByNomeContainingIgnoreCase(String name);

    public Optional<Usuario> findByUserNameContainingIgnoreCase(String userName);

    public Optional<Usuario> findByEmail(String email);

}
