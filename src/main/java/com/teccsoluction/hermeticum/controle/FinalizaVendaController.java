package com.teccsoluction.hermeticum.controle;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.teccsoluction.hermeticum.entidade.FormaPagamento;
import com.teccsoluction.hermeticum.entidade.PedidoVenda;
import com.teccsoluction.hermeticum.servico.PedidoVendaServicoImpl;
import com.teccsoluction.hermeticum.util.TipoFormaPagamento;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
		
//		  stageManager.switchScene(FxmlView.MOVIMENTACAOPEDIDOCOMPRA);  
		
		
		
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
				
		

		
		
		
//		if(pedidovenda == null){
//			
//			
//			pedidovenda = new PedidoVenda();
//			pedidovenda.setFormas(pedidovenda.getFormas());
//			
////			pedidovenda.setItems();
////			pedidovenda = pedidovenda2;
//			
//		}
		
//		pedidovenda.setFormas(pedidovenda.getFormas());
		
//		pedidovenda = getPedidovenda();
		

		
		
		btconfirma.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	
	    		System.out.println("confirmando Forma" + pedidovenda.getFormas());

	    		pedidovendaService.save(pedidovenda);
	    		
	    		System.out.println("Pedido Venda Salvo com Formas!" + pedidovenda);
	    		
	    		

			 
			 loadFormaDetails(pedidovenda);

	        
//	        clearFields();
	        
	        }
	    });
		
	}
	
	
//	private void SalvaPedidoComForma(PedidoVenda pedidovenda2) {
//		
////		
////		if(pedidovenda == null){
////			
////			
////			pedidovenda = new PedidoVenda();
////			pedidovenda = pedidovenda2;
////			
////		}
////		
////		pedidovenda = pedidovenda2;
////		
//		
////		setPedidovenda(pedidovenda2);
//		
//		pedidovendaService.save(pedidovenda);
//		
//		System.out.println("Pedido Venda Salvo!" + pedidovenda.getId());
//		
//	}



	public void  PreencherCampos(PedidoVenda venda){
		
		pedidovenda = venda;
		
		txtsubtotal.setText(venda.getTotalVenda().toString());
		txtdescontoitem.setText("0.00");
		txtdescontogeral.setText("0.00");
		txtacrescimo.setText("0.00");
		txttotalapagar.setText(venda.getTotal().toString());
		txttotalabaixo.setText(venda.getTotal().toString());
		txttotalpago.setText(venda.getTotalpago().toString());
//		txttotalpago.setText(venda.getTotalpago().toString());

		txtsaldoapagar.setText(venda.getTotal().min(venda.CalculaTotalPago(venda.getFormas())).toString());
		
//		txtsaldoapagar.setText(venda.getTotal().min(venda.getTotalpago()).toString());

		txttroco.setText(venda.getTotalpago().subtract(venda.getTotal()).toString());
		
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
	
	
//	public void loadFormas(PedidoVenda venda){
//		
////		  stageManager.switchScene(FxmlView.MOVIMENTACAOPEDIDOCOMPRA);  
//		
//		System.out.println("confirmando Credito");
//		
//		itemList.addAll(venda.getFormas());
//		tabelaforma.setItems(itemList);
//		loadFormaDetails(venda);
//		
//		
//		
//	}
	

}
