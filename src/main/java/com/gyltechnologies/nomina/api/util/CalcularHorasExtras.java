package com.gyltechnologies.nomina.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import com.gyltechnologies.nomina.api.dto.CalcularHorasExtrasRequest;
import com.gyltechnologies.nomina.api.dto.ItemHorasExtras;

public class CalcularHorasExtras {

	public static final Integer HORAS_ORDINARIAS_MAX = 8;
	public static final Integer HORA_DIA_FINAL =24;
	public static final Integer HORA_DIA_INICIAL = 0;
	
	public List<ItemHorasExtras> calcular(CalcularHorasExtrasRequest request){
		//condicion si no es el mismo dia!!!
		if(request.getFechaInicio().before(request.getFechaFin())) {
			List<ItemHorasExtras> list = new ArrayList<>();
						
			CalcularHorasExtrasRequest req1 = CalcularHorasExtrasRequest
					.builder()
					.fechaInicio(request.getFechaInicio())
					.fechaFin(request.getFechaInicio())
					.horaInicio(request.getHoraInicio())
					.horaFin(HORA_DIA_FINAL)
					.build();
			list = this.calcularDetalle(req1);
			
			CalcularHorasExtrasRequest req2 = CalcularHorasExtrasRequest
					.builder()
					.fechaInicio(request.getFechaFin())
					.fechaFin(request.getFechaFin())
					.horaInicio(HORA_DIA_INICIAL)
					.horaFin(request.getHoraFin())
					.horasOrdinariasEncontradas(req1.getHorasOrdinariasEncontradas())
					.build();
			list.addAll(this.calcularDetalle(req2));
			
			return list;
			
		}else {
			return this.calcular(request);
		}
		
	}
	
	public List<ItemHorasExtras> calcularDetalle(CalcularHorasExtrasRequest request){
		List<ItemHorasExtras> list = new ArrayList<>();
		return list;
	}
}
