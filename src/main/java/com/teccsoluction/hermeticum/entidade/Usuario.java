package com.teccsoluction.hermeticum.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.teccsoluction.hermeticum.framework.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Usuario")
public class Usuario extends BaseEntity implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	
	private String password;
	
	private String foto;
	
	
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return username;
	}
	

}
