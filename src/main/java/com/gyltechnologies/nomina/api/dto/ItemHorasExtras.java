package com.gyltechnologies.nomina.api.dto;

import java.util.Date;

import com.gyltechnologies.nomina.api.dto.CalcularHorasExtrasRequest.CalcularHorasExtrasRequestBuilder;

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

	private String key;
	private int cantidad;
}
