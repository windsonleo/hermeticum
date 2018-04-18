package com.teccsoluction.hermeticum.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teccsoluction.hermeticum.entidade.Usuario;




@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, UUID>{
	
//	Usuario findByEmail(String email);
	
    Usuario findByUsername(String username);


}
