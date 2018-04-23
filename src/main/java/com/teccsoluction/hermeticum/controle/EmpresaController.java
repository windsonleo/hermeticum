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
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.teccsoluction.hermeticum.entidade.Empresa;
import com.teccsoluction.hermeticum.entidade.Usuario;
import com.teccsoluction.hermeticum.framework.AbstractController;
import com.teccsoluction.hermeticum.servico.EmpresaServicoImpl;

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
public class EmpresaController extends AbstractController<Empresa>{


//	@Lazy
//    @Autowired
//    private StageManager stageManager;
	
	@Autowired
	private EmpresaServicoImpl empresaService;

	@FXML
    private JFXButton btnLogout;

	@FXML
	private JFXTextField id;
	@FXML
	private JFXTextField nomefantasia;
	@FXML
	private JFXTextField razaosocial;
	
	@FXML
	private JFXTextField cnpj;
	@FXML
	private JFXTextField ie;
	@FXML
	private JFXTextField email;
	@FXML
	private JFXTextField telefone;
	
	@FXML
	private JFXTextField foto;
	
	@FXML
	private JFXToggleButton ativo;
	
//
	@FXML
	private JFXButton atualizar;
	
	@FXML
	private JFXButton salvar;
	
	
	private Usuario usuarioUp;

	@FXML
	private TableColumn<Empresa, Long> colEmpresaId;

	@FXML
	private TableColumn<Empresa, String> colNomeFantasia;
	
	@FXML
	private TableColumn<Empresa, String> colRazaoSocial;

	@FXML
	private TableColumn<Empresa, String> colIE;
	
	@FXML
	private TableColumn<Empresa, String> colCnpj;
	
	@FXML
	private TableColumn<Empresa, String> colEmail;
	
	@FXML
	private TableColumn<Empresa, String> colFoto;
	
	
	@FXML
    private TableColumn<Empresa, String> colTelefone;
	
	@FXML
    private TableColumn<Empresa, Boolean> colAtivo;
	
	@FXML
    private TableColumn<Empresa, Boolean> colEdit;
	
	
	@FXML
    private TableColumn<Empresa, Boolean> colDel;
	

	
	private File filesaved;
	
	
	@FXML
	private JFXButton btsalvarfoto;
	
	
	private String diretorio;
	
	@FXML
	private Pane boxEntity;
	
	
	
	public EmpresaController(EmpresaServicoImpl emp) {
		super("empresa");

	this.empresaService = emp;
	
	}
	

    

   	private void clearFields() {
		
   		id.setText(null);
		nomefantasia.clear();
		razaosocial.clear();
		ie.clear();
		cnpj.clear();
		razaosocial.clear();
		telefone.clear();
		email.clear();
		foto.clear();
//		ativo.clear();
	}
	  @FXML
	  public void salvar(){
		
		  Empresa empresa = new Empresa();
//		  empresa.setId(2563L);
		  empresa.setAtivo(true);
		  empresa.setNomefantasia(nomefantasia.getText());
		  empresa.setRazaosocial(razaosocial.getText());
		  empresa.setCnpj(cnpj.getText());
		  empresa.setEmail(email.getText());
		  empresa.setInscricaoestadual(ie.getText());
		  empresa.setTelefone(telefone.getText());
		  empresa.setFoto(foto.getText());
		  
		  empresaService.save(empresa);
		  clearFields();
		  desligarLuz();
		  loadEntityDetails();
		  saveAlert(empresa);
//		  boxEntity.setEffect( new Lighting());
		  atualizar.setDisable(true);
		  salvar.setDisable(false);
		
		super.salvar();
	}
	  
	  @FXML
	public void atualizar(){
		
		 
		  String idstring = id.getText();
		  UUID idlong = UUID.fromString(idstring);		  
		  Empresa empresa = new Empresa();
		  empresa.setId(idlong);
		  empresa.setAtivo(true);
		  empresa.setNomefantasia(nomefantasia.getText());
		  empresa.setRazaosocial(razaosocial.getText());
		  empresa.setCnpj(cnpj.getText());
		  empresa.setEmail(email.getText());
		  empresa.setInscricaoestadual(ie.getText());
		  empresa.setTelefone(telefone.getText());
		  empresa.setFoto(foto.getText());
		  
		  getservice().edit(empresa);
		  clearFields();
		  desligarLuz();
		  loadEntityDetails();
		  updateAlert(empresa);
		
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
				 
				 diretorio = diretorioaux + "/src/main/resources/images/empresa/";
				  

				 
				  
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
	
	
		
		Callback<TableColumn<Empresa, Boolean>, TableCell<Empresa, Boolean>> cellFactory = 
				new Callback<TableColumn<Empresa, Boolean>, TableCell<Empresa, Boolean>>()
		{
			@Override
			public TableCell<Empresa, Boolean> call( final TableColumn<Empresa, Boolean> param)
			{
				final TableCell<Empresa, Boolean> cell = new TableCell<Empresa, Boolean>()
				{
					
					Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
//					
//
//					Image imgDel = new Image(getClass().getResourceAsStream("/images/del.png"));

					
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
								Empresa empresa = getTableView().getItems().get(getIndex());
								updateEmpresa(empresa);
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

					private void updateEmpresa(Empresa user) {
						id.setText(user.getId().toString());
						razaosocial.setText(user.getRazaosocial());
						nomefantasia.setText(user.getNomefantasia());
						telefone.setText(user.getTelefone());
						email.setText(user.getEmail());
						cnpj.setText(user.getCnpj());
						ie.setText(user.getInscricaoestadual());
						foto.setText(user.getFoto());
						
						atualizar.setDisable(false);
						
						salvar.setDisable(true);
						
						ligarLuz();
			    
						
//						dob.setValue(user.getDob());
//						if(user.getGender().equals("Male")) rbMale.setSelected(true);
//						else rbFemale.setSelected(true);
//						cbRole.getSelectionModel().select(user.getRole());
					}
					

				};
				return cell;
			}
		};
		
		
		Callback<TableColumn<Empresa, Boolean>, TableCell<Empresa, Boolean>> cellFactorydel = 
				new Callback<TableColumn<Empresa, Boolean>, TableCell<Empresa, Boolean>>()
		{
			@Override
			public TableCell<Empresa, Boolean> call( final TableColumn<Empresa, Boolean> param)
			{
				final TableCell<Empresa, Boolean> cell = new TableCell<Empresa, Boolean>()
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
								Empresa empresa = getTableView().getItems().get(getIndex());
								DeleteEmpresa(empresa);
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
					private void DeleteEmpresa(Empresa user) {
						
						
						updateAlert(user);
						getservice().delete(user.getId());
						loadEntityDetails();
						
						}

					
					
	
				};
				return cell;
			}
		};
		
		@Override
		public void setColumnProperties(){
			/* Override date format in table
			 * colDOB.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDate>() {
				 String pattern = "dd/MM/yyyy";
				 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
			     @Override 
			     public String toString(LocalDate date) {
			         if (date != null) {
			             return dateFormatter.format(date);
			         } else {
			             return "";
			         }
			     }

			     @Override 
			     public LocalDate fromString(String string) {
			         if (string != null && !string.isEmpty()) {
			             return LocalDate.parse(string, dateFormatter);
			         } else {
			             return null;
			         }
			     }
			 }));*/
			
			colEmpresaId.setCellValueFactory(new PropertyValueFactory<>("id"));
			colNomeFantasia.setCellValueFactory(new PropertyValueFactory<>("nomefantasia"));
			colRazaoSocial.setCellValueFactory(new PropertyValueFactory<>("razaosocial"));
			colCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
			colIE.setCellValueFactory(new PropertyValueFactory<>("inscricaoestadual"));
			colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
			colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
			colAtivo.setCellValueFactory(new PropertyValueFactory<>("ativo"));
			colFoto.setCellValueFactory(new PropertyValueFactory<>("foto"));

			colEdit.setCellFactory(cellFactory);
			colDel.setCellFactory(cellFactorydel);
			
			super.setColumnProperties();
		}
		
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		setColumnProperties();
		
		SetarUsuarioLoginAbstract();

		super.initialize(arg0, arg1);
		
	}
	
	@Override
	protected EmpresaServicoImpl getservice() {
		// TODO Auto-generated method stub
		return empresaService;
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
