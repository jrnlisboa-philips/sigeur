package br.com.sigeur.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.sigeur.model.Perfil;

public class PerfilDto {

	private Integer id;
	private String nome;
	
	public PerfilDto() {}

	public PerfilDto(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public PerfilDto(Perfil perfil) {
		this.id = perfil.getId();
		this.nome = perfil.getNome();
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
	
	public static List<PerfilDto> converter(List<Perfil> perfils) {
		return perfils.stream().map(PerfilDto::new).collect(Collectors.toList());
	}
}
