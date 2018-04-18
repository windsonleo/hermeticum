package com.teccsoluction.hermeticum.servico;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teccsoluction.hermeticum.dao.EstoqueDAO;
import com.teccsoluction.hermeticum.entidade.Estoque;
import com.teccsoluction.hermeticum.framework.AbstractEntityService;



@Service("EstoqueService")
@Transactional
public class EstoqueServicoImpl extends  AbstractEntityService<Estoque>{

	
	@Autowired
	private EstoqueDAO dao;
	
	
	
	
	public EstoqueServicoImpl() {
		  super(Estoque.class, "estoque");
		// TODO Auto-generated constructor stub
	}




	@Override
	protected JpaRepository<Estoque, UUID> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}




	@Override
	protected void validateSave(Estoque post) {
		// TODO Auto-generated method stub
		
	}




	@Override
	protected String getIdEntity(Estoque entity) {
		// TODO Auto-generated method stub
		return entity.getId().toString();
	}




	@Override
	protected void validateEdit(Estoque post) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public List<Estoque> findAllNew() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}




	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}





}
