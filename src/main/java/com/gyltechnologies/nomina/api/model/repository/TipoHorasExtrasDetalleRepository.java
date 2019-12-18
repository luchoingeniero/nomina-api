package com.gyltechnologies.nomina.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyltechnologies.nomina.api.model.entities.TipoHorasExtrasDetalleEntity;
@Repository
public interface TipoHorasExtrasDetalleRepository extends JpaRepository<TipoHorasExtrasDetalleEntity, Integer> {

}
