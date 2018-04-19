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
import com.teccsoluction.hermeticum.entidade.Usuario;
import com.teccsoluction.hermeticum.framework.AbstractController;
import com.teccsoluction.hermeticum.servico.UsuarioServicoImpl;

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
public class UsuarioController extends AbstractController<Usuario> {

   



//	@Lazy
//    @Autowired
//    private StageManager stageManager;
	
	@Autowired
	private UsuarioServicoImpl usuarioService;


	@FXML
	private JFXTextField id;
	@FXML
	private JFXTextField username;
//	@FXML
//	private JFXTextField LastName;
//	@FXML
//	private JFXTextField email;
	
	@FXML
	private JFXTextField password;
	@FXML
	private JFXTextField foto;
	
	@FXML
	private JFXToggleButton ativo;
	


	@FXML
	private TableColumn<Usuario, Long> colUsuarioId;

	@FXML
	private TableColumn<Usuario, String> colUsername;
	
//	@FXML
//	private TableColumn<Usuario, String> colLastName;

//	@FXML
//	private TableColumn<Usuario, String> colEmail;
	
	@FXML
	private TableColumn<Usuario, String> colFoto;
	
	@FXML
	private TableColumn<Usuario, String> colPassword;
	
	
	@FXML
    private TableColumn<Usuario, Boolean> colAtivo;
	
	@FXML
    private TableColumn<Usuario, Boolean> colEdit;
	
	
	@FXML
    private TableColumn<Usuario, Boolean> colDel;

	
	@FXML
	private JFXButton atualizar;
	
	@FXML
	private JFXButton salvar;
		
	
	private File filesaved;
	
	
	@FXML
	private JFXButton btsalvarfoto;
	
	
	private String diretorio;

	@FXML
	private Pane boxEntity;
	
	
	@Autowired
	public UsuarioController(UsuarioServicoImpl uss) {
		super("usuario");


		this.usuarioService=uss;
}
	

    

   	private void clearFields() {
		
   		id.setText(null);
   		username.clear();
//   		LastName.clear();
   		password.clear();
   		foto.clear();
//		email.clear();
	}
	  @FXML
	public void salvar(){
		
		  Usuario usuario = new Usuario();
//		  usuario.setId(2563L);
		  usuario.setAtivo(true);
		  usuario.setUsername(username.getText());
		  usuario.setPassword(password.getText());
		  usuario.setFoto(foto.getText());
//		  usuario.setEmail(email.getText());
		  
		  usuarioService.save(usuario);
		  clearFields();
		  desligarLuz();
		  loadEntityDetails();
		  saveAlert(usuario);
			atualizar.setDisable(true);
			salvar.setDisable(false);
		  super.salvar();
		
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
		 
		 diretorio = diretorioaux + "/src/main/resources/images/usuario/";
		  
//		  FileChooser filechoose = new FileChooser();
//		  filechoose.setTitle("escolha a foto");
//		  
//		  filesaved = filechoose.showSaveDialog(foto.getParent().getScene().getWindow());

		 
		  
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
	  
	  
	  @FXML
	  public void atualizar(){
		
		 
		  String idstring = id.getText();
		  UUID idlong = UUID.fromString(idstring);		  
		  Usuario usuario = new Usuario();
		  usuario.setId(idlong);
		  usuario.setAtivo(true);
		  usuario.setUsername(username.getText());
//		  usuario.setLastName(LastName.getText());
		  usuario.setPassword(password.getText());
		  usuario.setFoto(foto.getText());
//		  usuario.setEmail(email.getText());
		  
		  usuarioService.edit(usuario);
		  clearFields();
		  desligarLuz();
		  loadEntityDetails();
		updateAlert(usuario);
		atualizar.setDisable(true);
		salvar.setDisable(false);
		
		super.atualizar();
		
	}
   
//	private void CarregarFotoUsuario(){
//		
//        ///////////////important code starts from here
//
//        getCirculo().setStroke(Color.SEAGREEN);
//        getCirculo().setFill(new ImagePattern(imgs));
//        getCirculo().setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
//        
//        nome.setText("Windson Melo");
//        hora.setText(new Date().toLocaleString());
//        vindo.setText("Bem-Vindo,");
//        imgview.setVisible(false);
//		
//		
//		
//	}
//	  @FXML
//	private void cadastrousuario(){
//		
//		  stageManager.switchScene(FxmlView.MAIN);  
//		
//		
//		
//	}
	
			
			
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
				
				colUsuarioId.setCellValueFactory(new PropertyValueFactory<>("id"));
				colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
//				colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
				colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
//				colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
				colFoto.setCellValueFactory(new PropertyValueFactory<>("foto"));
				colAtivo.setCellValueFactory(new PropertyValueFactory<>("ativo"));
				
				colEdit.setCellFactory(cellFactory);
				colDel.setCellFactory(cellFactorydel);
				
				super.setColumnProperties();
			}
			
			
			
			
			Callback<TableColumn<Usuario, Boolean>, TableCell<Usuario, Boolean>> cellFactory = 
					new Callback<TableColumn<Usuario, Boolean>, TableCell<Usuario, Boolean>>()
			{
				@Override
				public TableCell<Usuario, Boolean> call( final TableColumn<Usuario, Boolean> param)
				{
					final TableCell<Usuario, Boolean> cell = new TableCell<Usuario, Boolean>()
					{
						
						Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
//						
	//
//						Image imgDel = new Image(getClass().getResourceAsStream("/images/del.png"));

						
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
									Usuario empresa = getTableView().getItems().get(getIndex());
									updateUsuario(empresa);
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

						private void updateUsuario(Usuario user) {
							id.setText(user.getId().toString());
							username.setText(user.getUsername());
//							LastName.setText(user.getLastName());
							password.setText(user.getPassword());
//							email.setText(user.getEmail());
							foto.setText(user.getFoto());
							atualizar.setDisable(false);
						
							salvar.setDisable(true);
							ligarLuz();

						}
						

					};
					return cell;
				}
			};
			
			
			Callback<TableColumn<Usuario, Boolean>, TableCell<Usuario, Boolean>> cellFactorydel = 
					new Callback<TableColumn<Usuario, Boolean>, TableCell<Usuario, Boolean>>()
			{
				@Override
				public TableCell<Usuario, Boolean> call( final TableColumn<Usuario, Boolean> param)
				{
					final TableCell<Usuario, Boolean> cell = new TableCell<Usuario, Boolean>()
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
									Usuario empresa = getTableView().getItems().get(getIndex());
									DeleteUsuario(empresa);
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
						private void DeleteUsuario(Usuario user) {
							
							
							usuarioService.delete(user.getId());
							updateAlert(user);
							loadEntityDetails();
							
							}

						
						
		
					};
					return cell;
				}
			};
			
		

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		setColumnProperties();
		
		super.initialize(arg0, arg1);

	}




	@Override
	protected UsuarioServicoImpl getservice() {
		// TODO Auto-generated method stub
		return usuarioService;
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
