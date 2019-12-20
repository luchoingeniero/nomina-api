package com.gyltechnologies.nomina.api.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalcularHorasExtrasRequest {
	@NotNull(message = "Fecha de Inicio es obligatoria")
	private Date fechaInicio;
	
	@NotNull(message = "Hora Inicio es obligatoria")
	private Integer horaInicio;
	
	@NotNull(message = "Fecha Final es obligatoria")
	private Date fechaFin;
	
	@NotNull(message = "Hora Final es obligatoria")
	private Integer horaFin;
	
	private Boolean soloExtras = false ;
}
