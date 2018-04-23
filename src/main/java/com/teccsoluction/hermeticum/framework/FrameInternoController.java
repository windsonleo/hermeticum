package com.teccsoluction.hermeticum.framework;



import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.jfoenix.controls.JFXButton;
import com.teccsoluction.hermeticum.conf.StageManager;
import com.teccsoluction.hermeticum.entidade.Estoque;
import com.teccsoluction.hermeticum.entidade.Usuario;
import com.teccsoluction.hermeticum.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public  class FrameInternoController<Entity> implements Initializable {

    
//	@Lazy
//    @Autowired
//    public StageManager stageManager;
        
	
    private Entity entity;
    

//	  private Usuario usuarioUp;
			
	
	
  @FXML
  private Image imgsviewinfo;
  
  @FXML
  private  ImageView imgviewinfo;
	
  @FXML
  private Circle circulo;
  
  @FXML
  private Label txtlabeltitle;
  
 

	

	
	
	
    

    public FrameInternoController() {

        
    }
    
		
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {

//			CarregarHeader();
		
		
		}
					
			
			 
			 		
				
public void init(Label entityparam){
	
//	 setEntity(entityparam);
//	
//	 txtlabeltitle.setText(entityparam.getText());
	
//	setTxtlabeltitle(entityparam);
	 
	 
//	 imgviewinfo.setVisible(false);
	
//	CarregarHeader();
	 
//	 circulo.setStroke(Color.DARKGRAY);
	  
	    
	   
//	    circulo.setFill(new ImagePattern(imgsviewinfo));
//	    circulo.setEffect(new DropShadow(+25d, 0d, +2d, Color.AZURE));
	    
//	    circuloemp.setStroke(Color.DARKGRAY);
//	    circuloemp.setFill(new ImagePattern(imgsempresa));
//	    circuloemp.setEffect(new DropShadow(+25d, 0d, +2d, Color.AZURE));


	    
	    
//	    imgviewempresa.setVisible(false);
//	    imgviewinfo.setVisible(false);
//	    txtlabeltitle.setText("teste");

	
}
			 
public void CarregarHeader(){
	

    circulo.setStroke(Color.DARKGRAY);
  
    
   
    circulo.setFill(new ImagePattern(imgsviewinfo));
    circulo.setEffect(new DropShadow(+25d, 0d, +2d, Color.AZURE));
    
//    circuloemp.setStroke(Color.DARKGRAY);
//    circuloemp.setFill(new ImagePattern(imgsempresa));
//    circuloemp.setEffect(new DropShadow(+25d, 0d, +2d, Color.AZURE));


    
    
//    imgviewempresa.setVisible(false);
    imgviewinfo.setVisible(false);
    txtlabeltitle.setText("teste");

//    txthora.setText(getHora());
//    txtcontext.setText(entityAlias);
//    empresanome.setText("Empresa Tal");
	
	
	
}			 
}
