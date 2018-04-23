package com.teccsoluction.hermeticum.controle;

import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.teccsoluction.hermeticum.entidade.Estoque;
import com.teccsoluction.hermeticum.entidade.Item;
import com.teccsoluction.hermeticum.framework.AbstractController;
import com.teccsoluction.hermeticum.servico.EstoqueServicoImpl;
import com.teccsoluction.hermeticum.view.FxmlView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.StringConverter;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Controller
public class EstoqueController extends AbstractController<Estoque>{
	

	@FXML
	private JFXTextField id;
	@FXML
	private JFXTextField nome;
	
//	@FXML
//	private JFXComboBox<StatusEstoque> status;
	
	@FXML
	private JFXToggleButton ativo;
	
	
	@FXML
	private JFXTextField saldoinicial;
	
	   
		@FXML
		private JFXButton atualizar;
		
		@FXML
		private JFXButton salvar;
		
		@FXML
		private JFXButton abrirEstoque;
		
		@FXML
		private JFXButton fecharEstoque;
		
		@FXML
		private JFXButton pdv;
		

	@Autowired
	private EstoqueServicoImpl EstoqueService;
	
	@FXML
	private TableColumn<Estoque, Long> colEstoqueId;

	@FXML
	private TableColumn<Estoque, String> colEstoqueNome;
//	
//	@FXML
//	private TableColumn<Estoque, String> colSaldoInicial;
	
	@FXML
	private TableColumn<Estoque, String> colItens;

	
	@FXML
    private TableColumn<Estoque, Boolean> colAtivo;
	
	@FXML
    private TableColumn<Estoque, Boolean> colEdit;
	
	
	@FXML
    private TableColumn<Estoque, Boolean> colDel;
	
	
	@FXML
    private TableColumn<Estoque, Boolean> colInfom;
	
	
	
//	private  ObservableList<Map<Item,String>> itemList;
//
//    
//	@FXML
//	private   TableView<Item> itemestoquetabela ;
//	
//	@FXML
//	private TableColumn<Item, String> colItemCode;
//	
//	@FXML
//	private TableColumn<Item, String> colItemDescricao;
//	
//	@FXML
//	private TableColumn<Item, String> colItemQtd;
//	
//	@FXML
//	private TableColumn<Item, String> colItemValorUnitario;
//	
//	@FXML
//	private TableColumn<Item, String> colItemValorTotal;
	
	
	
	
	@FXML
	private Pane boxEntity;
	
	
	
//	@FXML
//    private Label txttotalprodutovalor;
//	
//	@FXML
//    private Label txttotalitemvalor;
	
	
	private Estoque estoque;
	
	
//    public static final String Column1MapKey = "ITEM";
//    public static final String Column2MapKey = "QTD";
    
    
//    @FXML
//    TableColumn<Map, String> firstDataColumn = new TableColumn<>("ITEM");
//    @FXML
//    TableColumn<Map, String> secondDataColumn = new TableColumn<>("QTD");
//    
//	@FXML
//    private  TableView tabelaitem;
//	
//	
//	private ObservableList<Map> allData = FXCollections.observableArrayList();
	
//	@FXML
//	 private TableColumn<Entry<Item, String>, Item> firstDataColumn ;
//    
//	@FXML
//	 private TableColumn<Entry<Item, String>, String> secondDataColumn ;
//	
//	
//    private ObservableList<Entry<Item, String>> items;
//  
//    @FXML
//    private TableView<Entry<Item, String>> tabelaitem ;
	
	
	private EstoqueInfomacaoController controlestoque;
	
	
	
	@Autowired
	public EstoqueController(EstoqueServicoImpl cat) {
		super("Estoque");
		
		this.EstoqueService=cat;
	}
	
	
	@Override
	public void salvar() {
		// TODO Auto-generated method stub
		
		Estoque Estoque = new Estoque();
		
		Estoque.setAtivo(true);
		Estoque.setNome(nome.getText());
//		Estoque.setStatus(StatusEstoque.valueOf(status.getSelectionModel().getSelectedItem().name()));
//		Estoque.setSaldoinicial("0.00");
		
		getservice().save(Estoque);
		saveAlert(Estoque);
		clearFields();
		desligarLuz();
		loadEntityDetails();
		  atualizar.setDisable(true);
		  salvar.setDisable(false);
		
		super.salvar();
	}
	
	
   	private void clearFields() {
		
   		id.setText(null);
		nome.clear();
//		saldoinicial.clear();
//		status.clear();
//		ativo.clear();
	}
	
	@Override
	public void atualizar() {
		// TODO Auto-generated method stub
		
		 
		  String idstring = id.getText();
		  UUID idlong = UUID.fromString(idstring);		  
		  Estoque Estoque = new Estoque();
			Estoque.setId(idlong);
			Estoque.setAtivo(true);
			Estoque.setNome(nome.getText());
//			Estoque.setStatus(StatusEstoque.valueOf(status.getSelectionModel().getSelectedItem().name()));
//			Estoque.setSaldoinicial(saldoinicial.getText());
			
			getservice().edit(Estoque);
			updateAlert(Estoque);
			clearFields();
			desligarLuz();
			loadEntityDetails();
			  atualizar.setDisable(true);
			  salvar.setDisable(false);
			
		  
		  
		super.atualizar();
	}
	
	
	
	
	
	
	Callback<TableColumn<Estoque, Boolean>, TableCell<Estoque, Boolean>> cellFactory = 
			new Callback<TableColumn<Estoque, Boolean>, TableCell<Estoque, Boolean>>()
	{
		@Override
		public TableCell<Estoque, Boolean> call( final TableColumn<Estoque, Boolean> param)
		{
			final TableCell<Estoque, Boolean> cell = new TableCell<Estoque, Boolean>()
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
							Estoque empresa = getTableView().getItems().get(getIndex());
							updateEstoque(empresa);
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

				private void updateEstoque(Estoque user) {
					id.setText(user.getId().toString());
					nome.setText(user.getNome());
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
	
	
	Callback<TableColumn<Estoque, Boolean>, TableCell<Estoque, Boolean>> cellFactorydel = 
			new Callback<TableColumn<Estoque, Boolean>, TableCell<Estoque, Boolean>>()
	{
		@Override
		public TableCell<Estoque, Boolean> call( final TableColumn<Estoque, Boolean> param)
		{
			final TableCell<Estoque, Boolean> cell = new TableCell<Estoque, Boolean>()
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
							Estoque empresa = getTableView().getItems().get(getIndex());
							DeleteEstoque(empresa);
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
				private void DeleteEstoque(Estoque user) {

					getservice().delete(user.getId());
					loadEntityDetails();
					
					}

				
				

			};
			return cell;
		}
	};

	Callback<TableColumn<Estoque, Boolean>, TableCell<Estoque, Boolean>> cellFactoryInfom = 
			new Callback<TableColumn<Estoque, Boolean>, TableCell<Estoque, Boolean>>()
	{
		@Override
		public TableCell<Estoque, Boolean> call( final TableColumn<Estoque, Boolean> param)
		{
			final TableCell<Estoque, Boolean> cell = new TableCell<Estoque, Boolean>()
			{
				
				Image imgInfo = new Image(getClass().getResourceAsStream("/images/info.png"));
//				
//
//				Image imgDel = new Image(getClass().getResourceAsStream("/images/del.png"));

				
				final JFXButton btnInfo = new JFXButton();
				
				
				
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
						
						btnInfo.setOnAction(e ->{
							Estoque empresa = getTableView().getItems().get(getIndex());
							chamarViewInfo(empresa);
						});
						
						btnInfo.setStyle("-fx-background-color: transparent;");
						ImageView iv = new ImageView();
				        iv.setImage(imgInfo);
				        iv.setPreserveRatio(true);
				        iv.setSmooth(true);
				        iv.setCache(true);
				        btnInfo.setGraphic(iv);
						
						setGraphic(btnInfo);
						setAlignment(Pos.CENTER);
						setText(null);
					
						
					
					}
					
					
					
				}

				private void chamarViewInfo(Estoque user) {

					try{
						
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/informacaoestoque.fxml"));

				        Parent root =loader.load();
				        
				        controlestoque = (EstoqueInfomacaoController)loader.getController();				       
		
//				        controlestoque.initialize(user);
//				        controlestoque.setColumnPropertiesItem(user);
				        
				        controlestoque.SetarItems(user);
				        
				        
				        
				        javafx.stage.Window win = new Popup() ;
				    	
				    		Stage s1 = new Stage();
				    		s1.initOwner(win);
				    		s1.initModality(Modality.APPLICATION_MODAL);
//				    		s1.initStyle(StageStyle.UNDECORATED);
				    		 Scene scene = new Scene(root);
				    		 
				    		 s1.setScene(scene);
				    		 s1.show();
				    		 
						}catch (Exception e) {

						System.out.println("erro Estoque chamar view Items:"+ e);
						
						}
					
					
					
					
				}
				

			};
			return cell;
		}
	};
	
	
	@Override
	public void setColumnProperties() {
		// TODO Auto-generated method stub
		
		colEstoqueId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colEstoqueNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colItens.setCellValueFactory(new PropertyValueFactory<>("items"));
//		colSaldoInicial.setCellValueFactory(new PropertyValueFactory<>("saldoinicial"));
//		colIE.setCellValueFactory(new PropertyValueFactory<>("inscricaoestadual"));
//		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//		colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colAtivo.setCellValueFactory(new PropertyValueFactory<>("ativo"));
//		
		colEdit.setCellFactory(cellFactory);
		colDel.setCellFactory(cellFactorydel);
		colInfom.setCellFactory(cellFactoryInfom);

		super.setColumnProperties();
	}
	
	
	

	@Override
	protected EstoqueServicoImpl getservice() {
		return EstoqueService;
	}
	
	
	
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// TODO Auto-generated method stub
    	
		List<Estoque> estoques = EstoqueService.findAll();
		
		estoque = estoques.get(0);
    	
//    	setColumnProperties();
//    	status.getItems().setAll(StatusEstoque.values());
    	
//    	txttotalprodutovalor.setText(Integer.toString(estoque.getItems().size()));
//    	txttotalitemvalor.setText(TotalItem(estoque.getItems()).toString());
    	
//        itemList = FXCollections.observableArrayList(estoque.getItems());
//        itemestoquetabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//    	
//    	itemList.addAll(estoque.getItems());
//    	itemestoquetabela.setItems(itemList);
    	
    	 setColumnProperties();
//		 loadItemDetails();
		 
//    	 setColumnPropertiesItem();
//		 loadItemDetails();
		 
    	
    	super.initialize(arg0, arg1);
    }
    
    
    @Override
    public void loadEntityDetails() {
    	// TODO Auto-generated method stub
    	super.loadEntityDetails();
    }

	@FXML
    public void abrirEstoque() {
    	// TODO Auto-generated method stub
    	
    }
	
	@FXML
    public void fecharEstoque() {
    	// TODO Auto-generated method stub
    	
    }
    
	@FXML
    public void pdv() {
    	// TODO Auto-generated method stub
		
		stageManager.switchScene(FxmlView.PDV);
    	
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
	
	
//	private BigDecimal TotalItem(Map<Item,String> set){
//		
////	    Collection<String> itenstotal = estoque.getItems().values();
//			
//			BigDecimal total = new BigDecimal("0.00");
//
//	    for (Item item : set.keySet()) {
//
//	    	BigDecimal totalaux = item.getQtd();
//
//	    	total = total.add(totalaux);
//
//	    }
//	    
//	    return total;
//	}
	
	
//	public void loadItemDetails(){
//	itemList.clear();
//	itemList.addAll(estoque.getItems());
////	itemestoquetabela.setItems(itemList);
////	setColumnProperties();
//}
	
	
//	private void setColumnPropertiesItem() {
//		
//		Map<Item,String> map = new HashMap<Item,String>();
//		
//		map = estoque.getItems();
//		
//		// TODO Auto-generated method stub
//		
////		colItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
//
////		colItemCode.setCellValueFactory(new PropertyValueFactory<>("codigo"));
////		colItemDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
////		colItemQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
////		colItemValorTotal.setCellValueFactory(new PropertyValueFactory<>("totalItem"));
////		colIE.setCellValueFactory(new PropertyValueFactory<>("inscricaoestadual"));
////		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
////		colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
////		colItemValorUnitario.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
////		
////		colEdit.setCellFactory(cellFactory);
////		colDel.setCellFactory(cellFactorydel);
//		
////		super.setColumnProperties();
//		
//	
//        firstDataColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Entry<Item, String>, Item>, ObservableValue<Item>>() {
//
//            @Override
//            public ObservableValue<Item> call(TableColumn.CellDataFeatures<Entry<Item, String>, Item> p) {
//                // this callback returns property for just one cell, you can't use a loop here
//                // for first column we use key
//                return new SimpleObjectProperty<Item>(p.getValue().getKey());
//            }
//        });
//
//        secondDataColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Entry<Item, String>, String>, ObservableValue<String>>() {
//
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<Entry<Item, String>, String> p) {
//                // for second column we use value
//                return new SimpleStringProperty(p.getValue().getValue());
//            }
//        });
//
//       items = FXCollections.observableArrayList(map.entrySet());
////       table = new TableView<>(items);
//
//        tabelaitem.setItems(items);
//		
//
//	}
	
//	  private ObservableList<Map> generateDataInMap() {
//
//		  
//	           
//		  for(Item item : estoque.getItems().keySet()){
//	        
//	        Map<String, String> dataRow = new HashMap<String,String>();
//	 
//	            String value1 = item.getNome();
//	            String value2 = item.getQtd().toString();
//	 
//	            dataRow.put(Column1MapKey,value1 );
//	            dataRow.put( Column2MapKey,value2);
//	 
//	            allData.add(dataRow);
//	            
//	        }
//		  
////	        }
//	        return allData;
//	    }
	 
}
