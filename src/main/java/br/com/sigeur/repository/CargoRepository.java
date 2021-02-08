package br.com.sigeur.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sigeur.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer>{

}
