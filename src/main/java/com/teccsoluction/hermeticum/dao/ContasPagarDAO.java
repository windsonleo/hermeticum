package com.teccsoluction.hermeticum.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teccsoluction.hermeticum.entidade.ContasPagar;





@Repository
public interface ContasPagarDAO extends JpaRepository<ContasPagar, UUID>{
	

}
