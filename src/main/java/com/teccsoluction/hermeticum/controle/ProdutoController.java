package com.teccsoluction.hermeticum.controle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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
import com.teccsoluction.hermeticum.entidade.Categoria;
import com.teccsoluction.hermeticum.entidade.Fornecedor;
import com.teccsoluction.hermeticum.entidade.Produto;
import com.teccsoluction.hermeticum.framework.AbstractController;
import com.teccsoluction.hermeticum.servico.CategoriaServicoImpl;
import com.teccsoluction.hermeticum.servico.FornecedorServicoImpl;
import com.teccsoluction.hermeticum.servico.ProdutoServicoImpl;
import com.teccsoluction.hermeticum.util.UnidadeMedida;

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
public class ProdutoController extends AbstractController<Produto>{


	@Autowired
	private final ProdutoServicoImpl produtoService;
	
	@Autowired
	private final FornecedorServicoImpl fornecedorService;
	
	@Autowired
	private final CategoriaServicoImpl categoriaService;
	
	@FXML
	private JFXComboBox<Categoria> categoriaUp;
	
	@FXML
	private JFXTextField precocusto;
	
	@FXML
	private JFXTextField id;
	@FXML
	private JFXTextField nome;
	@FXML
	private JFXTextField foto;
	
	@FXML
	private JFXTextField descricao;
	
	@FXML
	private JFXComboBox<UnidadeMedida> un_medida;
	
	@FXML
	private JFXTextField precovenda;
	
	@FXML
	private JFXComboBox<Fornecedor> fornecedorUp;
	
	
	@FXML
	private JFXTextField codebar;
	
	@FXML
	private JFXToggleButton ativo;
	
	@FXML
	private JFXToggleButton sugestao;
	
	
	   
		@FXML
		private JFXButton atualizar;
		
		@FXML
		private JFXButton salvar;
		

	
	@FXML
	private TableColumn<Produto, Long> colProdutoId;

	@FXML
	private TableColumn<Produto, String> colProdutoNome;
	
	@FXML
	private TableColumn<Produto, String> colProdutocodebar;
	
//	@FXML
//	private TableColumn<Produto, String> colfoto;
	
	@FXML
	private TableColumn<Produto, String> colcategoria;
	
//	@FXML
//	private TableColumn<Produto, String> colprecocusto;
	
	@FXML
	private TableColumn<Produto, String> colprecovenda;
	
	@FXML
	private TableColumn<Produto, String> colfornecedor;
	
	@FXML
	private TableColumn<Produto, String> colun_med;
	
//	@FXML
//	private TableColumn<Produto, String> coldescricao;
	
//	@FXML
//	private TableColumn<Produto, String> colStatus;

//	@FXML
//    private TableColumn<Produto, Boolean> colesugestao;
	
	@FXML
    private TableColumn<Produto, Boolean> colAtivo;
	
	@FXML
    private TableColumn<Produto, Boolean> colEdit;
	
	
	@FXML
    private TableColumn<Produto, Boolean> colDel;
	
	private File filesaved;
	
	
	@FXML
	private JFXButton btsalvarfoto;
	
	
	private String diretorio;
	
	
	@FXML
	private Pane boxEntity;
	
	
	
	@Autowired
	public ProdutoController(ProdutoServicoImpl cat,FornecedorServicoImpl forn,CategoriaServicoImpl catt) {
		super("produto");
		
		this.produtoService=cat;
		this.fornecedorService=forn;
		this.categoriaService = catt;
	}
	
	
	@Override
	public void salvar() {
		// TODO Auto-generated method stub
		
		Produto caixa = new Produto();
		
		caixa.setAtivo(true);
		caixa.setNome(nome.getText());
		caixa.setCategoria(categoriaUp.getSelectionModel().getSelectedItem());
		caixa.setCodebar(codebar.getText());
		caixa.setDescricao(descricao.getText());
		caixa.setEsugestao(true);
		caixa.setFornecedor((fornecedorUp.getSelectionModel().getSelectedItem()));
		caixa.setPrecocusto(new BigDecimal(precocusto.getText()));
		caixa.setPrecovenda(new BigDecimal(precovenda.getText()));
		caixa.setUn_medida((un_medida.getSelectionModel().getSelectedItem()));
		
		
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
		  
		  Produto caixa = new Produto();
			caixa.setId(idlong);
			caixa.setAtivo(true);
			caixa.setNome(nome.getText());
			caixa.setCategoria(categoriaUp.getSelectionModel().getSelectedItem());
			caixa.setCodebar(codebar.getText());
			caixa.setDescricao(descricao.getText());
			caixa.setEsugestao(true);
			caixa.setFornecedor((fornecedorUp.getSelectionModel().getSelectedItem()));
			caixa.setPrecocusto(new BigDecimal(precocusto.getText()));
			caixa.setPrecovenda(new BigDecimal(precovenda.getText()));
			caixa.setUn_medida((un_medida.getSelectionModel().getSelectedItem()));
			
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
		nome.clear();
		descricao.clear();
		foto.clear();
//		categoriaUp.clear();
//		fornecedorUp.clear();
		precocusto.clear();
		precovenda.clear();
		codebar.clear();
//		un_medida.clear();

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
  			 
  			 diretorio = diretorioaux + "/src/main/resources/images/produto/";
  			  
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
	
	Callback<TableColumn<Produto, Boolean>, TableCell<Produto, Boolean>> cellFactory = 
			new Callback<TableColumn<Produto, Boolean>, TableCell<Produto, Boolean>>()
	{
		@Override
		public TableCell<Produto, Boolean> call( final TableColumn<Produto, Boolean> param)
		{
			final TableCell<Produto, Boolean> cell = new TableCell<Produto, Boolean>()
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
							Produto empresa = getTableView().getItems().get(getIndex());
							updateProduto(empresa);
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

				private void updateProduto(Produto user) {
					id.setText(user.getId().toString());
					nome.setText(user.getNome());
					descricao.setText(user.getDescricao());
					foto.setText(user.getFoto());
					codebar.setText(user.getCodebar());
//					fornecedorUp.setText(user.getFornecedor().getNomefantasia());
//					categoriaUp.setText(user.getCategoria().getNome());
					precocusto.setText(user.getPrecocusto().toString());
					precovenda.setText( user.getPrecovenda().toString());
//					un_medida.setText(user.getUn_medida().name());
					
					
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
	
	
	Callback<TableColumn<Produto, Boolean>, TableCell<Produto, Boolean>> cellFactorydel = 
			new Callback<TableColumn<Produto, Boolean>, TableCell<Produto, Boolean>>()
	{
		@Override
		public TableCell<Produto, Boolean> call( final TableColumn<Produto, Boolean> param)
		{
			final TableCell<Produto, Boolean> cell = new TableCell<Produto, Boolean>()
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
							Produto empresa = getTableView().getItems().get(getIndex());
							DeleteProduto(empresa);
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
				private void DeleteProduto(Produto user) {

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
		
		colProdutoId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
//		colfoto.setCellValueFactory(new PropertyValueFactory<>("foto"));
		colcategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
//		coldescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
//		colprecocusto.setCellValueFactory(new PropertyValueFactory<>("precocusto"));
		colprecovenda.setCellValueFactory(new PropertyValueFactory<>("precovenda"));
		colun_med.setCellValueFactory(new PropertyValueFactory<>("un_medida"));
		colProdutocodebar.setCellValueFactory(new PropertyValueFactory<>("codebar"));
		colfornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
//		colesugestao.setCellValueFactory(new PropertyValueFactory<>("esugestao"));
		colAtivo.setCellValueFactory(new PropertyValueFactory<>("ativo"));
//		
		colEdit.setCellFactory(cellFactory);
		colDel.setCellFactory(cellFactorydel);
		
		super.setColumnProperties();
	}
	
	
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		categoriaUp.getItems().setAll(categoriaService.findAll());
		fornecedorUp.getItems().setAll(fornecedorService.findAll());
		un_medida.getItems().setAll(UnidadeMedida.values());
		
		super.initialize(arg0, arg1);
	}
	
	
	
	

	@Override
	protected ProdutoServicoImpl getservice() {
		return produtoService;
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
