package com.teccsoluction.hermeticum.servico;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teccsoluction.hermeticum.dao.ProdutoDAO;
import com.teccsoluction.hermeticum.entidade.Produto;
import com.teccsoluction.hermeticum.framework.AbstractEntityService;




@Service("produtoService")
@Transactional
public class ProdutoServicoImpl extends  AbstractEntityService<Produto>{

	
	@Autowired
	private ProdutoDAO dao;
	
	
	public ProdutoServicoImpl() {
		  super(Produto.class, "produto");
		// TODO Auto-generated constructor stub
	}

    public Produto getProdutoPorCode( String nome){
    
    return dao.getProdutoPorCode(nome);
	

}



	@Override
	protected JpaRepository<Produto, UUID> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}







	@Override
	protected void validateSave(Produto post) {
		// TODO Auto-generated method stub
		
	}







	@Override
	protected String getIdEntity(Produto entity) {
		// TODO Auto-generated method stub
		return entity.getId().toString();
	}







	@Override
	protected void validateEdit(Produto post) {
		// TODO Auto-generated method stub
		
	}







	@Override
	public List<Produto> findAllNew() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}







	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}
    
}
