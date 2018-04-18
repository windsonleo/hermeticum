package com.teccsoluction.hermeticum.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teccsoluction.hermeticum.entidade.Cliente;





@Repository
public interface ClienteDAO extends JpaRepository<Cliente, UUID>{
	
    @Query("SELECT p FROM Cliente p where p.nome=:nome")
    public Cliente getClientePorNome(@Param("nome") String nome);

}
