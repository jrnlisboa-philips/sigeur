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

import br.com.sigeur.dto.UsuarioDto;
import br.com.sigeur.model.Perfil;
import br.com.sigeur.model.Usuario;
import br.com.sigeur.repository.UsuarioRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping("/")
	public List<UsuarioDto> list() {
		return UsuarioDto.converter(usuarioRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> getUsuarioById(@Valid @PathVariable("id") Integer id) 
		throws Exception{
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(
				() -> new Exception("Usuário não encontrado"));
		
		return ResponseEntity.ok().body(new UsuarioDto(usuario));
	}
	
	@PostMapping("/")
	public ResponseEntity<UsuarioDto> saveUsuario(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.ok().body(new UsuarioDto(usuarioRepository.save(usuario)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDto> updateUsuario(@Valid @PathVariable("id") Integer id, 
			@RequestBody Usuario usuarioReq) throws Exception {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(
				() -> new Exception("Usuário não encontrado"));
		
		usuarioReq.setId(usuario.getId());
		
		return ResponseEntity.ok().body(new UsuarioDto(usuarioRepository.save(usuario)));
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteUsuario(@Valid @PathVariable("id") Integer id) 
			throws Exception{
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(
				() -> new Exception("Usuário não encontrado"));			
			
		usuarioRepository.delete(usuario);
		
		Map<String, Boolean> usuarioDeleted = new HashMap<String, Boolean>();
		usuarioDeleted.put("deleted", true);
		
		return usuarioDeleted;
	}
	

	
	@PostMapping("/perfil")
	public ResponseEntity<UsuarioDto> savePerfilUsuario(@Valid @RequestBody Usuario usuarioR)
			throws Exception{
		Usuario usuario = usuarioRepository.findById(usuarioR.getId()).orElseThrow(
				() -> new Exception("Usuário não encontrado"));
		
		usuario.getPerfils().add(usuarioR.getPerfils().get(0));
		return ResponseEntity.ok().body(new UsuarioDto(usuarioRepository.save(usuario)));
	}
	
	@DeleteMapping("/perfil/{id}/{idUsuario}")
	public Map<String, Boolean> deletePerfilUsuario(@Valid @PathVariable("id") Integer id,
			@PathVariable("idUsuario") Integer idUsuario) 
		throws Exception{
		Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(
				() -> new Exception("Usuário não encontrado"));
		
		for(Perfil perf: usuario.getPerfils()) {
			if(perf.getId() == id)
				usuario.removePerfil(perf);
		}
		usuarioRepository.save(usuario);
		
		Map<String, Boolean> perfilDel = new HashMap<String, Boolean>();
		perfilDel.put("deleted", true);
		
		return perfilDel;
	}
}
