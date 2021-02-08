package br.com.sigeur.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sigeur.model.Cargo;
import br.com.sigeur.model.Perfil;
import br.com.sigeur.model.Usuario;

public class UsuarioDto {
	
	private Integer id;
	private String nome;
	private String cpf;
	private LocalDateTime dataNascimento;
	private char sexo;
	private Cargo cargo;
	private List<Perfil> perfils;
	private LocalDateTime dataCriacao;
	
	public UsuarioDto() {}
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.cpf = usuario.getCpf();
		this.dataNascimento = usuario.getDataNascimento();
		this.sexo = usuario.getSexo();
		this.cargo = usuario.getCargo();
		this.perfils = usuario.getPerfils();
		this.dataCriacao = usuario.getDataCriacao();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Perfil> getPerfils() {
		return perfils;
	}

	public void setPerfils(List<Perfil> perfils) {
		this.perfils = perfils;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public static List<UsuarioDto> converter(List<Usuario> usuarios) {
		// TODO Auto-generated method stub
		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}

}
