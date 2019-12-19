package com.gyltechnologies.nomina.api.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyltechnologies.nomina.api.dto.CalcularHorasExtrasRequest;
import com.gyltechnologies.nomina.api.model.entities.TipoHorasExtrasEntity;
import com.gyltechnologies.nomina.api.model.repository.TipoHorasExtrasRepository;
import com.gyltechnologies.nomina.api.util.CalcularHorasExtras;

@RestController
@RequestMapping("/")
public class TestController {
	
	@Autowired
	private TipoHorasExtrasRepository tipo;

	@Autowired
	private CalcularHorasExtras calcular;
	
	@GetMapping("/listar")
	public List<TipoHorasExtrasEntity> list() throws ParseException{
		CalcularHorasExtrasRequest req = CalcularHorasExtrasRequest.builder()
				.fechaInicio(new SimpleDateFormat("dd/MM/yyyy").parse("14/12/2019"))
				.fechaFin(new SimpleDateFormat("dd/MM/yyyy").parse("15/12/2019"))
				.horaInicio(17)
				.horaFin(2)
				.soloExtras(true)
				.build();
			
		this.calcular.calcular(req);
		
		return new ArrayList<TipoHorasExtrasEntity>();
	}
	
}
