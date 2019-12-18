package com.gyltechnologies.nomina.api.model.entities;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="nom_configuracion_horas_extras")
@Getter
@Setter
public class TipoHorasExtrasEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String tipo;
	private String descripcion;
	
	@Column(name = "tipo_hora")
	private String tipoHora;
	
	private int porcentaje;
	@OneToMany(mappedBy = "tipoHorasExtrasEntity", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<TipoHorasExtrasDetalleEntity> detalle;

}
