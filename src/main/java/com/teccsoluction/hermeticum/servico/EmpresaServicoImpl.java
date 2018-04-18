package com.teccsoluction.hermeticum.servico;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teccsoluction.hermeticum.dao.EmpresaDAO;
import com.teccsoluction.hermeticum.entidade.Empresa;
import com.teccsoluction.hermeticum.framework.AbstractEntityService;





@Service("empresaService")
@Transactional
public class EmpresaServicoImpl extends  AbstractEntityService<Empresa>{


	@Autowired
	private EmpresaDAO dao;
	
	
	
	public EmpresaServicoImpl() {
		super(Empresa.class, "empresa");
		// TODO Auto-generated constructor stub
	}



	@Override
	protected JpaRepository<Empresa, UUID> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}



	@Override
	protected void validateSave(Empresa post) {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected String getIdEntity(Empresa entity) {
		// TODO Auto-generated method stub
		return entity.getId().toString();
	}



	@Override
	protected void validateEdit(Empresa post) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<Empresa> findAllNew() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}



	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}
	
	


}
