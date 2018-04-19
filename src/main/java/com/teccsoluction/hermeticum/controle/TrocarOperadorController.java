package com.teccsoluction.hermeticum.controle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.teccsoluction.hermeticum.entidade.Cliente;
import com.teccsoluction.hermeticum.entidade.PedidoVenda;
import com.teccsoluction.hermeticum.entidade.Produto;
import com.teccsoluction.hermeticum.entidade.Usuario;
import com.teccsoluction.hermeticum.servico.ClienteServicoImpl;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Controller
public class TrocarOperadorController  implements Initializable {


    
	@FXML
	private TextField txtusuario;
	
	@FXML
    private JFXButton btcancela;	
	
	@FXML
    private JFXButton bttrocar;
	
	@FXML
	private TextField txtsenha;
	
	
	public Usuario usuario;
	
	
	
    
    
    public TrocarOperadorController() {
     
        

    }
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
 
		 
	}

	
	public Usuario GetCampos(){
		
		usuario.setUsername(txtusuario.getText());
		usuario.setPassword(txtsenha.getText());
		
		bttrocar.setDisable(false);
		btcancela.setDisable(false);
		
		
		return usuario;
	}	
	

	public void exit() {
		Platform.exit();
    }
	
	

	public void initialize(){
			
		usuario.setUsername(txtusuario.getText());
		usuario.setPassword(txtsenha.getText());
		
		bttrocar.setDisable(false);
		btcancela.setDisable(false);

		
	}	
	
	
	


	

}
