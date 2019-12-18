package com.gyltechnologies.nomina.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyltechnologies.nomina.api.model.entities.FestivosEntity;

@Repository
public interface FestivosRepository extends JpaRepository<FestivosEntity, Integer> {

}
