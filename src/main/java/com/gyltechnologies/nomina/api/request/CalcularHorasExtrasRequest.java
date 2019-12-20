package com.gyltechnologies.nomina.api.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalcularHorasExtrasRequest {
	@NotBlank
	private Date fechaInicio;
	
	@NotBlank
	private Integer horaInicio;
	
	@NotBlank
	private Date fechaFin;
	
	@NotBlank
	private Integer horaFin;
	
	private Boolean soloExtras = false ;
}
