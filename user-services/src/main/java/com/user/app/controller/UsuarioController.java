package com.user.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.app.entity.UsuarioEntity;
import com.user.app.model.Bike;
import com.user.app.model.Carro;
import com.user.app.service.UsuarioService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioEntity>> getAllUsuarios() {
		List<UsuarioEntity> listausuarios = usuarioService.getAllUsuarios();
		if (listausuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listausuarios);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioEntity> getUsuariosById(@PathVariable(value = "id") int id) {
		UsuarioEntity usuario = usuarioService.getUsuariosById(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity<UsuarioEntity> saveUsuario(@RequestBody UsuarioEntity usuario) {
		UsuarioEntity usuarioresponse = usuarioService.saveUsuario(usuario);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuarioresponse);
	}

	@CircuitBreaker(name = "carsCB", fallbackMethod = "fallbackGetCars")
	@GetMapping(value = "/cars/{userId}")
	public ResponseEntity<List<Carro>> getCarros(@PathVariable("userId") int userId) {
		UsuarioEntity usuario = usuarioService.getUsuariosById(userId);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Carro> carros = usuarioService.getCars(userId);
		return ResponseEntity.ok(carros);
	}
	@CircuitBreaker(name = "carsCB", fallbackMethod = "fallbackSaveCar")
	@PostMapping(value = "/savecar/{userId}")
	public ResponseEntity<Carro> saveCarro(@PathVariable("userId") int userId, @RequestBody Carro carro) {
		Carro carronew = usuarioService.saveCarro(userId, carro);
		if (carronew == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(carronew);

	}

	@CircuitBreaker(name = "bikesCB", fallbackMethod = "fallbackGetBikes")
	@GetMapping(value = "/bikers/{userId}")
	public ResponseEntity<List<Bike>> getBikers(@PathVariable("userId") int userId) {
		UsuarioEntity usuario = usuarioService.getUsuariosById(userId);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Bike> carros = usuarioService.getBikes(userId);
		return ResponseEntity.ok(carros);
	}

	@CircuitBreaker(name = "bikesCB", fallbackMethod = "fallbackSaveBike")
	@PostMapping(value = "/savebike/{userId}")
	public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike) {
		Bike bikenew = usuarioService.saveBike(userId, bike);
		if (bikenew == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(bikenew);

	}

	@CircuitBreaker(name = "allCB", fallbackMethod = "fallbackGetAll")
	@GetMapping(value = "/getAll/{userid}")
	public ResponseEntity<Map<String, Object>> getAllVehiculos(@PathVariable(value = "userid") int userid) {

		return ResponseEntity.ok(usuarioService.getUsuariosAndVehiculos(userid));
	}
	
	///metodo de fallback resilencia a fallos
	
	private ResponseEntity<List<Carro>> fallbackGetCars(@PathVariable("userId") int userId, RuntimeException e) {
		return new ResponseEntity("El usuario "+userId+" tiene los autos en el taller",HttpStatus.OK);
	}
	
	private ResponseEntity<Carro> fallbackSaveCar(@PathVariable("userId") int userId, @RequestBody Carro carro, RuntimeException e) {
		return new ResponseEntity("El usuario "+userId+" NO tiene dinero",HttpStatus.OK);
	}
	
	private ResponseEntity<List<Bike>> fallbackGetBikes(@PathVariable("userId") int userId, RuntimeException e) {
		return new ResponseEntity("El usuario "+userId+" tiene los motos en el taller",HttpStatus.OK);
	}
	
	private ResponseEntity<Carro> fallbackSaveBike(@PathVariable("userId") int userId, @RequestBody Carro carro, RuntimeException e) {
		return new ResponseEntity("El usuario "+userId+" no tiene dinero",HttpStatus.OK);
	}
	private ResponseEntity<Map<String, Object>> fallbackGetAll(@PathVariable(value = "userid") int userid, RuntimeException e) {
		return new ResponseEntity("El usuario "+userid+" tiene todo en el taller**",HttpStatus.OK);
	}

}
