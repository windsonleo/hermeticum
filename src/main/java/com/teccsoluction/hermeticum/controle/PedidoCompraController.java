package com.teccsoluction.hermeticum.controle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.teccsoluction.hermeticum.entidade.PedidoCompra;
import com.teccsoluction.hermeticum.framework.AbstractController;
import com.teccsoluction.hermeticum.servico.PedidoCompraServicoImpl;
import com.teccsoluction.hermeticum.util.StatusPedido;
import com.teccsoluction.hermeticum.view.FxmlView;

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
public class PedidoCompraController extends AbstractController<PedidoCompra>{
	
	

	@FXML
	private JFXTextField id;
	@FXML
	private JFXTextField nome;
	
	@FXML
	private JFXComboBox<StatusPedido> status;
	
	@FXML
	private JFXToggleButton ativo;
	
	
	@FXML
	private JFXTextField saldoinicial;
	
	   
		@FXML
		private JFXButton atualizar;
		
		@FXML
		private JFXButton salvar;
		
		@FXML
		private JFXButton abrirPedidoCompra;
		
		@FXML
		private JFXButton fecharPedidoCompra;
		
		@FXML
		private JFXButton pdv;
		

	@Autowired
	private PedidoCompraServicoImpl PedidoCompraService;
	
	@FXML
	private TableColumn<PedidoCompra, Long> colPedidoCompraId;

//	@FXML
//	private TableColumn<PedidoCompra, String> colPedidoCompraNome;
	
//	@FXML
//	private TableColumn<PedidoCompra, String> colSaldoInicial;
	
	@FXML
	private TableColumn<PedidoCompra, String> colStatus;

	
	@FXML
    private TableColumn<PedidoCompra, Boolean> colAtivo;
	
	@FXML
    private TableColumn<PedidoCompra, Boolean> colEdit;
	
	
	@FXML
    private TableColumn<PedidoCompra, Boolean> colDel;
	
	
	@FXML
	private Pane boxEntity;
	
	@FXML
	private JFXButton btrecebimento;
	
	
	
	@Autowired
	public PedidoCompraController(PedidoCompraServicoImpl cat) {
		super("PedidoCompra");
		
		this.PedidoCompraService=cat;
	}
	
	
	@Override
	public void salvar() {
		// TODO Auto-generated method stub
		
		PedidoCompra PedidoCompra = new PedidoCompra();
		
		PedidoCompra.setAtivo(true);
//		PedidoCompra.setNome(nome.getText());
		PedidoCompra.setStatus(StatusPedido.valueOf(status.getSelectionModel().getSelectedItem().name()));
//		PedidoCompra.setSaldoinicial("0.00");
		
		getservice().save(PedidoCompra);
		saveAlert(PedidoCompra);
		clearFields();
		desligarLuz();
		loadEntityDetails();
		atualizar.setDisable(true);
		salvar.setDisable(false);
		
		super.salvar();
	}
	
	
   	private void clearFields() {
		
   		id.setText(null);
//		nome.clear();
//		saldoinicial.clear();
//		status.clear();
//		ativo.clear();
	}
	
	@Override
	public void atualizar() {
		// TODO Auto-generated method stub
		
		 
		  String idstring = id.getText();
		  UUID idlong = UUID.fromString(idstring);
		  
		  PedidoCompra PedidoCompra = new PedidoCompra();
			PedidoCompra.setId(idlong);
			PedidoCompra.setAtivo(true);
//			PedidoCompra.setNome(nome.getText());
			PedidoCompra.setStatus(StatusPedido.valueOf(status.getSelectionModel().getSelectedItem().name()));
//			PedidoCompra.setSaldoinicial(saldoinicial.getText());
			
			getservice().edit(PedidoCompra);
			updateAlert(PedidoCompra);
			clearFields();
			desligarLuz();
			loadEntityDetails();
			atualizar.setDisable(true);
			salvar.setDisable(false);
			
		  
		  
		super.atualizar();
	}
	
	
	
	
	
	
	Callback<TableColumn<PedidoCompra, Boolean>, TableCell<PedidoCompra, Boolean>> cellFactory = 
			new Callback<TableColumn<PedidoCompra, Boolean>, TableCell<PedidoCompra, Boolean>>()
	{
		@Override
		public TableCell<PedidoCompra, Boolean> call( final TableColumn<PedidoCompra, Boolean> param)
		{
			final TableCell<PedidoCompra, Boolean> cell = new TableCell<PedidoCompra, Boolean>()
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
							PedidoCompra empresa = getTableView().getItems().get(getIndex());
							updatePedidoCompra(empresa);
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

				private void updatePedidoCompra(PedidoCompra user) {
					id.setText(user.getId().toString());
						//					nome.setText(user.getNome());
//					status.setText(user.getStatus().name());
//					saldoinicial.setText(user.getSaldoinicial());
			
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
	
	
	Callback<TableColumn<PedidoCompra, Boolean>, TableCell<PedidoCompra, Boolean>> cellFactorydel = 
			new Callback<TableColumn<PedidoCompra, Boolean>, TableCell<PedidoCompra, Boolean>>()
	{
		@Override
		public TableCell<PedidoCompra, Boolean> call( final TableColumn<PedidoCompra, Boolean> param)
		{
			final TableCell<PedidoCompra, Boolean> cell = new TableCell<PedidoCompra, Boolean>()
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
							PedidoCompra empresa = getTableView().getItems().get(getIndex());
							DeletePedidoCompra(empresa);
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
				private void DeletePedidoCompra(PedidoCompra user) {

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
		
		colPedidoCompraId.setCellValueFactory(new PropertyValueFactory<>("id"));
//		colPedidoCompraNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
//		colSaldoInicial.setCellValueFactory(new PropertyValueFactory<>("saldoinicial"));
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
	protected PedidoCompraServicoImpl getservice() {
		return PedidoCompraService;
	}
	
	
	
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// TODO Auto-generated method stub
    	
    	setColumnProperties();
    	status.getItems().setAll(StatusPedido.values());
    	
    	super.initialize(arg0, arg1);
    }
    
    
    @Override
    public void loadEntityDetails() {
    	// TODO Auto-generated method stub
    	super.loadEntityDetails();
    }

	@FXML
    public void abrirPedidoCompra() {
    	// TODO Auto-generated method stub
    	
    }
	
	@FXML
    public void fecharPedidoCompra() {
    	// TODO Auto-generated method stub
    	
    }
    
	@FXML
    public void pdv() {
    	// TODO Auto-generated method stub
		
		stageManager.switchScene(FxmlView.PDV);
    	
    } 
	
	@FXML
	private void desligarLuz(){
		 
	      
	      //Applying the Lighting effect to the text 
	      boxEntity.setEffect(new Lighting());  
		
		
		
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
	private void movimentacaorecebimento(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAORECEBIMENTO);  
		
		
		
	}
	 
}
