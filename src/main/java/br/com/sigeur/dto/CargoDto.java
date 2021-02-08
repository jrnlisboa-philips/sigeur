package br.com.sigeur.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.sigeur.model.Cargo;

public class CargoDto {
	
	private Integer id;
	private String nome;
	
	public CargoDto() {}
	
	public CargoDto(Cargo cargo) {
		this.id = cargo.getId();
		this.nome = cargo.getNome();
	}	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static List<CargoDto> converter(List<Cargo> cargos) {		
		return cargos.stream().map(CargoDto::new).collect(Collectors.toList());
	}
}
