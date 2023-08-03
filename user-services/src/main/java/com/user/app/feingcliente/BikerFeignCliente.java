package com.user.app.feingcliente;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.app.model.Bike;

@FeignClient(name = "bike-service")
//@RequestMapping("/bike")
public interface BikerFeignCliente {
	
	@PostMapping("/bike")
	public Bike saveBike(@RequestBody Bike carro);
	
	@GetMapping(value = "/bike/byuser/{userid}")
	public List<Bike> getBikers(@PathVariable("userid") int userid);

}
