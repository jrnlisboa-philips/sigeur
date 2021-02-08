package br.com.sigeur.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigeur.dto.PerfilDto;
import br.com.sigeur.model.Perfil;
import br.com.sigeur.repository.PerfilRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/perfils")
public class PerfilController {

	@Autowired
	PerfilRepository perfilRepository;
	
	@GetMapping("/")
	public List<PerfilDto> list() {
		return PerfilDto.converter(perfilRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PerfilDto> getPerfilById(@Valid @PathVariable("id") Integer id)
		throws Exception {
		Perfil perfil = perfilRepository.findById(id).orElseThrow( 
				() -> new Exception("Perfil não encontrado!"));
		
		return ResponseEntity.ok().body(new PerfilDto(perfil));
	}
	
	@PostMapping("/")
	public ResponseEntity<PerfilDto> savePerfil(@Valid @RequestBody Perfil perfil) {
		return ResponseEntity.ok().body( new PerfilDto(perfilRepository.save(perfil)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PerfilDto> updatePerfil(@Valid @PathVariable("id") Integer id,
			@RequestBody Perfil perfilRequest) throws Exception {
		Perfil perfil = perfilRepository.findById(id).orElseThrow(
				() -> new Exception("Perfil Não encontrado!"));
		
		perfil.setId(perfilRequest.getId());
		perfil.setNome(perfilRequest.getNome());
		
		return ResponseEntity.ok().body(new PerfilDto(perfilRepository.save(perfil)));
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deletePerfil(@Valid @PathVariable("id") Integer id) 
			throws Exception {
		Perfil perfil = perfilRepository.findById(id).orElseThrow(
				() -> new Exception("Perfil Não encontrado!"));
		
		perfilRepository.delete(perfil);
		
		Map<String, Boolean> deletedPerfil = new HashMap<String, Boolean>();
		deletedPerfil.put("deleted", true);
		
		return deletedPerfil;
	}
}
