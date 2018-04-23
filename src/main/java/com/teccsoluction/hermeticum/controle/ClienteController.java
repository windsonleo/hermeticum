package com.teccsoluction.hermeticum.controle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.teccsoluction.hermeticum.entidade.Cliente;
import com.teccsoluction.hermeticum.framework.AbstractController;
import com.teccsoluction.hermeticum.servico.ClienteServicoImpl;
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
public class ClienteController extends AbstractController<Cliente>{


	@Autowired
	private ClienteServicoImpl ClienteService;
	
	
	@FXML
	private JFXTextField id;
	@FXML
	private JFXTextField nome;
	@FXML
	private JFXTextField foto;
	
	@FXML
	private JFXTextField email;
	
	@FXML
	private JFXToggleButton ativo;
	
	@FXML
	private JFXTextField telefone;
	@FXML
	private JFXTextField datanascimento;
	
	@FXML
	private JFXComboBox<Genero> cbgenero;
			
	
	@FXML
		private JFXButton atualizar;
		
		@FXML
		private JFXButton salvar;
		

	
	@FXML
	private TableColumn<Cliente, Long> colClienteId;

	@FXML
	private TableColumn<Cliente, String> colClienteNome;
	
	@FXML
	private TableColumn<Cliente, String> colfoto;
	
	@FXML
	private TableColumn<Cliente, String> colemail;
	
	@FXML
	private TableColumn<Cliente, String> coldataNascimento;
	
	@FXML
	private TableColumn<Cliente, String> coltelefone;
	
	
	@FXML
	private TableColumn<Cliente, String> colgenero;
	

	
	@FXML
    private TableColumn<Cliente, Boolean> colAtivo;
	
	@FXML
    private TableColumn<Cliente, Boolean> colEdit;
	
	
	@FXML
    private TableColumn<Cliente, Boolean> colDel;
	
	private File filesaved;
	
	
	@FXML
	private JFXButton btsalvarfoto;
	
	
	private String diretorio;
	
	@FXML
	private Pane boxEntity;
	
	
	
	
	@Autowired
	public ClienteController(ClienteServicoImpl cat) {
		super("cliente");
		
		this.ClienteService=cat;		
		

	}
	
	
	@Override
	public void salvar() {
		// TODO Auto-generated method stub
		
		Cliente caixa = new Cliente();
		
		caixa.setAtivo(true);
		caixa.setNome(nome.getText());
		caixa.setDatanascimento(new Date(datanascimento.getText()));
		caixa.setFoto(foto.getText());
		caixa.setEmail(email.getText());
		caixa.setGenero(Genero.valueOf(cbgenero.getSelectionModel().getSelectedItem().name()));
		caixa.setTelefone(telefone.getText());
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
		  Cliente caixa = new Cliente();
			caixa.setId(idlong);
			caixa.setAtivo(true);
			caixa.setNome(nome.getText());
			caixa.setDatanascimento(new Date(datanascimento.getText()));
			caixa.setFoto(foto.getText());
			caixa.setEmail(email.getText());
			caixa.setGenero(Genero.valueOf(cbgenero.getSelectionModel().getSelectedItem().name()));
			caixa.setTelefone(telefone.getText());
			
			getservice().edit(caixa);
			updateAlert(caixa);
			clearFields();
			desligarLuz();
			loadEntityDetails();
			  atualizar.setDisable(true);
			  salvar.setDisable(false);
			
		  
		  
		super.atualizar();
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
			 
			 diretorio = diretorioaux + "/src/main/resources/images/cliente/";
			  
//			  FileChooser filechoose = new FileChooser();
//			  filechoose.setTitle("escolha a foto");
//			  
//			  filesaved = filechoose.showSaveDialog(foto.getParent().getScene().getWindow());

			 
			  
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
	
   	private void clearFields() {
		
   		id.setText(null);
		nome.clear();
		datanascimento.clear();
		foto.clear();
		email.clear();
		telefone.clear();
		cbgenero.getItems().setAll(Genero.values());
//		ativo.clear();
	}
	
	
	
	Callback<TableColumn<Cliente, Boolean>, TableCell<Cliente, Boolean>> cellFactory = 
			new Callback<TableColumn<Cliente, Boolean>, TableCell<Cliente, Boolean>>()
	{
		@Override
		public TableCell<Cliente, Boolean> call( final TableColumn<Cliente, Boolean> param)
		{
			final TableCell<Cliente, Boolean> cell = new TableCell<Cliente, Boolean>()
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
							Cliente empresa = getTableView().getItems().get(getIndex());
							updateCliente(empresa);
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

				private void updateCliente(Cliente user) {
					id.setText(user.getId().toString());
					nome.setText(user.getNome());
					datanascimento.setText(user.getDatanascimento().toString());
					foto.setText(user.getFoto());
					email.setText(user.getEmail());
					telefone.setText(user.getTelefone());					
					atualizar.setDisable(false);
//					
//					cbgenero.getItems().set(0, Genero.values());
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
	
	
	Callback<TableColumn<Cliente, Boolean>, TableCell<Cliente, Boolean>> cellFactorydel = 
			new Callback<TableColumn<Cliente, Boolean>, TableCell<Cliente, Boolean>>()
	{
		@Override
		public TableCell<Cliente, Boolean> call( final TableColumn<Cliente, Boolean> param)
		{
			final TableCell<Cliente, Boolean> cell = new TableCell<Cliente, Boolean>()
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
							Cliente empresa = getTableView().getItems().get(getIndex());
							DeleteCliente(empresa);
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
				private void DeleteCliente(Cliente user) {
					updateAlert(user);
					getservice().delete(user.getId());
					clearFields();
					loadEntityDetails();
					
					}

				
				

			};
			return cell;
		}
	};

	
	
	
	@Override
	public void setColumnProperties() {
		// TODO Auto-generated method stub
		
		colClienteId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colfoto.setCellValueFactory(new PropertyValueFactory<>("foto"));
		coldataNascimento.setCellValueFactory(new PropertyValueFactory<>("datanascimento"));
		colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colgenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		coltelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colAtivo.setCellValueFactory(new PropertyValueFactory<>("ativo"));
//		
		colEdit.setCellFactory(cellFactory);
		colDel.setCellFactory(cellFactorydel);
		
		super.setColumnProperties();
	}
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		cbgenero.getItems().setAll(Genero.values());
		
		SetarUsuarioLoginAbstract();
		
		super.initialize(arg0, arg1);
	}
	
	
	
	

	@Override
	protected ClienteServicoImpl getservice() {
		return ClienteService;
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
