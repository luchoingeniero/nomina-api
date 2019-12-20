package com.gyltechnologies.nomina.api.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="nom_configuracion_horas_extras_detalle")
@Getter
@Setter
public class TipoHorasExtrasDetalleEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nom_configuracion_horas_extras_id")
	private TipoHorasExtrasEntity tipoHorasExtrasEntity;
    @Column(name="hora_inicio")
	private int horaInicio;
    @Column( name ="hora_final")
	private int horaFinal;

}
