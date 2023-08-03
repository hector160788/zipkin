package com.user.app.feingcliente;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.app.model.Carro;

@FeignClient(name = "car-service")
//@RequestMapping("/car")
public interface CarFeignCliente {
	
	@PostMapping("/car")
	public Carro saveCar(@RequestBody Carro carro);
	
	@GetMapping(value = "/car/byuser/{userId}")
	public List<Carro> getCarros(@PathVariable("userId") int userId);

}
