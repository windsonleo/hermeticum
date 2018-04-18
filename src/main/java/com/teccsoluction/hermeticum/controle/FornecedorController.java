package com.teccsoluction.hermeticum.controle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.teccsoluction.hermeticum.entidade.Fornecedor;
import com.teccsoluction.hermeticum.framework.AbstractController;
import com.teccsoluction.hermeticum.servico.FornecedorServicoImpl;

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
public class FornecedorController extends AbstractController<Fornecedor>{


	@Autowired
	private FornecedorServicoImpl fornecedorService;
	
	@FXML
	private JFXTextField cnpj;
	
	@FXML
	private JFXTextField inscricaoestadual;

	@FXML
	private JFXTextField id;
	@FXML
	private JFXTextField nomefantasia;
	@FXML
	private JFXTextField razaosocial;
	
	@FXML
	private JFXToggleButton ativo;
	
	@FXML
	private JFXTextField foto;
	
	   
		@FXML
		private JFXButton atualizar;
		
		@FXML
		private JFXButton salvar;
		
	
	@FXML
	private TableColumn<Fornecedor, Long> colFornecedorId;

	@FXML
	private TableColumn<Fornecedor, String> colFornecedorNomeFantasia;
	
	@FXML
	private TableColumn<Fornecedor, String> colRazaoSocial;
	
	@FXML
	private TableColumn<Fornecedor, String> colCnpj;
	
	@FXML
	private TableColumn<Fornecedor, String> colIE;
	
	@FXML
	private TableColumn<Fornecedor, String> colFoto;
	
//	@FXML
//	private TableColumn<Fornecedor, String> colStatus;

	
	@FXML
    private TableColumn<Fornecedor, Boolean> colAtivo;
	
	@FXML
    private TableColumn<Fornecedor, Boolean> colEdit;
	
	
	@FXML
    private TableColumn<Fornecedor, Boolean> colDel;
	
	
	private File filesaved;
	
	
	@FXML
	private JFXButton btsalvarfoto;
	
	
	private String diretorio;
	
	@FXML
	private Pane boxEntity;
	
	
	@Autowired
	public FornecedorController(FornecedorServicoImpl cat) {
		super("fornecedor");
		
		this.fornecedorService=cat;
	}
	
	
	

	@Override
	public void salvar() {
		// TODO Auto-generated method stub
		
		Fornecedor caixa = new Fornecedor();
		
		caixa.setAtivo(true);
		caixa.setNomefantasia((nomefantasia.getText()));
		caixa.setCnpj(cnpj.getText());
		caixa.setInscricaoestadual(inscricaoestadual.getText());
		caixa.setRazaosocial(razaosocial.getText());
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
		  
		  Fornecedor caixa = new Fornecedor();
			caixa.setId(idlong);
			caixa.setAtivo(true);
			caixa.setNomefantasia((nomefantasia.getText()));
			caixa.setCnpj(cnpj.getText());
			caixa.setInscricaoestadual(inscricaoestadual.getText());
			caixa.setRazaosocial(razaosocial.getText());
			caixa.setFoto(foto.getText());
			
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
		nomefantasia.clear();
		inscricaoestadual.clear();
		razaosocial.clear();
		foto.clear();
		cnpj.clear();
		


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
  			 
  			 diretorio = diretorioaux + "/src/main/resources/images/fornecedor/";
  			  
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
	Callback<TableColumn<Fornecedor, Boolean>, TableCell<Fornecedor, Boolean>> cellFactory = 
			new Callback<TableColumn<Fornecedor, Boolean>, TableCell<Fornecedor, Boolean>>()
	{
		@Override
		public TableCell<Fornecedor, Boolean> call( final TableColumn<Fornecedor, Boolean> param)
		{
			final TableCell<Fornecedor, Boolean> cell = new TableCell<Fornecedor, Boolean>()
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
							Fornecedor empresa = getTableView().getItems().get(getIndex());
							updateFornecedor(empresa);
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

				private void updateFornecedor(Fornecedor user) {
					id.setText(user.getId().toString());
					nomefantasia.setText(user.getNomefantasia());
					inscricaoestadual.setText(user.getInscricaoestadual());
					cnpj.setText(user.getCnpj());
					foto.setText(user.getFoto());
					razaosocial.setText(user.getRazaosocial());
					
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
	
	
	Callback<TableColumn<Fornecedor, Boolean>, TableCell<Fornecedor, Boolean>> cellFactorydel = 
			new Callback<TableColumn<Fornecedor, Boolean>, TableCell<Fornecedor, Boolean>>()
	{
		@Override
		public TableCell<Fornecedor, Boolean> call( final TableColumn<Fornecedor, Boolean> param)
		{
			final TableCell<Fornecedor, Boolean> cell = new TableCell<Fornecedor, Boolean>()
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
							Fornecedor empresa = getTableView().getItems().get(getIndex());
							DeleteFornecedor(empresa);
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
				private void DeleteFornecedor(Fornecedor user) {

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
		
		colFornecedorId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colFornecedorNomeFantasia.setCellValueFactory(new PropertyValueFactory<>("nomefantasia"));
		colRazaoSocial.setCellValueFactory(new PropertyValueFactory<>("razaosocial"));
		colCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		colIE.setCellValueFactory(new PropertyValueFactory<>("inscricaoestadual"));
		colFoto.setCellValueFactory(new PropertyValueFactory<>("foto"));
//		colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colAtivo.setCellValueFactory(new PropertyValueFactory<>("ativo"));
//		
		colEdit.setCellFactory(cellFactory);
		colDel.setCellFactory(cellFactorydel);
		
		super.setColumnProperties();
	}
	
	
	
	
	
	
	
	

	@Override
	protected FornecedorServicoImpl getservice() {
		return fornecedorService;
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
