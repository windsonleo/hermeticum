package com.teccsoluction.hermeticum.servico;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teccsoluction.hermeticum.dao.FuncionarioDAO;
import com.teccsoluction.hermeticum.entidade.Funcionario;
import com.teccsoluction.hermeticum.framework.AbstractEntityService;





@Service("funcionarioService")
@Transactional
public class FuncionarioServicoImpl extends  AbstractEntityService<Funcionario>{

	
	@Autowired
	private FuncionarioDAO dao;
	
	
	public FuncionarioServicoImpl() {
		  super(Funcionario.class, "funcionario");
		// TODO Auto-generated constructor stub
	}


	@Override
	protected JpaRepository<Funcionario, UUID> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}


	@Override
	protected void validateSave(Funcionario post) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected String getIdEntity(Funcionario entity) {
		// TODO Auto-generated method stub
		return entity.getId().toString();
	}


	@Override
	protected void validateEdit(Funcionario post) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Funcionario> findAllNew() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}


	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}






}
