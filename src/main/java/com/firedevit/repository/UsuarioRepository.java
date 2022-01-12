package com.firedevit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firedevit.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/**
	 * Método que procura na tabela usuario o atributo idUsuario
	 * 
	 * @author Abner
	 * @param idUsuario
	 * @return optional com usuário
	 * 
	 */

	public Optional<Usuario> findByIdUsuario(Long idUsuario);

	/**
	 * Método que procura na tabela usuario o atributo nome
	 * 
	 * @author Abner
	 * @param nome
	 * @return optional com usuário
	 * 
	 */

	public Optional<Usuario> findByNome(String nome);

	/**
	 * Método que procura na tabela usuario o atributo userName
	 * 
	 * @author Abner
	 * @param userName
	 * @return optional com usuário
	 * 
	 */

	public Optional<Usuario> findByUserName(String userName);

	/**
	 * Método que procura na tabela usuario o atributo email
	 * 
	 * @author Abner
	 * @param email
	 * @return optional com usuário
	 * 
	 */

	public Optional<Usuario> findByEmail(String email);

}
