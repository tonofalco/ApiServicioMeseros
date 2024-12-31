package com.mx.ApiRestPeliculas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.ApiRestPeliculas.model.Mesas;

public interface MesasDao extends JpaRepository<Mesas, Long> {

}
