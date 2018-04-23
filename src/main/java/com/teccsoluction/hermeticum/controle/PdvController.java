package com.teccsoluction.hermeticum.controle;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.teccsoluction.hermeticum.conf.StageManager;
import com.teccsoluction.hermeticum.entidade.Cliente;
import com.teccsoluction.hermeticum.entidade.Empresa;
import com.teccsoluction.hermeticum.entidade.Estoque;
import com.teccsoluction.hermeticum.entidade.Item;
import com.teccsoluction.hermeticum.entidade.PedidoVenda;
import com.teccsoluction.hermeticum.entidade.Produto;
import com.teccsoluction.hermeticum.entidade.Usuario;
import com.teccsoluction.hermeticum.servico.ClienteServicoImpl;
import com.teccsoluction.hermeticum.servico.EmpresaServicoImpl;
import com.teccsoluction.hermeticum.servico.EstoqueServicoImpl;
import com.teccsoluction.hermeticum.servico.ItemServicoImpl;
import com.teccsoluction.hermeticum.servico.PedidoVendaServicoImpl;
import com.teccsoluction.hermeticum.servico.ProdutoServicoImpl;
import com.teccsoluction.hermeticum.servico.UsuarioServicoImpl;
import com.teccsoluction.hermeticum.util.SituacaoItem;
import com.teccsoluction.hermeticum.util.StatusPedido;
import com.teccsoluction.hermeticum.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Controller
public class PdvController implements Initializable{
	
	
	@Autowired
	private  EmpresaServicoImpl empresaService;
	
	@Autowired
	private  UsuarioServicoImpl usuarioService;
	
	@Autowired
	private  ItemServicoImpl itemService;
	
	@Autowired
	private  EstoqueServicoImpl estoqueService;
	
//	@Autowired
//	private  CaixaServicoImpl caixaService;
	
	@Autowired
	private ProdutoServicoImpl ProdutoService;
	
	@Autowired
	private PedidoVendaServicoImpl PedidoVendaService;
	
	@Autowired
	private ClienteServicoImpl ClienteService;
   
	@Lazy
    @Autowired
    private StageManager stageManager;
	
	@FXML
	private JFXButton btempresa;
	
	
	@FXML
	private JFXButton btfuncionario;
	
	@FXML
	private JFXButton btcliente;
	
	@FXML
	private JFXButton btfornecedor;
	
	@FXML
	private JFXButton btcategoria;
	
	@FXML
	private JFXButton btproduto;
	
	@FXML
	private JFXButton btconfiguracao;
	
	
	@FXML
	private JFXButton btcompras;
	
	@FXML
	private JFXButton btvendas;
	
	
	@FXML
	private JFXButton btestoque;
	
	
	
	@FXML
	private JFXButton btpdv;
	

	@FXML
    private JFXButton btusuario;
	
	@FXML
    private JFXButton btfinanceiro;
    
    
    @FXML
    private JFXButton bttrocarusuario;
    
    @FXML
    private JFXButton bteditarusuario;
    
    
	@FXML
    private JFXButton btexit;
    
	
	@FXML
    private Label txthora;
	
	@FXML
    private Label txtcontext;
	
	
	@FXML
    private JFXButton btaddcliente;
	
	@FXML
    private JFXButton btabrecupom;
	
	@FXML
    private JFXButton btcancelacupom;
	
	@FXML
    private JFXButton btcancelaitem;
	
	@FXML
    private JFXButton btencerracaixa;
	
	@FXML
    private JFXButton btpesquisaproduto;
	
	@FXML
    private JFXButton btalteraquantidade;
	
	@FXML
    private JFXButton btreimprimir;
	
	
	@FXML
    private JFXButton btfinalizavenda;
	
	@FXML
    private JFXButton bttrocaroperador;
	
	
    private Usuario usuarioUp;
	
    private Empresa empresaUp;
	

	@FXML
    private Label empresanome;	

//	@FXML
//    private Label empresadata;
	
	   @FXML
	    private Image imgsempresa;
	    
	    @FXML
	    private  ImageView imgviewempresa;
	
//	@FXML
//    private Label nomeusuario;
	
    @FXML
    private Image imgsusuario;
    
    @FXML
    private  ImageView imgviewusuario;
	
	
	@FXML
    private MenuItem sair;
	
	
    @FXML
    private Circle circulo;
    
    @FXML
    private Circle circuloemp;
    
	
	@FXML
	private JFXButton additem;
    
	@FXML
	private TextField codigobarra;
	
	@FXML
	private TextField qtd;
	@FXML
	private JFXTextField descricao;
	@FXML
	private JFXTextField valorunitario;
	@FXML
	private JFXTextField valortotal;
	
	@FXML
	private JFXTextField totalitems	;
	
	@FXML
	private JFXTextField totalvenda;

	
	private PedidoVenda pv ;
	
//	private  ObservableList<Map.Entry<Item, String>> itemList;
	
	private  ObservableList<Item> itemList;

    
	@FXML
	private   TableView<Item> cupomtabela ;
	
	
//	
//	@FXML
//	private TableColumn<Item, Long> colItemId;
	
	@FXML
	private TableColumn<Item, String> colItemCode;
	
	@FXML
	private TableColumn<Item, String> colItemDescricao;
	
	@FXML
	private TableColumn<Item, String> colItemQtd;
	
	@FXML
	private TableColumn<Item, String> colItemValorUnitario;
	
	@FXML
	private TableColumn<Item, String> colItemValorTotal;
	

	@FXML
    private Label retorno;	
	
	
	private Cliente clientecupom;
	
	@FXML
    private Label cupomnome;	
	
	@FXML
    private Label cupomend;	
	
	@FXML
    private Label cupomdata;	
	

	@FXML
    private Label pdv;	
	

	@FXML
    private Label cupomoperador;	
	
	
	private FinalizaVendaController control;
	
	
	private AddClienteController controlcliente;
	
	private TrocarOperadorController controloperador;
	
	@FXML
    private Label txtusuarionome;
	
	private Cliente cliente;
	
	private Usuario operador;
	
	private Estoque estoque;
	
//	@FXML
//    private Label lbdata;
	
//	@FXML
//    private Label lbhora;
	
	private Produto produto;
	
    
    public PdvController() {
		// TODO Auto-generated constructor stub
	}
    
	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
    }


    @FXML
    private void logout(ActionEvent event) throws IOException {
    	Platform.exit();    	
    }
    
    @FXML
    private void trocarusuario(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.LOGIN);    	
    }
    
    
    @FXML
    private void editarusuario(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.MOVIMENTACAOUSUARIO);    	
    }
    
    

   
	@FXML
	private void movimentacaoempresa(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOEMPRESA);  
		
		
		
	}
	
	@FXML
	private void movimentacaofornecedor(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOFORNECEDOR);  
		
		
		
	}
	
	@FXML
	private void movimentacaofuncionario(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOFUNCIONARIO);  
		
		
		
	}
	
	
	@FXML
	private void movimentacaocliente(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOCLIENTE);  
		
		
		
	}
	
	
	
	
	@FXML
	private void movimentacaocategoria(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOCATEGORIA);  
		
		
		
	}
	
	
	@FXML
	private void movimentacaoproduto(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOPRODUTO);  
		
		
		
	}
	
	
	@FXML
	private void movimentacaoformapagamento(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOFORMAPAGAMENTO);  
		
		
		
	}
	
	@FXML
	private void movimentacaoconfiguracao(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOCONFIGURACAO);  
		
		
		
	}
	
	  
	@FXML
	private void movimentacaocaixa(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOCAIXA);  
		
		
		
	}
	
	
	@FXML
	private void movimentacaousuario(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOUSUARIO);  
		
		
		
	}
	
	@FXML
	private void movimentacaopagamento(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOPAGAMENTO);  
		
		
		
	}
	
	
	@FXML
	private void movimentacaoestoque(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOESTOQUE);  
		
		
		
	}
	
	@FXML
	private void movimentacaocontasreceber(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOCONTASRECEBER);  
		
		
		
	}
	
	
	@FXML
	private void movimentacaocontaspagar(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOCONTASPAGAR);  
		
		
		
	}
	
	@FXML
	private void movimentacaorecebimento(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAORECEBIMENTO);  
		
		
		
	}
	
	
	@FXML
	private void movimentacaopedidocompra(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOPEDIDOCOMPRA);  
		
		
		
	}
	
	@FXML
	private void movimentacaopedidovenda(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOPEDIDOVENDA);  
		
		
		
	}
	
	@FXML
	private void movimentacaofinanceiro(){
		
		  stageManager.switchScene(FxmlView.MOVIMENTACAOFINANCEIRO);  
		
		
		
	}
	
	@FXML
    public void movimentacaopdv() {
    	// TODO Auto-generated method stub
		
		stageManager.switchScene(FxmlView.PDV);
    	
    } 
	
	
	@FXML
	private void abrecupom(){
		
//		  stageManager.switchScene(FxmlView.MOVIMENTACAOPEDIDOVENDA);  
		
		if(pv != null){
			
			retorno.setText("Cupom Aberto...");
			
		}else{
			
			retorno.setText("Abrindo Cupom...");
			
			pv = new PedidoVenda();
			itemList.clear();
			cupomtabela.setItems(itemList);
			
		}
		
		
	}
	
	@FXML
	private void addcliente(){
				
		retorno.setText("Adicionando Cliente ...");		
		DialogoEscolhaCliente();
		retorno.setText("Cliente Adicionado ..." + pv.getCliente());	
		
		
	}
	
	@FXML
	private void trocaroperador(){
		
		
		retorno.setText("Trocando Operador ...");
		
		DialogoTrocaOperador();
		
		retorno.setText("Operador Trocado ..." + operador);
		
	}
	
	@FXML
	private void finalizavenda(){
		

		
		FinalizaVenda(pv);
		
		retorno.setText("venda finalizada" + pv.getId());
		
	}
	
	@FXML
	private void cancelaitem(){
		
		
		retorno.setText("Cacnelando Item ...");
		
	}
	
	@FXML
	private void cancelacupom(){
		
		
		pv = new PedidoVenda();
		
		clearFields();
		
		totalitems.clear();
		totalvenda.clear();
		itemList.clear();
		
		cupomtabela.setItems(itemList);
		
		retorno.setText(" Cupom CANCELADO ...");
	    

		
	}
	
	@FXML
	private void reimprimir(){
		
		
		retorno.setText("Reimprimindo Cupom ...");
		
		
	}
	
	@FXML
	private void alteraquantidade(){
		
		
		retorno.setText("Alterando a Quantidade ...");
		
	}
	
	@FXML
	private void pesquisaproduto(){
		
		
		  retorno.setText("Pesquisando Produto...");
		  
		  
		
	}
	
	@FXML
	private void encerracaixa(){
		
		
		  retorno.setText("Encerrando Caixa ...");
		
	}
	
	
public void saveAlert(PedidoVenda user){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Entity saved successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The entity "+user.getId()+" has been created and \n"+" id is "+user.getId()+".");
		alert.showAndWait();
	}
	
	public void updateAlert(PedidoVenda user){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Entity updated successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The entity "+user.getId()+" has been created and \n"+" id is "+user.getId()+".");
		alert.showAndWait();
	}
	
	
	
	public void DialogoEscolhaCliente(){
		
		
		List<Cliente> clientes = ClienteService.findAll();
		
	
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addcliente.fxml"));

	        Parent root =loader.load();
	        
	        controlcliente = (AddClienteController)loader.getController();
	        

	        controlcliente.SetTxtFields(clientes);
	       
	       controlcliente.getTxtcliente().focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
			   
				if (! isNowFocused) {
										
					controlcliente.initialize();
			        
			    }
			});

	        
	        
	        
	        controlcliente.getBtconfirma().setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent event) {
	            	
	            	cliente = controlcliente.getClienteEscolhido();
	            	
					cliente = ClienteService.getClientePorNome(cliente.getNome());

	            	
	            	pv.setCliente(cliente);

	    			System.out.println(" cliente escolhido PDV" + controlcliente.getClienteEscolhido());
	    			
					retorno.setText("cliente escolhido" + controlcliente.getTxtclienteescolhido().getText());
					cupomnome.setText(controlcliente.getTxtclienteescolhido().getText());
					
	    				    			    					
	        	    Stage stage = (Stage) controlcliente.getBtconfirma().getScene().getWindow(); //Obtendo a janela atual
	        	    stage.close(); 
	            	
	            
	            }
	        
	        });
	        
	        
	        controlcliente.getBtcancela().setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent event) {

	            		    			
	        	    Stage stage = (Stage) controlcliente.getBtcancela().getScene().getWindow(); //Obtendo a janela atual
	        	    stage.close(); 
	            	
	            
	            }
	        
	        });
	        
	        
	        javafx.stage.Window win = new Popup() ;
	    	
	    		Stage s1 = new Stage();
	    		s1.initOwner(win);
	    		s1.initModality(Modality.APPLICATION_MODAL);
	    		s1.initStyle(StageStyle.UNDECORATED);
	    		 Scene scene = new Scene(root);
	    		 
	    		 s1.setScene(scene);
	    		 s1.show();
	    		 
			}catch (Exception e) {

			System.out.println("erro PDV cliente CONTROL:"+ e);
			
			}
		
	}
	
	
	
	
	public void DialogoTrocaOperador(){
		
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/trocaroperador.fxml"));

	        Parent root =loader.load();
	        
	      controloperador = (TrocarOperadorController)loader.getController();
	      
//	      controloperador.initialize(); 
	   
	        
	     
	     
	      controloperador.getBtcancela().setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {

			 
//	        	

				System.out.println("cancelando trocar operador PDV");
				
				
				
	    	    Stage stage = (Stage) controloperador.getBtcancela().getScene().getWindow(); //Obtendo a janela atual
	    	    stage.close(); 

	        	
	        
	        }
	    });
		
		
		
		
	      controloperador.getBttrocar().setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	
//	        	   operador =  controloperador.GetCampos();
	        	
//	        	operador.setUsername(controloperador.getTxtusuario().getText());
//	        	operador.setUsername(controloperador.getTxtsenha().getText());
	        	

				System.out.println("trocando operador PDV" + controloperador.getTxtusuario().getText());
				
				//controloperador
				
				boolean isvalido = usuarioService.authenticate(controloperador.getTxtusuario().getText(), controloperador.getTxtsenha().getText());
				
				if(isvalido){
					
					operador = usuarioService.findByUsername((controloperador.getTxtusuario().getText()));
					System.out.println("operador valido" +  controloperador.getTxtusuario().getText());

					retorno.setText("operador trocado e valido" + controloperador.getTxtusuario().getText());
					cupomoperador.setText(controloperador.getTxtusuario().getText());
				
				}

	        	 //Obtendo a janela atual
	    	    Stage stage = (Stage) controloperador.getBttrocar().getScene().getWindow();
	    	    stage.close(); 
	    		
	   
	        
	        }
	    });
		

	        javafx.stage.Window win = new Popup() ;
	    	
	    		Stage s1 = new Stage();
	    		s1.initOwner(win);
	    		s1.initModality(Modality.APPLICATION_MODAL);
	    		s1.initStyle(StageStyle.UNDECORATED);
	    		
	    		 Scene scene = new Scene(root);
	    		 
	    		 s1.setScene(scene);
	    		 s1.show();
	    		 
			}catch (Exception e) {

			System.out.println("erro TROCA OPERADOR PDV :"+ e);
			
			}
		
		
//		PreencheCupom(pv);
		
		
		
	}
	
	
	
	public void FinalizaVenda(PedidoVenda ped) {
		
		List<Estoque> estoques = estoqueService.findAll();
		
		estoque = estoques.get(0);
		
		
		try{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/finalizavenda.fxml"));

        Parent root =loader.load();
        
      control = (FinalizaVendaController)loader.getController();
        
      control.setPedidovenda(pv);
//      control.getPedidovenda();
      
      control.SetFormasEmVenda(pv);
             
      control.PreencherTabela();
        
      control.PreencherCampos(pv);
      
      
        
      control.loadFormaDetails(pv);
        
      
//        
//     control.setBtcancela(btcancela);
//     control.setBtconfirma(btconfirma);
     
     
 	control.getBtcancela().setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {

		 
//        	pv = control.pedidovenda;
        	
        	pv.setFormas(control.getPedidovenda().getFormas());

			pv.getFormas().clear();

			System.out.println("cancelando Forma PDV" + pv.getFormas());
			
			 control.SetFormasEmVenda(pv);
             
		      control.PreencherTabela();
		        
		      control.PreencherCampos(pv);
		      control.loadFormaDetails(pv);
			
    	    Stage stage = (Stage) control.getBtcancela().getScene().getWindow(); //Obtendo a janela atual
    	    stage.close(); 
        	
        
        }
    });
	
	
	
	
 	control.getBtconfirma().setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
        	
//        	pv = control.getPedidovenda();
        	pv.setFormas(control.getPedidovenda().getFormas());
        	pv.setAtivo(true);
        	pv.setData(new Date());
        	pv.setIspago(true);
        	pv.setStatus(StatusPedido.FINALIZADO);
        	pv.setTotal(pv.CalcularTotal(pv.getItems()));
        	
        	
    		for(Item item : pv.getItems()){
    			
    			itemService.save(item);
    			
    			}
        	
        	estoque.OperacaoEstoqueVenda(pv);
        	estoqueService.edit(estoque);
        	
//        	List<Caixa> caixas = caixaService.findAll();
//        	
//        	Caixa caixa = caixas.get(0);
//        	caixa.setSaldo(caixa.getSaldo().add(pv.getTotalVenda()));
//        	caixaService.edit(caixa);

        	
    		System.out.println("confirmando Forma PDV" + pv.getFormas());

    		PedidoVendaService.save(pv);
    		
    		System.out.println("Pedido Venda Salvo com Formas PDV!" + pv);
    		
			retorno.setText("venda salva" + pv);

    		
    		
    	    Stage stage = (Stage) control.getBtconfirma().getScene().getWindow(); //Obtendo a janela atual
    	    stage.close(); //
    		
    		clearFields();
    		
    		updateAlert(pv);
    		
    		itemList.clear();
    		
    		pv = new PedidoVenda();
    		
    		totalitems.clear();
    		totalvenda.clear();
    		
        
        }
    });
	

        javafx.stage.Window win = new Popup() ;
    	
    		Stage s1 = new Stage();
    		s1.initOwner(win);
    		s1.initModality(Modality.APPLICATION_MODAL);
    		s1.initStyle(StageStyle.UNDECORATED);
    		
    		 Scene scene = new Scene(root);
    		 
    		 s1.setScene(scene);
    		 s1.show();
    		 
		}catch (Exception e) {

		System.out.println("erro PDV CONTROL:"+ e);
		
		}
		
		
		
		
	}
	
	private void CarregarHeader(){

        circulo.setStroke(Color.DARKGRAY);
      
        
       
        circulo.setFill(new ImagePattern(imgsusuario));
        circulo.setEffect(new DropShadow(+25d, 0d, +2d, Color.AZURE));
        
        circuloemp.setStroke(Color.DARKGRAY);
        circuloemp.setFill(new ImagePattern(imgsempresa));
        circuloemp.setEffect(new DropShadow(+25d, 0d, +2d, Color.AZURE));

	
        imgviewempresa.setVisible(false);
        imgviewusuario.setVisible(false);
        
        
        txthora.setText(getHora());
        txtcontext.setText("Pdv");
        empresanome.setText("Empresa Tal");
        
		
	}
	

	public void loadItemDetails(){
	itemList.clear();
	itemList.addAll(pv.getItems());
	cupomtabela.setItems(itemList);
//	setColumnProperties();
}
	
	
	private void setColumnProperties() {
		// TODO Auto-generated method stub
		
//		colItemId.setCellValueFactory(new PropertyValueFactory<>("id"));

		colItemCode.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		colItemDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		colItemQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
		colItemValorTotal.setCellValueFactory(new PropertyValueFactory<>("totalItem"));
//		colIE.setCellValueFactory(new PropertyValueFactory<>("inscricaoestadual"));
//		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//		colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colItemValorUnitario.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
//		
//		colEdit.setCellFactory(cellFactory);
//		colDel.setCellFactory(cellFactorydel);
		
//		super.setColumnProperties();
	}
	
	
   	private void clearFields() {
		
   		codigobarra.clear();
		qtd.clear();
		valorunitario.clear();
		valortotal.clear();
		descricao.clear();
		
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		CarregarHeader();
		
       	
		 if(pv == null){
	        	pv = new PedidoVenda();
//	        	cupomtabela = new TableView<>(itemList);
		 }	
	        
	        itemList = FXCollections.observableArrayList(pv.getItems());
	        cupomtabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		List<Produto> prods = ProdutoService.findAll();
//		List<Cliente> clientes = ClienteService.findAll();
		TextFields.bindAutoCompletion(codigobarra,prods);
//		TextFields.bindAutoCompletion(txtcliente,clientes);
		
		
		codigobarra.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
		   
			if (! isNowFocused) {
				
				produto = ProdutoService.getProdutoPorCode(codigobarra.getText());
		        
		    	codigobarra.setText(produto.getCodebar());
		    	descricao.setText(produto.getDescricao());
		    	valorunitario.setText(produto.getPrecovenda().toString());

		        
		        
		    }
		});
		
		
		
		qtd.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
			   
			if (! isNowFocused) {
				
				produto = ProdutoService.getProdutoPorCode(codigobarra.getText());
				
				 java.math.BigDecimal qtdaux = new java.math.BigDecimal(qtd.getText());
				 java.math.BigDecimal tot = produto.getPrecovenda().multiply(qtdaux);
			        
			        
		    	codigobarra.setText(produto.getCodebar());
		    	descricao.setText(produto.getDescricao());
		    	valorunitario.setText(produto.getPrecovenda().toString());
		    	valortotal.setText(tot.toString());
		        
		        
		    }
		});
		
		
		
		
		
		int totit = pv.getItems().size();
		String toit = String.valueOf(totit);
		
		BigDecimal tvenda = pv.getTotalVenda();
		
		
		totalitems.setText(toit);
		totalvenda.setText(tvenda.toString());
		
//		cupomoperador.setText(operador.getUsername());
//		cupomnome.setText(pv.getCliente().getNome());
//		cupomend.setText(pv.getCliente().getNome());

		
		additem.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {

	        Item it = new Item();
	        it.setDescricao(produto.getDescricao());
	        it.setNome(produto.getNome());
	        it.setCodigo(produto.getCodebar());
	        it.setPrecoUnitario(produto.getPrecovenda());
	        it.setSituacao(SituacaoItem.AGUARDANDO);
	        it.setPrecoCusto(produto.getPrecocusto());
	        it.setUn_medida(produto.getUn_medida());
	       
	        java.math.BigDecimal qtdaux = new java.math.BigDecimal(qtd.getText());
	        it.setTotalItem(it.getPrecoUnitario().multiply(qtdaux));
	        it.setQtd(qtdaux);
	        
	        pv.addItem(it);
	        
	        cupomdata.setText(new Date().toLocaleString());
	        
			int totit = pv.getItems().size();
			String toit = String.valueOf(totit);
			
			BigDecimal tvenda = pv.getTotalVenda();
			
			
			totalitems.setText(toit);
			totalvenda.setText(tvenda.toString());
//			 itemList.addAll(pv.getItems().entrySet());
//			 itemList = FXCollections.observableArrayList(pv.getItems().entrySet());
			
			cupomtabela.setItems(itemList);
//		     cupomtabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		    
	        
//			 PedidoVendaService.edit(pv);
			
			retorno.setText("item Add" + it.getDescricao());
			 
			 loadItemDetails();

	        
	        clearFields();
	        
	        }
	    });
		
		 setColumnProperties();
		 loadItemDetails();
		 SetarUsuarioPdv();
	}
	
	public void SetarUsuarioPdv() {
		
		usuarioUp = stageManager.GetaUsuarioStage();
		
		txtusuarionome.setText(usuarioUp.getUsername());
		cupomoperador.setText(usuarioUp.getUsername());
	
		
	}
	
	 public String getHora() {
			// cria um StringBuilder
			StringBuilder sb = new StringBuilder();
			// cria um GregorianCalendar que vai conter a hora atual
			GregorianCalendar d = new GregorianCalendar();
			// anexa do StringBuilder os dados da hora
			sb.append( d.get( GregorianCalendar.HOUR_OF_DAY ) );
			sb.append( ":" );
			sb.append( d.get( GregorianCalendar.MINUTE ) );
			sb.append( ":" );
			sb.append( d.get( GregorianCalendar.SECOND ) );
			// retorna a String do StringBuilder
			return sb.toString();
		}
	 
//	 
//	 public void PreencheCupom(PedidoVenda pedidovenda) {
//
//		 cupomnome.setText(pedidovenda.getCliente().getNome());
//		 cupomend.setText(pedidovenda.getCliente().getNome());
//		 cupomoperador.setText(operador.getUsername());
//		 cupomdata.setText(new Date().toLocaleString());
//		 
//		 
//		}

//	
}

	 
