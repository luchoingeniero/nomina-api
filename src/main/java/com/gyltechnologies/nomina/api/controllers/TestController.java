package com.gyltechnologies.nomina.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyltechnologies.nomina.api.model.entities.TipoHorasExtrasEntity;
import com.gyltechnologies.nomina.api.model.repository.TipoHorasExtrasRepository;

@RestController
@RequestMapping("/")
public class TestController {
	
	@Autowired
	private TipoHorasExtrasRepository tipo;

	@GetMapping("/listar")
	public List<TipoHorasExtrasEntity> list(){
		return tipo.findAll();
	}
	
}
