package com.teccsoluction.hermeticum.servico;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teccsoluction.hermeticum.dao.RecebimentoDAO;
import com.teccsoluction.hermeticum.entidade.Recebimento;
import com.teccsoluction.hermeticum.framework.AbstractEntityService;



@Service("RecebimentoService")
@Transactional
public class RecebimentoServicoImpl extends  AbstractEntityService<Recebimento>{

	
	@Autowired
	private RecebimentoDAO dao;
	
	
	
	
	public RecebimentoServicoImpl() {
		  super(Recebimento.class, "recebimento");
		// TODO Auto-generated constructor stub
	}




	@Override
	protected JpaRepository<Recebimento, UUID> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}




	@Override
	protected void validateSave(Recebimento post) {
		// TODO Auto-generated method stub
		
	}




	@Override
	protected String getIdEntity(Recebimento entity) {
		// TODO Auto-generated method stub
		return entity.getId().toString();
	}




	@Override
	protected void validateEdit(Recebimento post) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public List<Recebimento> findAllNew() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}




	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}



}
