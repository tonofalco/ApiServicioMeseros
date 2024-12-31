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

import com.mx.ApiRestPeliculas.model.Mesero;
import com.mx.ApiRestPeliculas.service.MeseroServImp;

@RestController
@RequestMapping(path = "MeseroWs")
@CrossOrigin
public class MeseroWs {

	@Autowired
	MeseroServImp meseroImp;

	// http://localhost:9000/MeseroWs/listar
	@GetMapping(path = "listar")
	public List<Mesero> listar() {
		return meseroImp.listar();
	}

	// http://localhost:9000/MeseroWs/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Mesero mesero) {

		boolean response = meseroImp.guardar(mesero);

		if (response == true)
			return new ResponseEntity<>("El nombre o el ID ya exiten. ingresa otro", HttpStatus.OK);
		else
			return new ResponseEntity<>(mesero, HttpStatus.CREATED);

	}

	// http://localhost:9000/MeseroWs/buscar
	@PostMapping(path = "buscar")
	public Mesero buscar(@RequestBody Mesero mesero) {
		return meseroImp.buscar(mesero.getIdMesero());
	}

	// http://localhost:9000/MeseroWs/editar
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Mesero modelo) {

		boolean response = meseroImp.editar(modelo);

		if (response == false)
			return new ResponseEntity<>("El id no se encuentra en la BD", HttpStatus.OK);
		else
			return new ResponseEntity<>(modelo, HttpStatus.CREATED);
	}

	// http://localhost:9000/MarcasWs/eliminar
	@PostMapping(path = "eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Mesero modelo) {

		boolean response = meseroImp.eliminar(modelo.getIdMesero());

		if (response == false)
			return new ResponseEntity<>("El id no se encuentra en la BD", HttpStatus.OK);
		else
			return new ResponseEntity<>("Eliminado el registro con id " + modelo.getIdMesero(), HttpStatus.CREATED);
	}
}
