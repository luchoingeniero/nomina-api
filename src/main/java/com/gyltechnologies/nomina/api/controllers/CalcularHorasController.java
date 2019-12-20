package com.gyltechnologies.nomina.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyltechnologies.nomina.api.dto.CalcularHorasExtrasDTO;
import com.gyltechnologies.nomina.api.dto.ItemHorasExtras;
import com.gyltechnologies.nomina.api.request.CalcularHorasExtrasRequest;
import com.gyltechnologies.nomina.api.util.CalcularHorasExtras;

@RestController
@RequestMapping("/calcular")
public class CalcularHorasController {

	@Autowired
	private CalcularHorasExtras calcularHorasExtras;
	
	@PostMapping("/horas")
	public List<ItemHorasExtras> calcularHoras(@RequestBody CalcularHorasExtrasRequest request){

		CalcularHorasExtrasDTO calcularHorasExtrasDTO = CalcularHorasExtrasDTO
				.builder()
				.fechaInicio(request.getFechaInicio())
				.horaInicio(request.getHoraInicio())
				.fechaFin(request.getFechaFin())
				.horaFin(request.getHoraFin())
				.soloExtras(request.getSoloExtras())
				.build();
		
		return this.calcularHorasExtras.calcular(calcularHorasExtrasDTO);
	}
	
}
