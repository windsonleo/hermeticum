package com.teccsoluction.hermeticum.controle;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.teccsoluction.hermeticum.conf.StageManager;
import com.teccsoluction.hermeticum.entidade.Usuario;
import com.teccsoluction.hermeticum.servico.UsuarioServicoImpl;
import com.teccsoluction.hermeticum.view.FxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.shape.Sphere;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ram Alapure
 * @since 05-04-2017
 */

@Getter
@Setter
@Controller
public class LoginController implements Initializable{

	@FXML
    private JFXButton btnLogin,btnEntrar,btnLembrar;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField username;

 
    private Usuario usuario;
    
    @FXML
    private Label lblLogin;
    
    @Autowired
    private UsuarioServicoImpl userService;
    
    @Lazy
    @Autowired
    private StageManager stageManager;
    
    @FXML
    private ProgressIndicator progress;
    
    
    public MainController maincontrol;
    
    
    @FXML
    private Sphere esfera = new Sphere();
    
        
	@FXML
    public void login(ActionEvent event) throws IOException{
		
		
		usuario = userService.findByUsername((getUsername()));
		
		if(userService.authenticate(getUsername(), getPassword())){
			
			stageManager.SetaUsuarioStage(usuario);
    		    		
    		stageManager.switchScene(FxmlView.MAIN);
			
//			PegarUsuario();
			

//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
//
//	        Parent root =loader.load();
//	        
//	      maincontrol = (MainController)loader.getController();
//	      
//	      Label nomeusu =  new Label();
//	      nomeusu.setText(usuario.getFirstName());
//			
//			maincontrol.setUsuarioUp(usuario);
//			maincontrol.setNomeusuario(nomeusu);
//   
//		
//	         javafx.stage.Window win = new Popup() ;
//		
//			Stage s1 = new Stage();
//			s1.initOwner(win);
//			s1.initModality(Modality.APPLICATION_MODAL);
//			
//			 Scene scene = new Scene(root);
//			 
//			 s1.setScene(scene);
//			 s1.show();

		
		
	    
			
    		
    	}else{
    		
    		lblLogin.setText("Falha na Autenticação.");
    		lblLogin.setVisible(true);
    	}
		
		
		
		
    }
	
	public String getPassword() {
		return password.getText();
	}

	public String getUsername() {
		return username.getText();
	}
	
	public Sphere getEsfera() {
		
		
		
		return esfera;
	}
	
	
	public void SetEsfera(Sphere esfera){
		
		this.esfera = esfera;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	
		
	
	}
	
//	private void PegarUsuario() {
//	
//	
////	try{
////		
////		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
////
////        Parent root =loader.load();
////        
////      maincontrol = (MainController)loader.getController();
////      maincontrol.SetarUsuarioLogin(usuario);
//      
//      stageManager.switchScene(FxmlView.MAIN);
//      
//      
////      maincontrol
//      
//      
////      
////      Label nomeusu =  new Label();
////      nomeusu.setText(usuario.getFirstName());
////		
////		maincontrol.setUsuarioUp(usuario);
////		maincontrol.setNomeusuario(nomeusu);
////
////	
//         javafx.stage.Window win = new Popup() ;
//	
//		Stage s1 = new Stage();
//		s1.initOwner(win);
//		s1.initModality(Modality.WINDOW_MODAL);
//		
//		 Scene scene = new Scene(root);
//		 
//		 s1.setScene(scene);
//		 s1.show();
//      
////      Stage stage=new Stage();
////      stage.setScene(new Scene(root));
////      stage.show();
//    		 
//		}catch (Exception e) {
//
//		System.out.println("erro Login CONTROL:"+ e);
//		
//		}
//		
//	
//}
	
	
	
    }

