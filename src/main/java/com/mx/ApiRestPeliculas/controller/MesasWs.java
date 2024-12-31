package com.mx.ApiRestPeliculas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiRestPeliculas.model.Mesas;
import com.mx.ApiRestPeliculas.service.MesasServImp;

@RestController
@RequestMapping(path = "MesasWs")
@CrossOrigin
public class MesasWs {

	@Autowired
	MesasServImp mesasImp;

	// http://localhost:9000/MesasWs/listar
	@GetMapping(path = "listar")
	public List<Mesas> listar() {
		return mesasImp.listar();
	}

	// http://localhost:9000/MesasWs/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Mesas mesa) {

		String response = mesasImp.guardar(mesa);
		System.out.println("valor:" + response);

		if (response.equals("idMesaRepetido")) {
			return new ResponseEntity<>("el id de mesa ya existe", HttpStatus.OK);
		} else if (response.equals("NumMesaRepetido")) {
			return new ResponseEntity<>("el num de mesa no debe repetirse", HttpStatus.OK);
		} else if (response.equals("idMeseroNulo")) {
			return new ResponseEntity<>("el id de mesero no existe", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(mesa, HttpStatus.CREATED);
		}

	}

	// http://localhost:9000/MesasWs/editar
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Mesas mesa) {

		boolean response = mesasImp.editar(mesa);

		if (response == false)
			return new ResponseEntity<>("El id no se encuentra en la BD", HttpStatus.OK);
		else
			return new ResponseEntity<>(mesa, HttpStatus.CREATED);
	}

	// http://localhost:9000/MesasWs/eliminar
	@PostMapping(path = "eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Mesas mesa) {

		boolean response = mesasImp.eliminar(mesa.getIdMesa());

		if (response == false)
			return new ResponseEntity<>("El id no se encuentra en la BD", HttpStatus.OK);
		else
			return new ResponseEntity<>("Eliminado el registro con id " + mesa.getIdMesa(), HttpStatus.CREATED);
	}
}
