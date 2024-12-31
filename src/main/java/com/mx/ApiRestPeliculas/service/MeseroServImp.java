package com.mx.ApiRestPeliculas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestPeliculas.dao.MeseroDao;
import com.mx.ApiRestPeliculas.model.Mesero;

@Service
public class MeseroServImp {

	@Autowired
	MeseroDao meseroDao;

	// --LISTAR
	public List<Mesero> listar() {
		return meseroDao.findAll();
	}

	// --GUARDAR
	@Transactional
	public boolean guardar(Mesero mesero) {

		boolean bandera = false;

		for (Mesero m : meseroDao.findAll()) {
			// validar que id no se repita
			if (m.getIdMesero().equals(mesero.getIdMesero())) {
				if (m.getApp().equals(mesero.getApp()) && m.getApm().equals(mesero.getApm())) {
					bandera = true;
					break;
				}
			}
			// validar que nombre no se repita
			if (m.getNombre().equals(mesero.getNombre())) {
				bandera = true;
				break;
			}
		}

		if (bandera == false) {
			meseroDao.save(mesero);
		}
		return bandera;
	}

	// --BUSCAR
	@Transactional
	public Mesero buscar(long idMesero) {
		return meseroDao.findById(idMesero).orElse(null);
	}

	// --EDITAR
	@Transactional
	public boolean editar(Mesero mesero) {

		boolean bandera = false;

		for (Mesero m : meseroDao.findAll()) {
			// Validar que el id exista
			if (m.getIdMesero().equals(mesero.getIdMesero())) {
				bandera = true;
				break;
			}
		}

		if (bandera == true) {
			meseroDao.save(mesero);
		}

		return bandera;
	}

	// --ELIMINAR
	@Transactional
	public boolean eliminar(long idMesero) {

		boolean bandera = false;

		for (Mesero m : meseroDao.findAll()) {
			// Validar que el id exista para poder eliminar
			if (m.getIdMesero().equals(idMesero)) {
				bandera = true;
				break;
			}
		}

		if (bandera == true) {
			meseroDao.deleteById(idMesero);

		}
		return bandera;
	}

}
