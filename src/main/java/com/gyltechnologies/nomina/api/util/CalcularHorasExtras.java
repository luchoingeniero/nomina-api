package com.gyltechnologies.nomina.api.util;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gyltechnologies.nomina.api.dto.CalcularHorasExtrasRequest;
import com.gyltechnologies.nomina.api.dto.ItemHorasExtras;
import com.gyltechnologies.nomina.api.model.entities.FestivosEntity;
import com.gyltechnologies.nomina.api.model.entities.TipoHorasExtrasDetalleEntity;
import com.gyltechnologies.nomina.api.model.repository.FestivosRepository;
import com.gyltechnologies.nomina.api.model.repository.TipoHorasExtrasDetalleRepository;

@Component
public class CalcularHorasExtras {

	public static final Integer HORAS_ORDINARIAS_MAX = 8;
	public static final Integer HORA_DIA_FINAL =24;
	public static final Integer HORA_DIA_INICIAL = 0;
	public static final Integer DOMINGO = 7;
	@Autowired
	private TipoHorasExtrasDetalleRepository tipoHorasExtrasDetalleRepository;
	@Autowired
	private FestivosRepository festivosRepository;
	
	public String getTipoDia(Date fecha) {
		/*Festivo_Dominical*/
		/*Normal*/
		Optional<FestivosEntity> optionalFestivo = this.festivosRepository.findByFecha(fecha);
		return (optionalFestivo.isPresent()||this.isDomingo(fecha))?"Festivo_Dominical":"Normal";
	}
	
	public Boolean isDomingo(Date fecha) {
		
		DayOfWeek dayOfWeek 
        = DayOfWeek.from(this.convertToLocalDateViaMilisecond(fecha));
		return dayOfWeek.getValue()==DOMINGO;
	}
	
	public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
	    return Instant.ofEpochMilli(dateToConvert.getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
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
					.horasOrdinariasEncontradas(0)
					.soloExtras(request.getSoloExtras())
					.tipoDia(getTipoDia(request.getFechaInicio()))
					.build();
			list = this.calcularDetalle(req1);
			
			CalcularHorasExtrasRequest req2 = CalcularHorasExtrasRequest
					.builder()
					.fechaInicio(request.getFechaFin())
					.fechaFin(request.getFechaFin())
					.horaInicio(HORA_DIA_INICIAL)
					.horaFin(request.getHoraFin())
					.horasOrdinariasEncontradas(req1.getHorasOrdinariasEncontradas())
					.soloExtras(request.getSoloExtras())
					.tipoDia(getTipoDia(request.getFechaFin()))
					.build();
			list.addAll(this.calcularDetalle(req2));
			
			return list;
			
		}else {
			request.setHorasOrdinariasEncontradas(0);
			request.setTipoDia(this.getTipoDia(request.getFechaInicio()));
			return this.calcularDetalle(request);
		}
		
	}
	
	public List<ItemHorasExtras> calcularDetalle(CalcularHorasExtrasRequest request){
		List<ItemHorasExtras> list = new ArrayList<>();
		Integer horasTrabajadas = request.getHoraFin()-request.getHoraInicio();
		if(!request.getSoloExtras()&&request.getHorasOrdinariasEncontradas()<HORAS_ORDINARIAS_MAX) {
			List<TipoHorasExtrasDetalleEntity> horasOrdinarias = this.tipoHorasExtrasDetalleRepository.getTipoHorasAplica(request.getTipoDia(), "Ordinaria", request.getHoraInicio(),request.getHoraFin());
			Integer aplicarHoras = 0;
			for(TipoHorasExtrasDetalleEntity item : horasOrdinarias) {
				//System.out.println(item.getHoraInicio()+" "+item.getHoraFinal()+" "+item.getTipoHorasExtrasEntity().getDescripcion()+" "+item.getTipoHorasExtrasEntity().getPorcentaje());
				aplicarHoras = item.getHoraFinal() - request.getHoraInicio();
				//System.out.println("verificar "+item.getHoraFinal()+"-"+request.getHoraInicio()+"==>"+aplicarHoras);
				
				if( aplicarHoras>0 && request.getHorasOrdinariasEncontradas()+ aplicarHoras <= HORAS_ORDINARIAS_MAX) {
					request.setHorasOrdinariasEncontradas(request.getHorasOrdinariasEncontradas()+aplicarHoras);
					list.add(ItemHorasExtras.builder().key(item.getTipoHorasExtrasEntity().getDescripcion()).cantidad(aplicarHoras).build());
					request.setHoraInicio(item.getHoraFinal());
				}else if(request.getHorasOrdinariasEncontradas() + aplicarHoras > HORAS_ORDINARIAS_MAX) {
					aplicarHoras = HORAS_ORDINARIAS_MAX - request.getHorasOrdinariasEncontradas();
					request.setHorasOrdinariasEncontradas(request.getHorasOrdinariasEncontradas()+aplicarHoras);
					list.add(ItemHorasExtras.builder().key(item.getTipoHorasExtrasEntity().getDescripcion()).cantidad(aplicarHoras).build());
					request.setHoraInicio(request.getHoraInicio()+aplicarHoras);
					break;
				}
	
			}
			
			
		}
		
		List<TipoHorasExtrasDetalleEntity> horasExtras = this.tipoHorasExtrasDetalleRepository.getTipoHorasAplica(request.getTipoDia(), "Extra", request.getHoraInicio(),request.getHoraFin());
		
		for(TipoHorasExtrasDetalleEntity item : horasExtras) {
			Integer aplicarHorasExtras = item.getHoraFinal() - request.getHoraInicio();
			if(item.getHoraFinal()>request.getHoraFin()) {
				aplicarHorasExtras = request.getHoraFin() - request.getHoraInicio();
			}
			request.setHoraInicio(item.getHoraFinal());
			if(aplicarHorasExtras>0) {
				list.add(ItemHorasExtras.builder().key(item.getTipoHorasExtrasEntity().getDescripcion()).cantidad(aplicarHorasExtras).build());	
			}
			
			
		}
		
		
		
		System.out.println("Horas Trabajadas en el dia TipoDia("+request.getTipoDia()+")"+" "+request.getFechaInicio()+" "+horasTrabajadas);
		list.forEach(item->{
			System.out.println(item.getKey()+" "+item.getCantidad());
		});
		
		
		return list;
	}
}
