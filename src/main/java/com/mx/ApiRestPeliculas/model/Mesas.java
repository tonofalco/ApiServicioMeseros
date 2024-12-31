package com.mx.ApiRestPeliculas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 CREATE TABLE MESAS(
	ID NUMBER PRIMARY KEY,
	NUM_MESA NUMBER NOT NULL,
	NUM_SILLAS NUMBER NOT NULL,
	ID_MESERO NUMBER NOT NULL,
	FOREIGN KEY(ID_MESERO) REFERENCES MESERO(ID)
);
 * */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "MESAS")
public class Mesas {
	
	@Id
	@Column(name = "ID")
	private Long idMesa;
	
	@Column(name = "NUM_MESA")
	private Long numMesa;
	
	@Column(name = "NUM_SILLAS")
	private Long numSillas;
	
	// Cardinalidad -- Muchas mesas pertenecen a un mesero
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MESERO")
	Mesero mesero;

}
