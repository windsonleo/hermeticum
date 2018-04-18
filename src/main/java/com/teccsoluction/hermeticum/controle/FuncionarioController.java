package com.teccsoluction.hermeticum.controle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.teccsoluction.hermeticum.entidade.Funcionario;
import com.teccsoluction.hermeticum.entidade.Usuario;
import com.teccsoluction.hermeticum.framework.AbstractController;
import com.teccsoluction.hermeticum.servico.FuncionarioServicoImpl;
import com.teccsoluction.hermeticum.servico.UsuarioServicoImpl;
import com.teccsoluction.hermeticum.util.Genero;

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
import javafx.stage.FileChooser;
import javafx.util.Callback;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Controller
public class FuncionarioController extends AbstractController<Funcionario>{


	@Autowired
	private FuncionarioServicoImpl FuncionarioService;
	
	@Autowired
	private UsuarioServicoImpl UsuarioService;
	
	@FXML
	private JFXTextField id;
	@FXML
	private JFXTextField nome;
	@FXML
	private JFXTextField foto;
	
	@FXML
	private JFXComboBox<Usuario> cbusuario;
	
	@FXML
	private JFXComboBox<Genero> cbgenero;
	
	@FXML
	private JFXToggleButton ativo;
	
	
	   
		@FXML
		private JFXButton atualizar;
		
		@FXML
		private JFXButton salvar;
		

	
	@FXML
	private TableColumn<Funcionario, Long> colFuncionarioId;

	@FXML
	private TableColumn<Funcionario, String> colFuncionarioNome;
	
	@FXML
	private TableColumn<Funcionario, String> colfoto;
	
	@FXML
	private TableColumn<Funcionario, String> colusuario;
	
//	@FXML
//	private TableColumn<Funcionario, String> colStatus;

	
	@FXML
    private TableColumn<Funcionario, Boolean> colAtivo;
	
	@FXML
    private TableColumn<Funcionario, Boolean> colEdit;
	
	
	@FXML
    private TableColumn<Funcionario, Boolean> colDel;
	
	@FXML
	private TableColumn<Funcionario, String> colgenero;
	
	
	private File filesaved;
	
	
	@FXML
	private JFXButton btsalvarfoto;
	
	
	private String diretorio;

	@FXML
	private Pane boxEntity;
	
	
	@Autowired
	public FuncionarioController(FuncionarioServicoImpl cat,UsuarioServicoImpl catt) {
		super("funcionario");
		
		this.FuncionarioService=cat;
		this.UsuarioService = catt;
	}
	
	
	@Override
	public void salvar() {
		// TODO Auto-generated method stub
		
		Funcionario caixa = new Funcionario();
		
		caixa.setAtivo(true);
		caixa.setNome(nome.getText());
		caixa.setUsuario(cbusuario.getSelectionModel().getSelectedItem());
		caixa.setGenero(Genero.valueOf(cbgenero.getSelectionModel().getSelectedItem().name()));
		caixa.setFoto(foto.getText());
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
		  
		  Funcionario caixa = new Funcionario();
			caixa.setId(idlong);
			caixa.setAtivo(true);
			caixa.setNome(nome.getText());
			caixa.setUsuario(cbusuario.getSelectionModel().getSelectedItem());
			caixa.setGenero(Genero.valueOf(cbgenero.getSelectionModel().getSelectedItem().name()));
			caixa.setFoto(foto.getText());
			
			getservice().edit(caixa);
			updateAlert(caixa);
			clearFields();
			 desligarLuz();
			loadEntityDetails();
			
			
		  
		  
		super.atualizar();
	}
	
	
   	private void clearFields() {
		
   		id.setText(null);
		nome.clear();
//		usuario.clear();
		foto.clear();
		cbusuario.getItems().setAll(UsuarioService.findAll());
//		ativo.clear();
	}
	
    @FXML
  		private void open(){
  			
  			  FileChooser filechoose = new FileChooser();
  			  filechoose.setTitle("escolha a foto");
  			  
  			  filesaved = filechoose.showOpenDialog(foto.getParent().getScene().getWindow());

  			  
  			  if (filesaved != null) {

  			  foto.setText(filesaved.getName());
  			
  			  
  			  }
  			
  		}
  		  @FXML
  		private void salvarfoto() throws IOException {

  			 String  diretorioaux = System.getProperty("user.dir");
  			 
  			 diretorio = diretorioaux + "/src/main/resources/images/funcionario/";
  			  
//  			  FileChooser filechoose = new FileChooser();
//  			  filechoose.setTitle("escolha a foto");
//  			  
//  			  filesaved = filechoose.showSaveDialog(foto.getParent().getScene().getWindow());

  			 
  			  
  			  try {

  			       BufferedImage image =ImageIO.read(filesaved);
  			       // Save the image as a PNG file
  			       ImageIO.write(image, "png", new File(diretorio + filesaved.getName()));
  			      // Save it as a JPEG file
  			      // ImageIO.write(image, "jpg", new File("C:\\test.jpg"));

  			       System.out.println("Salvo com sucesso!");
  			       System.out.println("Salvo com sucesso IMG!" + image);
  			      
  			  }catch(Exception e) {
  			      
  				  e.printStackTrace();
  		       }
  				
  			
  		}
	
	Callback<TableColumn<Funcionario, Boolean>, TableCell<Funcionario, Boolean>> cellFactory = 
			new Callback<TableColumn<Funcionario, Boolean>, TableCell<Funcionario, Boolean>>()
	{
		@Override
		public TableCell<Funcionario, Boolean> call( final TableColumn<Funcionario, Boolean> param)
		{
			final TableCell<Funcionario, Boolean> cell = new TableCell<Funcionario, Boolean>()
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
							Funcionario empresa = getTableView().getItems().get(getIndex());
							updateFuncionario(empresa);
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

				private void updateFuncionario(Funcionario user) {
					id.setText(user.getId().toString());
					nome.setText(user.getNome());
//					usuario.setText(user.getUsuario().getFirstName());
					foto.setText(user.getFoto());
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
	
	
	Callback<TableColumn<Funcionario, Boolean>, TableCell<Funcionario, Boolean>> cellFactorydel = 
			new Callback<TableColumn<Funcionario, Boolean>, TableCell<Funcionario, Boolean>>()
	{
		@Override
		public TableCell<Funcionario, Boolean> call( final TableColumn<Funcionario, Boolean> param)
		{
			final TableCell<Funcionario, Boolean> cell = new TableCell<Funcionario, Boolean>()
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
							Funcionario empresa = getTableView().getItems().get(getIndex());
							DeleteFuncionario(empresa);
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
				private void DeleteFuncionario(Funcionario user) {

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
		
		colFuncionarioId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colFuncionarioNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colfoto.setCellValueFactory(new PropertyValueFactory<>("foto"));
		colusuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
		colgenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
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
		
		cbusuario.getItems().setAll(UsuarioService.findAll());
		cbgenero.getItems().setAll(Genero.values());
		
		super.initialize(arg0, arg1);
	}
	
	
	

	@Override
	protected FuncionarioServicoImpl getservice() {
		return FuncionarioService;
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
