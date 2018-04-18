package com.teccsoluction.hermeticum.servico;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teccsoluction.hermeticum.dao.PedidoVendaDAO;
import com.teccsoluction.hermeticum.entidade.PedidoVenda;
import com.teccsoluction.hermeticum.framework.AbstractEntityService;



@Service("PedidoVendaService")
@Transactional
public class PedidoVendaServicoImpl extends  AbstractEntityService<PedidoVenda>{

	
	@Autowired
	private PedidoVendaDAO dao;
	
	
	
	
	public PedidoVendaServicoImpl() {
		  super(PedidoVenda.class, "pedidovenda");
		// TODO Auto-generated constructor stub
	}




	@Override
	protected JpaRepository<PedidoVenda, UUID> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}




	@Override
	protected void validateSave(PedidoVenda post) {
		// TODO Auto-generated method stub
		
	}




	@Override
	protected String getIdEntity(PedidoVenda entity) {
		// TODO Auto-generated method stub
		return entity.getId().toString();
	}




	@Override
	protected void validateEdit(PedidoVenda post) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public List<PedidoVenda> findAllNew() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}




	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}



	


}
