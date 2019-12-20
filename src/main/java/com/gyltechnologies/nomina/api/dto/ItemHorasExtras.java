package com.gyltechnologies.nomina.api.dto;



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
public class ItemHorasExtras {

	private String dia;
	private String tipoHora;
	private Integer cantidad;
	private Integer porcentaje;
}
