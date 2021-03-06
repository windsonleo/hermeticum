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
public class AddClienteController  implements Initializable {


    
	@FXML
	private TextField txtcliente;
	
	@FXML
    private JFXButton btcancela;	
	
	@FXML
    private JFXButton btconfirma;
	
	@FXML
	private TextField txtclienteescolhido;
	
//	@Autowired
//	private ClienteServicoImpl ClienteService;
	
	
	public Cliente cliente;
	
	
	
    
    
    public AddClienteController() {
     
        

    }
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		 
	}



	
	public void SetTxtFields(List<Cliente> clientes){
		

			TextFields.bindAutoCompletion(txtcliente,clientes);
					
		
//					txtclienteescolhido.setText(txtcliente.getText());
					btconfirma.setDisable(true);
					btcancela.setDisable(false);

			        
			        

		
		
	}
	
	
	
	public void SetTxtFieldClienteEscolhido(Cliente cliente){
		
		btconfirma.setDisable(false);
		btcancela.setDisable(false);
		
		
	}	
	
	
	public Cliente getClienteEscolhido(){
			
			cliente = new Cliente();
			cliente.setNome(txtcliente.getText());

		return cliente;
	}	
	
	
	
	public void exit() {
		Platform.exit();
    }
	
	

	public void initialize(){
				
		
		txtclienteescolhido.setText(txtcliente.getText());
		btconfirma.setDisable(false);
		btcancela.setDisable(false);
		
		
	}	


	

}
