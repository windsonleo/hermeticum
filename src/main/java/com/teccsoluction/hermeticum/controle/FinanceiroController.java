package com.teccsoluction.hermeticum.controle;

import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.teccsoluction.hermeticum.conf.StageManager;
import com.teccsoluction.hermeticum.entidade.Usuario;
import com.teccsoluction.hermeticum.view.FxmlView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@Controller
public class FinanceiroController implements Initializable{

   
	@Lazy
    @Autowired
    private StageManager stageManager;
	
	@FXML
	private JFXButton btempresa;
	
	
	@FXML
	private JFXButton btfuncionario;
	
	@FXML
	private JFXButton btcliente;
	
	@FXML
	private JFXButton btfornecedor;
	
	@FXML
	private JFXButton btcategoria;
	
	@FXML
	private JFXButton btproduto;
	
	@FXML
	private JFXButton btconfiguracao;
	
	
	@FXML
	private JFXButton btcompras;
	
	@FXML
	private JFXButton btvendas;
	
	
	@FXML
	private JFXButton btestoque;
	
	
	
	@FXML
	private JFXButton btpdv;
	

	@FXML
    private JFXButton btusuario;
	
	@FXML
    private JFXButton btfinanceiro;
	
	
    private Usuario usuarioUp;
	
//   	
	

	@FXML
    private Label empresanome;	

//	@FXML
//    private Label empresadata;
	
	   @FXML
	    private Image imgsempresa;
	    
	    @FXML
	    private  ImageView imgviewempresa;
	
//	@FXML
//    private Label nomeusuario;
	
    @FXML
    private Image imgsusuario;
    
    @FXML
    private  ImageView imgviewusuario;
	
	
	@FXML
    private MenuItem sair;
	
	
    @FXML
    private Circle circulo;
    
    @FXML
    private Circle circuloemp;
    
    
    @FXML
    private JFXButton bttrocarusuario;
    
    @FXML
    private JFXButton bteditarusuario;
    
    
	@FXML
    private JFXButton btexit;
	
	
	public LoginController logincontrol;
    
	@FXML
    private Label txtusuarionome;
	
	@FXML
    private Label txthora;
	
	@FXML
    private Label txtcontext;

	
    
    public FinanceiroController() {
		// TODO Auto-generated constructor stub
	}
    
	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
    }

	/**
	 * Logout and go to the login page
	 */
    @FXML
    private void logout(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.LOGIN);    	
    }
//    
    @FXML
    private void trocarusuario(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.LOGIN);    	
    }
//    
//    
    @FXML
    private void editarusuario(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.MOVIMENTACAOUSUARIO);    	
    }
//    
    

   
	@FXML
	private void movimentacaoempresa(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOEMPRESA);  
		
		
		
	}
	
	
	   
		@FXML
		private void movimentacaopdv(){
			
			  stageManager.switchScene(FxmlView.PDV);  
			
			
			
		}
	
	@FXML
	private void movimentacaofornecedor(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOFORNECEDOR);  
		
		
		
	}
	
	@FXML
	private void movimentacaofuncionario(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOFUNCIONARIO);  
		
		
		
	}
	
	
	@FXML
	private void movimentacaocliente(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOCLIENTE);  
		
		
		
	}
//	
//	
//	
//	
	@FXML
	private void movimentacaocategoria(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOCATEGORIA);  
		
		
		
	}
//	
//	
	@FXML
	private void movimentacaoproduto(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOPRODUTO);  
		
		
		
	}
//	
//	
//	
//	@FXML
//	private void movimentacaoformapagamento(){
//		
//		  stageManager.switchScene(FxmlView.MOVIMENTACAOFORMAPAGAMENTO);  
//		
//		
//		
//	}
//	
	@FXML
	private void movimentacaoconfiguracao(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOCONFIGURACAO);  
		
		
		
	}
//	
//	  
//	@FXML
//	private void movimentacaocaixa(){
//		
//		  stageManager.switchScene(FxmlView.MOVIMENTACAOCAIXA);  
//		
//		
//		
//	}
//	
//	
	@FXML
	private void movimentacaousuario(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOUSUARIO);  
		
		
		
	}
//	
//	@FXML
//	private void movimentacaopagamento(){
//		
//		  stageManager.switchScene(FxmlView.MOVIMENTACAOPAGAMENTO);  
//		
//		
//		
//	}
//	
//	
	@FXML
	private void movimentacaoestoque(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOESTOQUE);  
		
		
		
	}
//	
	@FXML
	private void movimentacaocontasreceber(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOCONTASRECEBER);  
		
		
		
	}
//	
//	
	@FXML
	private void movimentacaocontaspagar(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOCONTASPAGAR);  
		
		
		
	}
//	
	@FXML
	private void movimentacaorecebimento(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAORECEBIMENTO);  
		
		
		
	}
//	
//	
	@FXML
	private void movimentacaopedidocompra(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOPEDIDOCOMPRA);  
		
		
		
	}
//	
	@FXML
	private void movimentacaopedidovenda(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOPEDIDOVENDA);  
		
		
		
	}
	
	@FXML
	private void movimentacaofinanceiro(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOFINANCEIRO);  
		
		
		
	}
	
	
	private void CarregarHeader(){
		
        ///////////////important code starts from here
//        circulo = new Circle(250,250,120);
        circulo.setStroke(Color.DARKGRAY);
      
//        imgs = new Image(imgs.impl_getUrl(),false);
        
        	
//          imgs = new Image("https://photos.app.goo.gl/DiyH0fkOrqybkk4K2",false);

//        imgview = new ImageView(imgs.impl_getUrl() + "ia.jpg");
        
        
        
       
        circulo.setFill(new ImagePattern(imgsusuario));
        circulo.setEffect(new DropShadow(+25d, 0d, +2d, Color.AZURE));
        
        circuloemp.setStroke(Color.DARKGRAY);
        circuloemp.setFill(new ImagePattern(imgsempresa));
        circuloemp.setEffect(new DropShadow(+25d, 0d, +2d, Color.AZURE));
//
        imgviewempresa.setVisible(false);
        imgviewusuario.setVisible(false);
        
        txthora.setText(getHora());
        txtcontext.setText("Financeiro");
        empresanome.setText("Empresa Tal");
        
        
		
		
		
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		CarregarHeader();
		
		SetarUsuarioLogin();
		
		

		
	}

	public void SetarUsuarioLogin() {
				
		usuarioUp = stageManager.GetaUsuarioStage();
		
		txtusuarionome.setText(usuarioUp.getUsername());
	
		
	}
	
	 public String getHora() {
			// cria um StringBuilder
			StringBuilder sb = new StringBuilder();
			// cria um GregorianCalendar que vai conter a hora atual
			GregorianCalendar d = new GregorianCalendar();
			// anexa do StringBuilder os dados da hora
			sb.append( d.get( GregorianCalendar.HOUR_OF_DAY ) );
			sb.append( ":" );
			sb.append( d.get( GregorianCalendar.MINUTE ) );
			sb.append( ":" );
			sb.append( d.get( GregorianCalendar.SECOND ) );
			// retorna a String do StringBuilder
			return sb.toString();
		}
	
	
}
