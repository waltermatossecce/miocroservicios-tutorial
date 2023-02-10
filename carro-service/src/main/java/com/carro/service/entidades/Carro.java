package com.carro.service.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CARRO")
@Data
public class Carro {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_carro")
    @SequenceGenerator(sequenceName = "SEQ_CARRO", allocationSize = 1, name = "seq_carro")
	@Column(name="ID")
	private int id;
	@Column(name="MARCA")
	private String marca;
	@Column(name="MODELO")
	private String modelo;
	@Column(name="USUARIOID")
	private int usuarioId;
}
