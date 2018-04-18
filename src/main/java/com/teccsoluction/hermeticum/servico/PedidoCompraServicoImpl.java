package com.teccsoluction.hermeticum.servico;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teccsoluction.hermeticum.dao.PedidoCompraDAO;
import com.teccsoluction.hermeticum.entidade.PedidoCompra;
import com.teccsoluction.hermeticum.framework.AbstractEntityService;



@Service("PedidoCompraService")
@Transactional
public class PedidoCompraServicoImpl extends  AbstractEntityService<PedidoCompra>{

	
	@Autowired
	private PedidoCompraDAO dao;
	
	
	
	
	public PedidoCompraServicoImpl() {
		  super(PedidoCompra.class, "pedidocompra");
		// TODO Auto-generated constructor stub
	}




	@Override
	protected JpaRepository<PedidoCompra, UUID> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}




	@Override
	protected void validateSave(PedidoCompra post) {
		// TODO Auto-generated method stub
		
	}




	@Override
	protected String getIdEntity(PedidoCompra entity) {
		// TODO Auto-generated method stub
		return entity.getId().toString();
	}




	@Override
	protected void validateEdit(PedidoCompra post) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public List<PedidoCompra> findAllNew() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}




	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}



	



}
