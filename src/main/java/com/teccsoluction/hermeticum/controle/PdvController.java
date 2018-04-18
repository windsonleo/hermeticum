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
import com.teccsoluction.hermeticum.entidade.Item;
import com.teccsoluction.hermeticum.entidade.PedidoVenda;
import com.teccsoluction.hermeticum.entidade.Produto;
import com.teccsoluction.hermeticum.entidade.Usuario;
import com.teccsoluction.hermeticum.servico.ClienteServicoImpl;
import com.teccsoluction.hermeticum.servico.EmpresaServicoImpl;
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
	
	
	public LoginController logincontrol;
    
	
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
	
	@FXML
    private Label txtusuarionome;
	
	private Cliente cliente;
	
//	@FXML
//    private Label lbdata;
	
//	@FXML
//    private Label lbhora;
	
	
	
    
    public PdvController() {
		// TODO Auto-generated constructor stub
	}
    
	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
    }

	/**
	 * Logout and go to the login page
	 */
    @FXML
    private void logout(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.LOGIN);    	
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
	
	
//	@FXML
//	private void movimentacaocompras(){
//		
//		  stageManager.switchScene(FxmlView.MOVIMENTACAOCOMPRAS);  
//		
//		
//		
//	}
//	
//	@FXML
//	private void movimentacaovendas(){
//		
//		  stageManager.switchScene(FxmlView.MOVIMENTACAOVENDAS);  
//		
//		
//		
//	}
	
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
				
				
		DialogoEscolhaCliente();
		
		
		
	}
	
	@FXML
	private void trocaroperador(){
		
		
		retorno.setText("Trocando Operador ...");
		
		DialogoTrocaOperador();
		
	}
	
	@FXML
	private void finalizavenda(){
		

		
		FinalizaVenda(pv);
		
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
//	        controlcliente.SetTxtFieldClienteEscolhido();
//	        cliente = controlcliente.getClienteEscolhido(controlcliente.cliente);
//	       cliente =  controlcliente.getClienteEscolhido(pv);
	       
	       controlcliente.getTxtcliente().focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
			   
				if (! isNowFocused) {
					
//					cliente = ClienteService.getClientePorNome(controlcliente.getTxtcliente().toString());
////			        
////					TextField clientescolhido = 					
//					controlcliente.SetTxtFieldClienteEscolhido(cliente);
					
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
	    			
//	    			retorno.setText("cliente escolhido:" + cliente.getNome());
	    			
	    			
	    			
	        	    Stage stage = (Stage) controlcliente.getBtconfirma().getScene().getWindow(); //Obtendo a janela atual
	        	    stage.close(); 
	            	
	            
	            }
	        
	        });
	        
//	        retorno.setText("cliente escolhido:" + pv.getCliente().getNome());
	        
	        controlcliente.getBtcancela().setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent event) {

	            	
	            	
//
//	    			System.out.println(" cliente cancelado PDV" + pv.getCliente());
	    			
	        	    Stage stage = (Stage) controlcliente.getBtcancela().getScene().getWindow(); //Obtendo a janela atual
	        	    stage.close(); 
	            	
	            
	            }
	        
	        });
	        
	        
//	        controlcliente.getTxtcliente().getProperti
	        
//	        controlcliente.set
	        

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
		
		
//		List<Cliente> choices = new ArrayList<Cliente>();
//		choices = ClienteService.findAll();
//
//		ChoiceDialog<Cliente> dialog = new ChoiceDialog<Cliente>(choices.get(0),choices);
//		dialog.setTitle("Escolha o Cliente ");
//		dialog.setHeaderText("Dailogo de  Escolha de Cliente");
//		dialog.setContentText("Escolha o Cliente da Venda:");
//		
//
//		// Traditional way to get the response value.
//		java.util.Optional<Cliente> result = dialog.showAndWait();
//		if (result.isPresent()){
//		    System.out.println("Sua Escolha: " + result.get());
//		}
//
//		// The Java 8 way to get the response value (with lambda expression).
//		result.ifPresent(letter -> System.out.println("Sua  Escolha: " + letter));
//		
//		
//		clientecupom = result.get();
//		pv.setCliente(clientecupom);
//		cupomnome.setText(clientecupom.getNome());
//		cupomend.setText(clientecupom.getEmail());
//		
//
//		
//		retorno.setText("Cliente Adicionado");
		
//		List<Cliente> clientes = ClienteService.findAll();
//		
//		TextFields.bindAutoCompletion(txtcliente,clientes);
		
//		PreencheCupom(pv);
		
	}
	
	
	
	
	public void DialogoTrocaOperador(){
		
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Dialog Trocar Operador");
		dialog.setHeaderText("Digite seu Usuario e Senha");

		// Set the icon (must be included in the project).
//		dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

		// Set the button types.
		javafx.scene.control.ButtonType loginButtonType = new javafx.scene.control.ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, javafx.scene.control.ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("Nome Usuario");
		PasswordField password = new PasswordField();
		password.setPromptText("Senha");

		grid.add(new Label("Nome Usuario:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Senha:"), 0, 1);
		grid.add(password, 1, 1);

		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		username.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> username.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(username.getText(), password.getText());
		    }
		    return null;
		});

		java.util.Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(usernamePassword -> {
		    System.out.println("Username=" + usernamePassword.getKey() + ", Senha=" + usernamePassword.getValue());
		    
		    boolean uservalido = usuarioService.authenticate(usernamePassword.getKey(), usernamePassword.getValue());
		    
		    if(uservalido){
		    	
		    	cupomoperador.setText(usernamePassword.getKey());
		    }
		
		});
		
		
		retorno.setText("Operador Trocado");
		
		
	}
	
	
	
	public void FinalizaVenda(PedidoVenda ped) {
		
		
		
		
//	    try {
//    	
//    	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/finalizavenda.fxml"));
//    	 
//         Parent root = fxmlLoader.load();
//         
//       
//	
//        javafx.stage.Window win = new Popup() ;
//	
//		Stage s1 = new Stage();
//		s1.initOwner(win);
//		s1.initModality(Modality.APPLICATION_MODAL);
//		
//		 Scene scene = new Scene(root);
//		 
//		 s1.setScene(scene);
//		 s1.show();
//	
//    
//    
//    } catch (Exception ex) {
//    	
//    	System.out.println(ex);
//    
//    }
//	
	
//	ObservableList<FormaPagamento> lista = FXCollections.observableArrayList(ClienteService.findAll());

	
//     JFXComboBox<Cliente> combocli = null;	
//     combocli.getItems().setAll(lista);
	
//    try {
//        Stage dialogStage = new Stage();
//        dialogStage.initModality(Modality.WINDOW_MODAL);
//        dialogStage.initOwner(pdv.getScene().getWindow());
//        new AuxFinalizaVenda(pv).start(dialogStage);
//        
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
    
		
//    txtsubtotal.setText("99.9999");
		
		
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
//			control
        	
        
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
        	
//        	List<Caixa> caixas = caixaService.findAll();
//        	
//        	Caixa caixa = caixas.get(0);
//        	caixa.setSaldo(caixa.getSaldo().add(pv.getTotalVenda()));
//        	caixaService.edit(caixa);

        	
    		System.out.println("confirmando Forma PDV" + pv.getFormas());

    		PedidoVendaService.save(pv);
    		
    		System.out.println("Pedido Venda Salvo com Formas PDV!" + pv);
    		
    		
    	    Stage stage = (Stage) control.getBtconfirma().getScene().getWindow(); //Obtendo a janela atual
    	    stage.close(); //
    		
    		clearFields();
    		
    		updateAlert(pv);
    		
    		itemList.clear();
    		
    		pv = new PedidoVenda();
    		
    		totalitems.clear();
    		totalvenda.clear();
    		
    		
        	
		 
//		 loadItemDetails();
//
//        
//        clearFields();
        
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
	

//	public void loadItemDetails(){
//		itemList.clear();
//		itemList.addAll(pv.getItems().entrySet());
//		cupomtabela.setItems(itemList);
//		setColumnProperties();
//	}
	

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
		
//		 setColumnProperties();
		
		
//		combocli.getItems().setAll(ClienteService.findAll());
		 
	     
	        	
//	        	
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
				
				Produto produto = ProdutoService.getProdutoPorCode(codigobarra.getText());
		        
		    	codigobarra.setText(produto.getCodebar());
		    	descricao.setText(produto.getDescricao());
		    	valorunitario.setText(produto.getPrecovenda().toString());

		        
		        
		    }
		});
		
		
		
		qtd.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
			   
			if (! isNowFocused) {
				
				Produto produtoaux = ProdutoService.getProdutoPorCode(codigobarra.getText());
				
				 java.math.BigDecimal qtdaux = new java.math.BigDecimal(qtd.getText());
				 java.math.BigDecimal tot = produtoaux.getPrecovenda().multiply(qtdaux);
			        
			        
		    	codigobarra.setText(produtoaux.getCodebar());
		    	descricao.setText(produtoaux.getDescricao());
		    	valorunitario.setText(produtoaux.getPrecovenda().toString());
		    	valortotal.setText(tot.toString());
		        
		        
		    }
		});
		
		
		
		
		
		int totit = pv.getItems().size();
		String toit = String.valueOf(totit);
		
		BigDecimal tvenda = pv.getTotalVenda();
		
		
		totalitems.setText(toit);
		totalvenda.setText(tvenda.toString());

		
		additem.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {

	        Item it = new Item();
	        it.setDescricao(descricao.getText());
	        it.setCodigo(codigobarra.getText());
	        it.setPrecoUnitario(new java.math.BigDecimal(valorunitario.getText()));
	        it.setSituacao(SituacaoItem.AGUARDANDO);
	       
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
		
		
//		txtcliente.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
//			   
//			if (! isNowFocused) {
//				
////				Cliente ciente = ClienteService.findAll();
////		        
////		    	codigobarra.setText(produto.getCodebar());
////		    	descricao.setText(produto.getDescricao());
////		    	valorunitario.setText(produto.getPrecovenda().toString());
//
//		        
//		        
//		    }
//		});	
	
		

		 setColumnProperties();
		 loadItemDetails();
		 SetarUsuarioPdv();
	}
	
	public void SetarUsuarioPdv() {
		
		usuarioUp = stageManager.GetaUsuarioStage();
		
	txtusuarionome.setText(usuarioUp.getUsername());
//	nomeusuario.setText(usuarioUp.getFirstName());
	
		
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
	 
	 public void PreencheCupom(PedidoVenda pedidovenda) {

		 cupomnome.setText(pedidovenda.getCliente().getNome());
		 cupomend.setText(pedidovenda.getCliente().getNome());
		 cupomoperador.setText(stageManager.GetaUsuarioStage().getUsername());
		 cupomdata.setText(new Date().toLocaleString());
		 
		 
		}

//	
}

	 
