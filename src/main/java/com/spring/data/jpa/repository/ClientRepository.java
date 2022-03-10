package com.spring.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.data.jpa.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	public List<Client> findByNomStartingWith(String nom, Sort ordreTri);

	public List<Client> findAllSortedByNomPrenom();

	public Client findOneByNumeroV1(String numero);

	public Client findOneByNumeroV2(@Param("numero") String numero);

	public List<Client> findAllByNomAndDepartement(@Param("nom") String nom, @Param("codeDep") String codeDepartement);

}
