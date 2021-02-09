package br.com.sigeur.model;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario extends Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargo;
	
	@ManyToMany
	@JoinTable(
			name = "usuario_perfil",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "perfil_id")
			)
	private List<Perfil> perfils;

	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	
	public Usuario() {}
	
	public void setUsuario(Usuario usuario) {
		this.id = usuario.getId();
		this.setNome(usuario.getNome());
		this.setCpf(usuario.getCpf());;
		this.setDataNascimento(usuario.getDataNascimento());;
		this.setSexo(usuario.getSexo());;
		this.cargo = usuario.getCargo();
		this.perfils = usuario.getPerfils();
		this.dataCriacao = usuario.getDataCriacao();
	}

	public Usuario(Integer id, String nome, String cpf, Date dataNascimento, String sexo, Cargo cargo,
			List<Perfil> perfils, Date dataCriacao) {
		this.id = id;
		this.setNome(nome);
		this.setCpf(cpf);
		this.setDataNascimento(dataNascimento);
		this.setSexo(sexo);
		this.cargo = cargo;
		this.perfils = perfils;
		this.dataCriacao = dataCriacao;
	}

	public Usuario(Integer id, String nome, String cpf, Date dataNascimento, String sexo, Cargo cargo,
			Date dataCriacao) {
		this.id = id;
		this.setNome(nome);
		this.setCpf(cpf);
		this.setDataNascimento(dataNascimento);
		this.setSexo(sexo);
		this.cargo = cargo;
		this.dataCriacao = dataCriacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public void removePerfil(Perfil perfil) {
		this.perfils.remove(perfil);
		perfil.getUsuarios().remove(this);
	}
	
}
