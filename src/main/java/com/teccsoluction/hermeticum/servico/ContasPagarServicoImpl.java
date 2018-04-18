package com.teccsoluction.hermeticum.servico;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teccsoluction.hermeticum.dao.ContasPagarDAO;
import com.teccsoluction.hermeticum.entidade.ContasPagar;
import com.teccsoluction.hermeticum.framework.AbstractEntityService;




@Service("ContasPagarService")
@Transactional
public class ContasPagarServicoImpl extends  AbstractEntityService<ContasPagar>{

	
	@Autowired
	private ContasPagarDAO dao;
	
	
	
	
	public ContasPagarServicoImpl() {
		  super(ContasPagar.class, "contaspagar");
		// TODO Auto-generated constructor stub
	}




	@Override
	protected JpaRepository<ContasPagar, UUID> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}




	@Override
	protected void validateSave(ContasPagar post) {
		// TODO Auto-generated method stub
		
	}




	@Override
	protected String getIdEntity(ContasPagar entity) {
		// TODO Auto-generated method stub
		return entity.getId().toString();
	}




	@Override
	protected void validateEdit(ContasPagar post) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public List<ContasPagar> findAllNew() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}




	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}




}
