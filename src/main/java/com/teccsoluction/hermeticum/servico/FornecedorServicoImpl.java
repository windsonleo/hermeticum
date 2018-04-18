package com.teccsoluction.hermeticum.servico;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teccsoluction.hermeticum.dao.FornecedorDAO;
import com.teccsoluction.hermeticum.entidade.Fornecedor;
import com.teccsoluction.hermeticum.framework.AbstractEntityService;




@Service("fornecedorService")
@Transactional
public class FornecedorServicoImpl extends  AbstractEntityService<Fornecedor>{

	
	@Autowired
	private FornecedorDAO dao;
	
	
	public FornecedorServicoImpl() {
		  super(Fornecedor.class, "produto");
		// TODO Auto-generated constructor stub
	}


	@Override
	protected JpaRepository<Fornecedor, UUID> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}


	@Override
	protected void validateSave(Fornecedor post) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected String getIdEntity(Fornecedor entity) {
		// TODO Auto-generated method stub
		return entity.getId().toString();
	}


	@Override
	protected void validateEdit(Fornecedor post) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Fornecedor> findAllNew() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}


	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}



	


}
