package com.mx.ApiRestPeliculas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestPeliculas.dao.MesasDao;
import com.mx.ApiRestPeliculas.model.Mesas;

@Service
public class MesasServImp {

	@Autowired
	MesasDao mesasDao;

	public List<Mesas> listar() {
		return mesasDao.findAll();
	}

	// --GUARDAR
	@Transactional
	public String guardar(Mesas mesa) {

		boolean bandera = false;
		String respuesta = "";

		for (Mesas m : mesasDao.findAll()) {
			// validar que id no se repita
			if (m.getIdMesa().equals(mesa.getIdMesa())) {
				bandera = true;
				respuesta = "idMesaRepetido";
				break;
			}
			// validar que num mesa no se repita
			if (m.getNumMesa().equals(mesa.getNumMesa())) {
				bandera = true;
				respuesta = "NumMesaRepetido";
				break;
			}
			// validar que exista id mesero
			if (m.getMesero().getIdMesero().equals(mesa.getMesero().getIdMesero())) {
				bandera = true;
				respuesta = "idMeseroExiste";
				break;
			}
		}

		if (bandera == false) {
			mesasDao.save(mesa);
			respuesta = "idMeseroNulo";
		}
		return respuesta;
	}

	// --BUSCAR
	@Transactional
	public Mesas buscar(long idMesa) {
		return mesasDao.findById(idMesa).orElse(null);
	}

	// --EDITAR
	@Transactional
	public boolean editar(Mesas mesa) {

		boolean bandera = false;

		for (Mesas m : mesasDao.findAll()) {
			if (m.getIdMesa().equals(mesa.getIdMesa())) {
				if (m.getMesero().getIdMesero().equals(mesa.getMesero().getIdMesero())) {
					bandera = true;
					break;
				}
			}

		}

		if (bandera == true) {
			mesasDao.save(mesa);
		}

		return bandera;
	}

	// --ELIMINAR
	
	@Transactional
	public boolean eliminar(long idMesa) {

		boolean bandera = false;
		// Validar que el id exista para poder eliminar
		for (Mesas m : mesasDao.findAll()) {
			if (m.getIdMesa().equals(idMesa)) {
				bandera = true;
				break;
			}
		}

		if (bandera == true) {
			mesasDao.deleteById(idMesa);

		}
		return bandera;
	}
}
