package com.teccsoluction.hermeticum.controle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.teccsoluction.hermeticum.entidade.Cliente;
import com.teccsoluction.hermeticum.entidade.Fornecedor;
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
public class AddFornecedorController  implements Initializable {


    
	@FXML
	private TextField txtfornecedor;
	
	@FXML
    private JFXButton btcancela;	
	
	@FXML
    private JFXButton btconfirma;
	
	@FXML
	private TextField txtfornecedorescolhido;
	
//	@Autowired
//	private ClienteServicoImpl ClienteService;
	
	
	public Fornecedor fornecedor;
	
	
	
    
    
    public AddFornecedorController() {
     
        

    }
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		 
	}



	
	public void SetTxtFields(List<Fornecedor> fornecedores){
		

			TextFields.bindAutoCompletion(txtfornecedor,fornecedores);
					
		
//					txtclienteescolhido.setText(txtcliente.getText());
					btconfirma.setDisable(true);
					btcancela.setDisable(false);

			        
			        

		
		
	}
	
	
	
	public void SetTxtFieldClienteEscolhido(Fornecedor fornecedor){
		
		btconfirma.setDisable(false);
		btcancela.setDisable(false);
		
		
	}	
	
	
	public Fornecedor getFornecedorEscolhido(){
			
		fornecedor = new Fornecedor();
		fornecedor.setNomefantasia(txtfornecedor.getText());

		return fornecedor;
	}	
	
	
	
	public void exit() {
		Platform.exit();
    }
	
	

	public void initialize(){
				
		
		txtfornecedorescolhido.setText(txtfornecedor.getText());
		btconfirma.setDisable(false);
		btcancela.setDisable(false);
		
		
	}	


	

}
