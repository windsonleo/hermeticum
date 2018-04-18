package com.teccsoluction.hermeticum.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teccsoluction.hermeticum.entidade.Produto;




@Repository
public interface ProdutoDAO extends JpaRepository<Produto, UUID>{
	
    @Query("SELECT p FROM Produto p where p.codebar=:codebar")
    public Produto getProdutoPorCode(@Param("codebar") String codebar);
	

}
