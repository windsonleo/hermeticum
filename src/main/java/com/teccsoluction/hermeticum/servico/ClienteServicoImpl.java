package com.teccsoluction.hermeticum.servico;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teccsoluction.hermeticum.dao.ClienteDAO;
import com.teccsoluction.hermeticum.entidade.Cliente;
import com.teccsoluction.hermeticum.entidade.Produto;
import com.teccsoluction.hermeticum.framework.AbstractEntityService;

	



@Service("clienteService")
@Transactional
public class ClienteServicoImpl extends  AbstractEntityService<Cliente>{

	
	@Autowired
	private ClienteDAO dao;
	
	
	public ClienteServicoImpl() {
		  super(Cliente.class, "cliente");
		// TODO Auto-generated constructor stub
	}



	public Cliente edit(Cliente categoria){
		
		getDao().saveAndFlush(categoria);
		
		return categoria;
	}



	@Override
	protected JpaRepository<Cliente, UUID> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}



	@Override
	protected void validateSave(Cliente post) {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected String getIdEntity(Cliente entity) {
		// TODO Auto-generated method stub
		return entity.getId().toString();
	}



	@Override
	protected void validateEdit(Cliente post) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<Cliente> findAllNew() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}



	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}

	

    public Cliente getClientePorNome( String nome){
        
    return dao.getClientePorNome(nome);
	

}




}
