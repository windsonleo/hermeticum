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
	
	@Autowired
	private ClienteServicoImpl ClienteService;
	
	
	public Cliente cliente;
	
	
	
    
    
    public AddClienteController() {
     
        

    }
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
//		 System.out.println(pedidovenda);
		
		
		

		
		 
		 
	}



	
	public void SetTxtFields(List<Cliente> clientes){
		
//        this.txtsubtotal.setText(pv.getTotalVenda().toString());
		
		
		
			TextFields.bindAutoCompletion(txtcliente,clientes);
					
		
					txtclienteescolhido.setText(txtcliente.getText());
					btconfirma.setDisable(true);
					btcancela.setDisable(false);

			        
			        

		
		
	}
	
	
	
	public void SetTxtFieldClienteEscolhido(Cliente cliente){
		
//      this.txtsubtotal.setText(pv.getTotalVenda().toString());
		
		
//		txtcliente.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
//			   
//				if (! isNowFocused) {
//					
//					this.cliente = ClienteService.getClientePorNome(txtcliente.getText());
//			        
//					txtclienteescolhido.setText(cliente.getNome());
//					

//			        
//			        
//			    }
//			});
		
		
//		txtclienteescolhido.setText(cliente.getNome());
		btconfirma.setDisable(false);
		btcancela.setDisable(false);
		
		
	}	
	
	
	public Cliente getClienteEscolhido(){
		
//      this.txtsubtotal.setText(pv.getTotalVenda().toString());
		
//		if(venda.getCliente() != null) {
//					
//					cliente = ClienteService.getClientePorNome(venda.getCliente().getNome());
//			        
//					txtclienteescolhido.setText(cliente.getNome());
//					
//					btconfirma.setDisable(false);
//					btcancela.setDisable(false);
//					
//					return cliente;
//					
//					
//					
//		}else {
//			
			cliente = new Cliente();
			cliente.setNome(txtcliente.getText());
//			venda.setCliente(cliente);
//			
//			btconfirma.setDisable(false);
//			btcancela.setDisable(false);
//			
//			return cliente;
//			
//			
//		}

		
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
