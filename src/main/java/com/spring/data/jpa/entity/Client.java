package com.spring.data.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
//@NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
@NamedQueries({
		@NamedQuery(name = "Client.findAllSortedByNomPrenom", query = "SELECT c FROM Client c ORDER BY c.nom, c.prenom"),
		@NamedQuery(name = "Client.findOneByNumeroV1", query = "SELECT c FROM Client c WHERE c.numero = ?1"),
		@NamedQuery(name = "Client.findOneByNumeroV2", query = "SELECT c FROM Client c WHERE c.numero = :numero"),
		@NamedQuery(name = "Client.findAllByNomAndDepartement", query = "SELECT c FROM Client c WHERE c.nom = :nom and c.ville.codePostal like CONCAT(:codeDep,'%')") })

public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_client")
	private int idClient;

	private String adresse;

	private String civilite;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_naissance")
	private Date dateNaissance;

	private String nom;

	private String numero;

	private String prenom;

	// uni-directional many-to-one association to Ville
	@ManyToOne
	@JoinColumn(name = "id_ville")
	private Ville ville;

	@Override
	public String toString() {
		return "Client [civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", ville=" + ville + "]";
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

}
