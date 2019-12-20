package com.gyltechnologies.nomina.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	public List<ItemHorasExtras> calcularHoras(@Valid  @RequestBody CalcularHorasExtrasRequest request){

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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException( MethodArgumentNotValidException error ) {
	   return parseErrors(error.getBindingResult());
	}
	
	private ResponseEntity<Object> parseErrors(BindingResult error) {
		List<String> errors = new ArrayList<>();
		error.getAllErrors().forEach(item->errors.add(item.getDefaultMessage()));
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
	
}
