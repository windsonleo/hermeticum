package com.teccsoluction.hermeticum.servico;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teccsoluction.hermeticum.dao.UsuarioDAO;
import com.teccsoluction.hermeticum.entidade.Usuario;
import com.teccsoluction.hermeticum.framework.AbstractEntityService;
	


@Service("usuarioService")
@Transactional
public class UsuarioServicoImpl extends  AbstractEntityService<Usuario> {

	


	@Autowired
	private UsuarioDAO dao;
	
	
	public UsuarioServicoImpl() {
		super(Usuario.class, "usuario");
		// TODO Auto-generated constructor stub
	}
	
	
	

	
	public boolean authenticate(String username, String password) {

		Usuario user = this.findByUsername(username);
		if(user == null){
			return false;
		}else{
			if(password.equals(user.getPassword())) return true;
			else return false;
		}
		
	}


	@Override
	protected void validateSave(Usuario post) {
		// TODO Auto-generated method stub
		
	}





	@Override
	protected String getIdEntity(Usuario entity) {
		// TODO Auto-generated method stub
		return entity.getId().toString();
	}





	@Override
	protected void validateEdit(Usuario post) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public List<Usuario> findAllNew() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}







	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}





	@Override
	protected JpaRepository<Usuario, UUID> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}



    public Usuario findByUsername(String username) {

        return dao.findByUsername(username);
    }



}
