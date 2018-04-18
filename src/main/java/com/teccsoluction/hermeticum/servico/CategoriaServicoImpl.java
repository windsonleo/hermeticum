package com.teccsoluction.hermeticum.servico;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teccsoluction.hermeticum.dao.CategoriaDAO;
import com.teccsoluction.hermeticum.entidade.Categoria;
import com.teccsoluction.hermeticum.framework.AbstractEntityService;





@Service("categoriaService")
@Transactional
public class CategoriaServicoImpl extends  AbstractEntityService<Categoria>{

	
	@Autowired
	private CategoriaDAO dao;
	
	
	public CategoriaServicoImpl() {
		  super(Categoria.class, "categoria");
		// TODO Auto-generated constructor stub
	}


	@Override
	protected JpaRepository<Categoria, UUID> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}


	@Override
	protected void validateSave(Categoria post) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected String getIdEntity(Categoria entity) {
		// TODO Auto-generated method stub
		return entity.getId().toString();
	}


	@Override
	protected void validateEdit(Categoria post) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Categoria> findAllNew() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}


	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}






}
