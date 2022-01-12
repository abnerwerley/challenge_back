package com.firedevit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firedevit.model.Usuario;

@Repository
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

	public Optional<Usuario> findByNomeContainingIgnoreCase(String nome);
	
	/**
	 * Método que procura na tabela usuario o atributo Name
	 * 
	 * @author Abner
	 * @param userName
	 * @return List com usuários
	 * 
	 */
	
	public List<Usuario> findUsersByNomeContainingIgnoreCase(String name);

	/**
	 * Método que procura na tabela usuario o atributo userName
	 * 
	 * @author Abner
	 * @param userName
	 * @return optional com usuário
	 * 
	 */

	public Optional<Usuario> findByUserNameContainingIgnoreCase(String userName);
	
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
