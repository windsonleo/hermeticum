package com.teccsoluction.hermeticum.servico;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teccsoluction.hermeticum.dao.CategoriaDAO;
import com.teccsoluction.hermeticum.dao.ItemDAO;
import com.teccsoluction.hermeticum.entidade.Categoria;
import com.teccsoluction.hermeticum.entidade.Item;
import com.teccsoluction.hermeticum.framework.AbstractEntityService;





@Service("itemService")
@Transactional
public class ItemServicoImpl extends  AbstractEntityService<Item>{

	
	@Autowired
	private ItemDAO dao;
	
	
	public ItemServicoImpl() {
		  super(Item.class, "item");
		// TODO Auto-generated constructor stub
	}


	@Override
	protected JpaRepository<Item, UUID> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}


	@Override
	protected void validateSave(Item post) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected String getIdEntity(Item entity) {
		// TODO Auto-generated method stub
		return entity.getId().toString();
	}


	@Override
	protected void validateEdit(Item post) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Item> findAllNew() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}


	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}







}
