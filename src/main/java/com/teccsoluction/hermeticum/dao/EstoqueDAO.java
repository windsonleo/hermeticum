package com.teccsoluction.hermeticum.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teccsoluction.hermeticum.entidade.Estoque;




@Repository
public interface EstoqueDAO extends JpaRepository<Estoque, UUID>{
	

}
