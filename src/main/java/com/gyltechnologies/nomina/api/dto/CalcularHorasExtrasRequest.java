package com.gyltechnologies.nomina.api.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalcularHorasExtrasRequest {

	private Date fechaInicio;
	private Integer horaInicio;
	private Date fechaFin;
	private Integer horaFin;
	@Builder.Default
	private Integer horasOrdinariasEncontradas = 0;
	@Builder.Default
	private Boolean soloExtras = false ;
}
