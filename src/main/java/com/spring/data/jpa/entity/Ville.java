package com.spring.data.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQuery(name = "Ville.findAll", query = "SELECT v FROM Ville v")
public class Ville implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ville")
	private int idVille;

	@Column(name = "code_pays")
	private String codePays;

	@Column(name = "code_postal")
	private String codePostal;

	@Column(name = "id_maire")
	private int idMaire;

	@Override
	public String toString() {
		return "Ville [nom=" + nom + "]";
	}

	private String nom;

	public int getIdVille() {
		return idVille;
	}

	public void setIdVille(int idVille) {
		this.idVille = idVille;
	}

	public String getCodePays() {
		return codePays;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public int getIdMaire() {
		return idMaire;
	}

	public void setIdMaire(int idMaire) {
		this.idMaire = idMaire;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
