package com.gyltechnologies.nomina.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gyltechnologies.nomina.api.model.entities.TipoHorasExtrasDetalleEntity;
@Repository
public interface TipoHorasExtrasDetalleRepository extends JpaRepository<TipoHorasExtrasDetalleEntity, Integer> {

	@Query(value = "select D.*\r\n" + 
			"FROM NOM_CONFIGURACION_Horas_EXTRAS C,NOM_CONFIGURACION_Horas_EXTRAS_DETALLE D WHERE\r\n" + 
			" D.NOM_CONFIGURACION_Horas_EXTRAS_id=C.ID AND  C.Tipo=:tipoDia AND C.Tipo_Hora=:tipoHora\r\n" + 
			"AND (\r\n" + 
			"(:horaInicial BETWEEN hora_inicio AND hora_Final)\r\n" + 
			"OR\r\n" + 
			"(:horaFinal BETWEEN hora_inicio AND hora_Final)\r\n" + 
			"OR (hora_inicio  BETWEEN :horaInicial AND :horaFinal))\r\n" + 
			"AND hora_inicio<:horaFinal\r\n" + 
			"order by hora_inicio", nativeQuery = true)
	public List<TipoHorasExtrasDetalleEntity> getTipoHorasAplica(@Param("tipoDia") String tipoDia
			,@Param("tipoHora") String tipoHora,@Param("horaInicial") Integer horaInicial, @Param("horaFinal") Integer horaFinal);
	
}
