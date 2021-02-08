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

import br.com.sigeur.dto.CargoDto;
import br.com.sigeur.model.Cargo;
import br.com.sigeur.repository.CargoRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	private CargoRepository cargoRepository;
	
	@GetMapping("/")
	public List<CargoDto> list() {
		List<Cargo> cargos = cargoRepository.findAll();
		
		return CargoDto.converter(cargos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CargoDto> getCargo(@Valid @PathVariable("id") Integer id)
		throws Exception{
		Cargo cargo = cargoRepository.findById(id).orElseThrow(
				() -> new Exception("Cargo não encontrado!")
				);
		
		return ResponseEntity.ok().body(new CargoDto(cargo));
	}
	
	@PostMapping("/")
	public CargoDto saveCargo(@Valid @RequestBody Cargo cargo) {
		return new CargoDto(cargoRepository.save(cargo));
	}
	
	@PutMapping("/{id}")
	public CargoDto updateCargo(@Valid @PathVariable("id") Integer id,
			@RequestBody Cargo cargoRequest) throws Exception {
		Cargo cargo = cargoRepository.findById(id).orElseThrow(
				() -> new Exception("Cargo não encontrado!")
				);
		
		cargo.setId(cargoRequest.getId());
		cargo.setNome(cargoRequest.getNome());
		
		return new CargoDto(cargoRepository.save(cargo));
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteCargo(@Valid @PathVariable("id") Integer id) 
			throws Exception{
		Cargo cargo = cargoRepository.findById(id).orElseThrow(
				() -> new Exception("Cargo não encontrado!")
				);
		
		cargoRepository.delete(cargo);
		Map<String, Boolean> respDeleted = new HashMap<String, Boolean>();
		respDeleted.put("deleted", true);
		
		return respDeleted;
	}
}
