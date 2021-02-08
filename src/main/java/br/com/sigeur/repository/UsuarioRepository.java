package br.com.sigeur.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sigeur.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
