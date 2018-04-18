package com.teccsoluction.hermeticum.servico;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teccsoluction.hermeticum.dao.ContasReceberDAO;
import com.teccsoluction.hermeticum.entidade.ContasReceber;
import com.teccsoluction.hermeticum.framework.AbstractEntityService;



@Service("ContasReceberService")
@Transactional
public class ContasReceberServicoImpl extends  AbstractEntityService<ContasReceber>{

	
	@Autowired
	private ContasReceberDAO dao;
	
	
	
	
	public ContasReceberServicoImpl() {
		  super(ContasReceber.class, "ContasReceber");
		// TODO Auto-generated constructor stub
	}




	@Override
	protected JpaRepository<ContasReceber, UUID> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}




	@Override
	protected void validateSave(ContasReceber post) {
		// TODO Auto-generated method stub
		
	}




	@Override
	protected String getIdEntity(ContasReceber entity) {
		// TODO Auto-generated method stub
		return entity.getId().toString();
	}




	@Override
	protected void validateEdit(ContasReceber post) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public List<ContasReceber> findAllNew() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}




	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}





}
