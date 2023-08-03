package com.user.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.app.entity.UsuarioEntity;
import com.user.app.feingcliente.BikerFeignCliente;
import com.user.app.feingcliente.CarFeignCliente;
import com.user.app.model.Bike;
import com.user.app.model.Carro;
import com.user.app.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CarFeignCliente carFeignCliente;
	@Autowired
	private BikerFeignCliente bikerFeignCliente;

	public List<UsuarioEntity> getAllUsuarios() {
		return repository.findAll();
	}

	public UsuarioEntity getUsuariosById(int id) {
		return repository.findById(id).orElse(null);
	}

	public UsuarioEntity saveUsuario(UsuarioEntity usuario) {
		repository.save(usuario);
		return usuario;
	}

	public List<Carro> getCars(int userid) {
		@SuppressWarnings("unchecked")
		List<Carro> listaCarros = restTemplate.getForObject("http://car-service/car/byuser/" + userid, List.class);
		return listaCarros;
	}

	public List<Bike> getBikes(int userid) {
		@SuppressWarnings("unchecked")
		List<Bike> listaBikes = restTemplate.getForObject("http://bike-service/bike/byuser/" + userid, List.class);
		return listaBikes;
	}

	public Carro saveCarro(int userId, Carro carro) {
		carro.setUserId(userId);
		return carFeignCliente.saveCar(carro);
	}

	public Bike saveBike(int userId, Bike bike) {
		bike.setUserId(userId);
		return bikerFeignCliente.saveBike(bike);
	}

	public List<Carro> getCarros(int userId) {
		return carFeignCliente.getCarros(userId);
	}

	public List<Bike> getBikers(int userId) {
		return bikerFeignCliente.getBikers(userId);
	}

	public Map<String, Object> getUsuariosAndVehiculos(int userId) {
		Map<String, Object> resul = new HashMap<>();
		UsuarioEntity usuario = repository.findById(userId).orElse(null);
		if (usuario == null) {
			resul.put("mensaje", "No existe el usuario");
			return resul;
		}
		resul.put("User", usuario);
		List<Carro> listacarros = carFeignCliente.getCarros(userId);
		List<Bike> listabikers = bikerFeignCliente.getBikers(userId);
		if (listacarros.isEmpty()) {
			resul.put("Cars", "El usuario no tiene carros");
		} else {
			resul.put("Cars", listacarros);
		}
		if (listabikers.isEmpty()) {
			resul.put("Bikers", "El usuario no tiene bikers");
		} else {
			resul.put("Bikers", listabikers);
		}
		return resul;
	}

}
