package com.teccsoluction.hermeticum.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teccsoluction.hermeticum.entidade.Fornecedor;
import com.teccsoluction.hermeticum.entidade.Produto;



@Repository
public interface FornecedorDAO extends JpaRepository<Fornecedor, UUID>{

	
    @Query("SELECT p FROM Fornecedor p where p.nomefantasia=:nomefantasia")
    public Fornecedor getFornecedorPorNome(@Param("nomefantasia") String nomefantasia);

}
