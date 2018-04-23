package com.teccsoluction.hermeticum.controle;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.teccsoluction.hermeticum.entidade.Categoria;
import com.teccsoluction.hermeticum.framework.AbstractController;
import com.teccsoluction.hermeticum.framework.AbstractEditor;
import com.teccsoluction.hermeticum.servico.CategoriaServicoImpl;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Controller
public class CategoriaController extends AbstractController<Categoria>{

	
	
	@FXML
	private JFXTextField id;
	@FXML
	private JFXTextField nome;
	@FXML
	private JFXComboBox<Categoria> catpai;
	
	@FXML
	private JFXToggleButton ativo;
	
	
	   
		@FXML
		private JFXButton atualizar;
		
		@FXML
		private JFXButton salvar;
		

	@Autowired
	private final CategoriaServicoImpl CategoriaService;
	
	@FXML
	private TableColumn<Categoria, Long> colCategoriaId;

	@FXML
	private TableColumn<Categoria, String> colCategoriaNome;
	
	@FXML
	private TableColumn<Categoria, String> colcatpai;
	
//	@FXML
//	private TableColumn<Categoria, String> colStatus;

	
	@FXML
    private TableColumn<Categoria, Boolean> colAtivo;
	
	@FXML
    private TableColumn<Categoria, Boolean> colEdit;
	
	
	@FXML
    private TableColumn<Categoria, Boolean> colDel;
	
	@FXML
	private Pane boxEntity;
	
	
	@Autowired
	public CategoriaController(CategoriaServicoImpl cat) {
		super("categoria");
		
		this.CategoriaService=cat;
	}
	
	
    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Categoria.class, new AbstractEditor<Categoria>(this.CategoriaService) {

        });
    }
	
	
	
	@Override
	public void salvar() {
		// TODO Auto-generated method stub
		
		Categoria caixa = new Categoria();
		
		caixa.setAtivo(true);
		caixa.setNome(nome.getText());
		caixa.setCatpai(catpai.getSelectionModel().getSelectedItem());
		
		getservice().save(caixa);
		saveAlert(caixa);
		clearFields();
		desligarLuz();
		loadEntityDetails();
		atualizar.setDisable(true);
		salvar.setDisable(false);
		
		super.salvar();
	}
	
	
	
	
	@Override
	public void atualizar() {
		// TODO Auto-generated method stub
		
		 
		  String idstring = id.getText();
		  UUID idlong = UUID.fromString(idstring);
		  
		  Categoria caixa = new Categoria();
			caixa.setId(idlong);
			caixa.setAtivo(true);
			caixa.setNome(nome.getText());
			caixa.setCatpai(catpai.getSelectionModel().getSelectedItem());
			
			getservice().edit(caixa);
			updateAlert(caixa);
			clearFields();
			desligarLuz();
			loadEntityDetails();
			atualizar.setDisable(true);
			salvar.setDisable(false);
			
			
		  
		  
		super.atualizar();
	}
	
	
   	private void clearFields() {
		
   		id.setText(null);
		nome.clear();
		catpai.getItems().setAll(getservice().findAll());

//		ativo.clear();
	}
	
	
	
	Callback<TableColumn<Categoria, Boolean>, TableCell<Categoria, Boolean>> cellFactory = 
			new Callback<TableColumn<Categoria, Boolean>, TableCell<Categoria, Boolean>>()
	{
		@Override
		public TableCell<Categoria, Boolean> call( final TableColumn<Categoria, Boolean> param)
		{
			final TableCell<Categoria, Boolean> cell = new TableCell<Categoria, Boolean>()
			{
				
				Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
//				
//
//				Image imgDel = new Image(getClass().getResourceAsStream("/images/del.png"));

				
				final JFXButton btnEdit = new JFXButton();
				
				
				
				@Override
				public void updateItem(Boolean check, boolean empty)
				{
					super.updateItem(check, empty);
					if(empty)
					{
						setGraphic(null);
						setText(null);
					}
					else{
						
						btnEdit.setOnAction(e ->{
							Categoria empresa = getTableView().getItems().get(getIndex());
							updateCategoria(empresa);
						});
						
						btnEdit.setStyle("-fx-background-color: transparent;");
						ImageView iv = new ImageView();
				        iv.setImage(imgEdit);
				        iv.setPreserveRatio(true);
				        iv.setSmooth(true);
				        iv.setCache(true);
						btnEdit.setGraphic(iv);
						
						setGraphic(btnEdit);
						setAlignment(Pos.CENTER);
						setText(null);
					
						
					
					}
					
					
					
				}

				private void updateCategoria(Categoria user) {
					id.setText(user.getId().toString());
					nome.setText(user.getNome());
//					catpai.setText(user.getCatpai().getNome());
					
					atualizar.setDisable(false);
//					
					salvar.setDisable(true);
					ligarLuz();
//					dob.setValue(user.getDob());
//					if(user.getGender().equals("Male")) rbMale.setSelected(true);
//					else rbFemale.setSelected(true);
//					cbRole.getSelectionModel().select(user.getRole());
				}
				

			};
			return cell;
		}
	};
	
	
	Callback<TableColumn<Categoria, Boolean>, TableCell<Categoria, Boolean>> cellFactorydel = 
			new Callback<TableColumn<Categoria, Boolean>, TableCell<Categoria, Boolean>>()
	{
		@Override
		public TableCell<Categoria, Boolean> call( final TableColumn<Categoria, Boolean> param)
		{
			final TableCell<Categoria, Boolean> cell = new TableCell<Categoria, Boolean>()
			{
				

//
				Image imgDel = new Image(getClass().getResourceAsStream("/images/del.png"));

//				
				final JFXButton btnDel = new JFXButton();
				
				
				
				@Override
				public void updateItem(Boolean check, boolean empty)
				{
					super.updateItem(check, empty);
					if(empty)
					{
						setGraphic(null);
						setText(null);
					}
					else{
												
						
						btnDel.setOnAction(e ->{
							Categoria empresa = getTableView().getItems().get(getIndex());
							DeleteCategoria(empresa);
						});
						
						btnDel.setStyle("-fx-background-color: transparent;");
						ImageView ivv = new ImageView();
				        ivv.setImage(imgDel);
				        ivv.setPreserveRatio(true);
				        ivv.setSmooth(true);
				        ivv.setCache(true);
				        btnDel.setGraphic(ivv);
						
						setGraphic(btnDel);
						setAlignment(Pos.CENTER);
						setText(null);
						
					
					}
					
					
					
				}
				private void DeleteCategoria(Categoria user) {
					
					updateAlert(user);
					getservice().delete(user.getId());
					loadEntityDetails();
					
					}

				
				

			};
			return cell;
		}
	};

	
	
	
	@Override
	public void setColumnProperties() {
		// TODO Auto-generated method stub
		
		colCategoriaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colCategoriaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
//		colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		colcatpai.setCellValueFactory(new PropertyValueFactory<>("catpai"));
//		colIE.setCellValueFactory(new PropertyValueFactory<>("inscricaoestadual"));
//		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//		colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colAtivo.setCellValueFactory(new PropertyValueFactory<>("ativo"));
//		
		colEdit.setCellFactory(cellFactory);
		colDel.setCellFactory(cellFactorydel);
		
		super.setColumnProperties();
	}
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		catpai.getItems().setAll(getservice().findAll());
		
		SetarUsuarioLoginAbstract();
		
		super.initialize(arg0, arg1);
	}
	
	

	@Override
	protected CategoriaServicoImpl getservice() {
		return CategoriaService;
	}
	
	
	
    
	@Override
	public void SetarUsuarioLoginAbstract() {
		// TODO Auto-generated method stub
		
		super.SetarUsuarioLoginAbstract();
	}
	
	@FXML
	private void ligarLuz(){
		
		 Light.Point light = new Light.Point(); 
	      
	      //Setting the color of the light
	      light.setColor(Color.WHITE);  
	      
	      //Setting the position of the light 
	      light.setX(150); 
	      light.setY(200); 
	      light.setZ(400); 
	       
	      //Instantiating the Lighting class  
	      Lighting lighting = new Lighting(); 
	      
	      //Setting the light 
	      lighting.setLight(light);  
	      
	      //Applying the Lighting effect to the text 
	      boxEntity.setEffect(lighting);  
		
		
		
	}
	
	@FXML
	private void desligarLuz(){
		 
	      
	      //Applying the Lighting effect to the text 
	      boxEntity.setEffect(new Lighting());  
		
		
		
	}	
	 
}
