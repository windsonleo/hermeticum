package com.teccsoluction.hermeticum.controle;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.teccsoluction.hermeticum.entidade.Categoria;
import com.teccsoluction.hermeticum.entidade.FormaPagamento;
import com.teccsoluction.hermeticum.entidade.PedidoVenda;
import com.teccsoluction.hermeticum.servico.ItemServicoImpl;
import com.teccsoluction.hermeticum.servico.PedidoVendaServicoImpl;
import com.teccsoluction.hermeticum.util.EditCell;
import com.teccsoluction.hermeticum.util.TipoFormaPagamento;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Controller
public class FinalizaVendaController  implements Initializable {

	
//    private ObservableList<FormaPagamento> lista;
	
	
	private  ObservableList<FormaPagamento> itemList =FXCollections.observableArrayList();

	
	@Autowired
	private  PedidoVendaServicoImpl pedidovendaService;
	
	@Autowired
	private  ItemServicoImpl itemService;
	
	
	
	@FXML
	private TableView<FormaPagamento> tabelaforma;
	
	
	@FXML
	private TableColumn<FormaPagamento, Long> colFormaId;
	
	@FXML
	private TableColumn<FormaPagamento, String> colFormaNome;
	
	@FXML
	private TableColumn<FormaPagamento, String> colFormaTipo;
	
	@FXML
	private TableColumn<FormaPagamento, Integer> colIFormaPArcela;
	
	@FXML
	private TableColumn<FormaPagamento, BigDecimal> colIFormaValorPago;
	
	
	@FXML
    private TableColumn<FormaPagamento, Boolean> colEdit;
	
	
	@FXML
    private TableColumn<FormaPagamento, Boolean> colDel;
	
	
    public PedidoVenda pedidovenda;
    
    
    
    
    
    
    
    
//	@FXML
    public Label txtsubtotal;	
	
	
	private String subtotal;
	
	@FXML
    private Label txtdescontoitem;	
	
	@FXML
    private Label txtdescontogeral;	
	
	@FXML
    private Label txtacrescimo;	
	
	@FXML
    private Label txttotalapagar;	
	
	@FXML
    private Label txttotalabaixo;
	
	@FXML
    private Label txttotalpago;	
	
	@FXML
    private Label txtsaldoapagar;
	
	@FXML
    private Label txttroco;	
	

	@FXML
    private JFXButton btcancela;	
	
	@FXML
    private JFXButton btconfirma;	
	
	
	@FXML
    private JFXButton btdinheiro;	
	
	@FXML
    private JFXButton btcredito;	
	@FXML
    private JFXButton btdebito;	
	
	@FXML
    private JFXButton btoutros;	
    
	@FXML
	private TextField txtcliente;
	

	
	@FXML
    private JFXButton btsalvarforma;
	
	@FXML
    private Label txtsimbol;
	
//	@FXML
//	private JFXTextField id;
	@FXML
	private JFXTextField nome;
	@FXML
	private JFXTextField tipo;
	
	@FXML
	private JFXTextField parcelas;
	
	@FXML
	private JFXTextField percdesconto;
	
	@FXML
	private JFXTextField jtxtfvalorpago;
	
	@FXML
	private Pane paneforma;
	
    
    
    public FinalizaVendaController() {
     
        

    }
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
//		 System.out.println(pedidovenda);

		
		 
		 
	}



	
	public void SetTxtFields(PedidoVenda pv){
		
//        this.txtsubtotal.setText(pv.getTotalVenda().toString());

		
		
	}
	
	public void SetFormasEmVenda(PedidoVenda pv){
		
//      this.txtsubtotal.setText(pv.getTotalVenda().toString());
		
		pedidovenda = pv;
		pedidovenda.setAtivo(true);
		pedidovenda.setCliente(pv.getCliente());
		pedidovenda.setData(new Date());
		pedidovenda.setFormas(pv.getFormas());
		pedidovenda.setTotalpago(pv.CalculaTotalPago(pv.getFormas()));
		pedidovenda.setTotal(pv.CalcularTotal(pv.getItems()));
//		pedidovenda.setFormas(pv.getFormas());
		
		PreencherCampos(pv);

		
		
	}
	
	
	public void exit() {
		Platform.exit();
    }
	
	
	@FXML
	private void CancelaForma(){
		
		
		btcancela.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {


			pedidovenda.getFormas().clear();

			System.out.println("cancelando Forma" + pedidovenda.getFormas());
			
			
	        
	        }
	    });
		
		
		
		
		
	}
	
	@FXML
	private void confirmaForma(){
				
		

		btconfirma.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	
	    		System.out.println("confirmando Forma" + pedidovenda.getFormas());

	    		pedidovendaService.save(pedidovenda);
	    		
	    		System.out.println("Pedido Venda Salvo com Formas!" + pedidovenda);
	    		
	    		

			 
			 loadFormaDetails(pedidovenda);

	        
	        
	        }
	    });
		
	}
	



	public void  PreencherCampos(PedidoVenda venda){
		
		pedidovenda = venda;
		
		txtsubtotal.setText(venda.getTotalVenda().toString());
		txtdescontoitem.setText("0.00");
		txtdescontogeral.setText("0.00");
		txtacrescimo.setText("0.00");
		txttotalapagar.setText(venda.getTotal().toString());
		txttotalabaixo.setText(venda.getTotal().toString());
		txttotalpago.setText(venda.getTotalVendaPaga().toString());
//		txttotalpago.setText(venda.getTotalpago().toString());

//		txtsaldoapagar.setText(venda.getTotal().min(venda.CalculaTotalPago(venda.getFormas())).toString());
		
		txtsaldoapagar.setText(venda.getTotal().subtract(venda.getTotalVendaPaga()).toString());

		txttroco.setText(venda.getTotalVendaPaga().subtract(venda.getTotal()).toString());
		
		System.out.println("venda dinheiro : " +pedidovenda);
		System.out.println("venda dinheiro venda2 : " +venda);
		
		
//		loadFormaDetails();
				
		
		
	}
	
	
	
	public void  PreencherTabela(){
		

		colFormaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colFormaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colFormaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		colIFormaPArcela.setCellValueFactory(new PropertyValueFactory<>("parcela"));
		colIFormaValorPago.setCellValueFactory(new PropertyValueFactory<>("valorpago"));
		colEdit.setCellFactory(cellFactory);
		colDel.setCellFactory(cellFactorydel);
//		colIE.setCellValueFactory(new PropertyValueFactory<>("inscricaoestadual"));
//		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//		colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
//		colItemValorUnitario.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
				
		
		
	}
	
	
	
	public void loadFormaDetails(PedidoVenda venda){
	itemList.clear();
//	this.pedidovenda = venda;
//	itemList.addAll(venda.getFormas());
	itemList = FXCollections.observableArrayList(venda.getFormas());
	tabelaforma.setItems(itemList);
	
//	setColumnProperties();
}
	
	
	
	@FXML
	private void dinheiro(){
		
		
		System.out.println("confirmando Dinheiro");
		

   	
	    	FormaPagamento forma = new FormaPagamento();

			forma.setTipo(TipoFormaPagamento.DINHEIRO);
			forma.setNome(forma.getTipo().name());
			forma.setAtivo(true);
			forma.setParcelas(0);
			forma.setValorpago(new BigDecimal(txtsubtotal.getText()));
			
			pedidovenda.addForma(forma);
			pedidovenda.setTotalpago(pedidovenda.CalculaTotalPago(pedidovenda.getFormas()));
			pedidovenda.setTotal(pedidovenda.CalcularTotal(pedidovenda.getItems()));
	        
			itemList = FXCollections.observableArrayList(pedidovenda.getFormas());
			tabelaforma.setItems(itemList);
		    tabelaforma.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	        
			 
			 loadFormaDetails(pedidovenda);

		    
		    PreencherTabela();
		    PreencherCampos(pedidovenda);
	        

		
		
	}
	
	
	@FXML
	private void debito(){
		
		
		System.out.println("confirmando Debito");
		
		
		
	    		FormaPagamento forma = new FormaPagamento();

			forma.setTipo(TipoFormaPagamento.DEBITO);
			forma.setNome(forma.getTipo().name());
			forma.setAtivo(true);
			forma.setParcelas(0);
			forma.setValorpago(new BigDecimal(txtsubtotal.getText()));
			
			pedidovenda.addForma(forma);
			pedidovenda.setTotalpago(pedidovenda.CalculaTotalPago(pedidovenda.getFormas()));
			pedidovenda.setTotal(pedidovenda.CalcularTotal(pedidovenda.getItems()));

			itemList = FXCollections.observableArrayList(pedidovenda.getFormas());

			tabelaforma.setItems(itemList);
		    tabelaforma.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			 
			 loadFormaDetails(pedidovenda);

		    
		    PreencherTabela();
		    PreencherCampos(pedidovenda);
	}
	
	
	@FXML
	private void outros(){
		
		
		System.out.println("confirmando Otros");
		
	
	        	
	    		FormaPagamento forma = new FormaPagamento();

			forma.setTipo(TipoFormaPagamento.CHEQUE);
			forma.setNome(forma.getTipo().name());
			forma.setAtivo(true);
			forma.setParcelas(0);
			forma.setValorpago(new BigDecimal(txtsubtotal.getText()));
			
			pedidovenda.addForma(forma);
			pedidovenda.setTotalpago(pedidovenda.CalculaTotalPago(pedidovenda.getFormas()));
			pedidovenda.setTotal(pedidovenda.CalcularTotal(pedidovenda.getItems()));

			itemList = FXCollections.observableArrayList(pedidovenda.getFormas());

			tabelaforma.setItems(itemList);
		    tabelaforma.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


		
			 
			 loadFormaDetails(pedidovenda);

		    
		    PreencherTabela();
		    PreencherCampos(pedidovenda);
		
	}
	
	@FXML
	private void cartao(){
		
		
		System.out.println("confirmando Credito");
		
	
	        	
	    		FormaPagamento forma = new FormaPagamento();

			forma.setTipo(TipoFormaPagamento.CREDITO);
			forma.setNome(forma.getTipo().name());
			forma.setAtivo(true);
			forma.setParcelas(0);
			forma.setValorpago(new BigDecimal(txtsubtotal.getText()));
			
			pedidovenda.addForma(forma);
			
			pedidovenda.setTotalpago(pedidovenda.CalculaTotalPago(pedidovenda.getFormas()));
			pedidovenda.setTotal(pedidovenda.CalcularTotal(pedidovenda.getItems()));
			

			itemList = FXCollections.observableArrayList(pedidovenda.getFormas());

			tabelaforma.setItems(itemList);
		    tabelaforma.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			 
			 loadFormaDetails(pedidovenda);

		    
		    PreencherTabela();
		    PreencherCampos(pedidovenda);
		
		
	}
	
	

	Callback<TableColumn<FormaPagamento, Boolean>, TableCell<FormaPagamento, Boolean>> cellFactory = 
			new Callback<TableColumn<FormaPagamento, Boolean>, TableCell<FormaPagamento, Boolean>>()
	{
		@Override
		public TableCell<FormaPagamento, Boolean> call( final TableColumn<FormaPagamento, Boolean> param)
		{
			final TableCell<FormaPagamento, Boolean> cell = new TableCell<FormaPagamento, Boolean>()
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
							FormaPagamento empresa = getTableView().getItems().get(getIndex());
							
							updateFormaPagamento(empresa);
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

				private void updateFormaPagamento(FormaPagamento user) {
//					setupValorPago(user);
					
//					id.setText(user.getId().toString());
//					nome.setText(user.getNome());
//					catpai.setText(user.getCatpai().getNome());
					
//					atualizar.setDisable(false);
////					
//					salvar.setDisable(true);
//					ligarLuz();
//					dob.setValue(user.getDob());
//					if(user.getGender().equals("Male")) rbMale.setSelected(true);
//					else rbFemale.setSelected(true);
//					cbRole.getSelectionModel().select(user.getRole());
					
//					colIFormaValorPago.get
					
					
					btsalvarforma.setVisible(true);
					jtxtfvalorpago.setVisible(true);
					txtsimbol.setVisible(true);
					
					jtxtfvalorpago.setText(user.getValorpago().toString());
					txtsimbol.setText("R$");
//					id.setText(user.getId().toString());
					nome.setText(user.getNome());
					percdesconto.setText(Float.toString(user.getPercdesconto()));
					parcelas.setText(Integer.toString(user.getParcelas()));
					tipo.setText(user.getTipo().name());
					paneforma.setVisible(true);
					
					
				}
				

			};
			return cell;
		}
	};
	
	
	
	
	Callback<TableColumn<FormaPagamento, Boolean>, TableCell<FormaPagamento, Boolean>> cellFactorydel = 
			new Callback<TableColumn<FormaPagamento, Boolean>, TableCell<FormaPagamento, Boolean>>()
	{
		@Override
		public TableCell<FormaPagamento, Boolean> call( final TableColumn<FormaPagamento, Boolean> param)
		{
			final TableCell<FormaPagamento, Boolean> cell = new TableCell<FormaPagamento, Boolean>()
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
							FormaPagamento empresa = getTableView().getItems().get(getIndex());
							DeleteFormaPagamento(empresa);
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
				private void DeleteFormaPagamento(FormaPagamento user) {

						pedidovenda.removeForma(user);
						loadFormaDetails(pedidovenda);
						PreencherCampos(pedidovenda);
						 PreencherTabela();
					
					}

				
				

			};
			return cell;
		}
	};
	
	
	
//	private void setupValorPago(FormaPagamento forma) {
//		
//		BigDecimal converter = colIFormaValorPago.getCellData(forma);
//		// sets the cell factory to use EditCell which will handle key presses
//		// and firing commit events
//		colIFormaValorPago.setCellFactory(
//				EditCell.<FormaPagamento, BigDecimal>();
//		// updates the salary field on the PersonTableData object to the
//		// committed value
//		colIFormaValorPago.setOnEditCommit(event -> {
//			final BigDecimal value = event.getNewValue() != null
//					? event.getNewValue() : event.getOldValue();
//			((FormaPagamento) event.getTableView().getItems()
//					.get(event.getTablePosition().getRow())).setValorpago(value);
//			tabelaforma.refresh();
//		});
//	}
	
	@FXML
	private void salvarformavalor(){
		
		
		FormaPagamento forma = new FormaPagamento();
		
		forma.setAtivo(true);
		forma.setNome(nome.getText());
		forma.setParcelas(Integer.parseInt((parcelas.getText())));
		forma.setTipo(TipoFormaPagamento.valueOf(tipo.getText()));
		forma.setPercdesconto(Float.parseFloat(percdesconto.getText()));
		forma.setValorpago(new BigDecimal(jtxtfvalorpago.getText()));
		
//		pedidovenda.getFormas().clear();
		
		
		if(!pedidovenda.getFormas().contains(forma)){
			
			pedidovenda.addForma(forma);
			
			System.out.println("entrou no contains");
			
		} else {
			
			pedidovenda.removeForma(forma);
			pedidovenda.addForma(forma);
			
			System.out.println("entrou no else contains");
			
		}
		
		paneforma.setVisible(false);
		
		loadFormaDetails(pedidovenda);
		PreencherCampos(pedidovenda);
		 PreencherTabela();
		
		
	}
	
}
