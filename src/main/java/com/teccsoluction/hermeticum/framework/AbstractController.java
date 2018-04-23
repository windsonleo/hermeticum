package com.teccsoluction.hermeticum.framework;



import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.jfoenix.controls.JFXButton;
import com.teccsoluction.hermeticum.conf.StageManager;
import com.teccsoluction.hermeticum.controle.EstoqueInfomacaoController;
import com.teccsoluction.hermeticum.entidade.Estoque;
import com.teccsoluction.hermeticum.entidade.Usuario;
import com.teccsoluction.hermeticum.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class AbstractController<Entity> implements Initializable {

    private final String entityAlias;
    
	@Lazy
    @Autowired
    public StageManager stageManager;
    
	@FXML
    private  TableView<Entity> entitytabela;
    
	
    private Entity entity;
    
    
    private  ObservableList<Entity> entityList = FXCollections.observableArrayList();
    
    protected abstract AbstractEntityService<Entity> getservice();
    
    
	@FXML
    private TableColumn<Entity, Boolean> colEdit;
	
	
	@FXML
    private TableColumn<Entity, Boolean> colDel;
	
	
	@FXML
    private TableColumn<Entity, Boolean> colInfo;
	
	
	@FXML
    private MenuItem deleteEntity;
	
	
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
	
	
	  private Usuario usuarioUp;
			
	

	@FXML
  private Label empresanome;	

	@FXML
  private Label empresadata;
	
	   @FXML
	    private Image imgsempresa;
	    
	    @FXML
	    private  ImageView imgviewempresa;

	
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
  private JFXButton bttrocarusuario;
  
  @FXML
  private JFXButton bteditarusuario;
  
	@FXML
    private JFXButton btfinanceiro;
  
  
	@FXML
  private JFXButton btexit;
	
	
	@FXML
    private Label txtusuarionome;
	
	@FXML
    private Label txthora;
	
	@FXML
    private Label txtcontext;
	
//	
	private FrameInternoController<Entity> frameinterno;
	
	
    

    public AbstractController(String entityAlias) {
        this.entityAlias = entityAlias;
//        this.prop = properties;
        
    }

   

	@FXML
	private void logout(ActionEvent event) {
		Platform.exit();
    }
	
	
//    @FXML
//    private void logout(ActionEvent event) throws IOException {
//    	stageManager.switchScene(FxmlView.LOGIN);    	
//    }
    
    
    @FXML
    private void trocarusuario(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.LOGIN);    	
    }
    
    
    @FXML
    private void editarusuario(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.MOVIMENTACAOUSUARIO);    	
    }
    
    
public void saveAlert(Entity user){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Entity saved successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The entity "+user+" "+entityAlias +" has been created and \n"+" id is "+ entityAlias+".");
		alert.showAndWait();
	}
	
	public void updateAlert(Entity user){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Entity updated successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The entity "+user+" "+entityAlias+" has been updated.");
		alert.showAndWait();
	}

   
    @FXML
    public void deleteEntity(ActionEvent event){
    	List<Entity> users = entitytabela.getSelectionModel().getSelectedItems();
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete selected?");
		Optional<ButtonType> action = alert.showAndWait();
		
		if(action.get() == ButtonType.OK) getservice().deleteInBatch(users);
    	
    	loadEntityDetails();
    }
    
 	private void clearFields() {
		
//   		id.setText(null);
//		nomefantasia.clear();
//		razaosocial.clear();
//		ie.clear();
//		cnpj.clear();
//		razaosocial.clear();
//		telefone.clear();
//		email.clear();
//		ativo.clear();
	}
	@FXML
	public void salvar(){
		
//		  Empresa empresa = new Empresa();
////		  empresa.setId(2563L);
//		  empresa.setAtivo(true);
//		  empresa.setNomefantasia(nomefantasia.getText());
//		  empresa.setRazaosocial(razaosocial.getText());
//		  empresa.setCnpj(cnpj.getText());
//		  empresa.setEmail(email.getText());
//		  empresa.setInscricaoestadual(ie.getText());
//		  empresa.setTelefone(telefone.getText());
		  
		  getservice().save(entity);
		  clearFields();
		  loadEntityDetails();
		  saveAlert(entity);
		
		
	}
	  
	@FXML
	public void atualizar(){
		
		 
//		  String idstring = id.getText();
//		  long idlong = Long.parseLong(idstring);
//		  
//		  Empresa empresa = new Empresa();
//		  empresa.setId(idlong);
//		  empresa.setAtivo(true);
//		  empresa.setNomefantasia(nomefantasia.getText());
//		  empresa.setRazaosocial(razaosocial.getText());
//		  empresa.setCnpj(cnpj.getText());
//		  empresa.setEmail(email.getText());
//		  empresa.setInscricaoestadual(ie.getText());
//		  empresa.setTelefone(telefone.getText());
		  
//		  Entity enti = getservice().findOne(idlong);
		  
//		  getservice().edit(enti);
//		  clearFields();
//		  loadEntityDetails();
//		  updateAlert(enti);
		
		
	}
	  
	  
		public void loadEntityDetails(){
			entityList.clear();
			entityList.addAll(getservice().findAll());
			entitytabela.setItems(entityList);
		}
		
		

    
		
		public void setColumnProperties(){
			
			colInfo.setCellFactory(cellFactoryInfo);
			
		}
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {

			entitytabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
//			itens = listView.getItems();

			CarregarHeader();
			
			SetarUsuarioLoginAbstract();
			
			setColumnProperties();
			
			// Add all users into table
			loadEntityDetails();
			
		}
		
		
		@FXML
		private void movimentacaoempresa(){
			
			  stageManager.switchScene(FxmlView.MOVIMENTACAOEMPRESA);  
			
			
			
		}
		
		
		   
			@FXML
			private void movimentacaopdv(){
				
				  stageManager.switchScene(FxmlView.PDV);  
				
				
				
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
	//	
	//	
	//	
	//	
		@FXML
		private void movimentacaocategoria(){
			
			  stageManager.switchScene(FxmlView.MOVIMENTACAOCATEGORIA);  
			
			
			
		}
	//	
	//	
		@FXML
		private void movimentacaoproduto(){
			
			  stageManager.switchScene(FxmlView.MOVIMENTACAOPRODUTO);  
			
			
			
		}
	//	
	//	
	//	
//		@FXML
//		private void movimentacaoformapagamento(){
//			
//			  stageManager.switchScene(FxmlView.MOVIMENTACAOFORMAPAGAMENTO);  
//			
//			
//			
//		}
	//	
		@FXML
		private void movimentacaoconfiguracao(){
			
			  stageManager.switchScene(FxmlView.MOVIMENTACAOCONFIGURACAO);  
			
			
			
		}
	//	
//		  
//		@FXML
//		private void movimentacaocaixa(){
//			
//			  stageManager.switchScene(FxmlView.MOVIMENTACAOCAIXA);  
//			
//			
//			
//		}
	//	
	//	
		@FXML
		private void movimentacaousuario(){
			
			  stageManager.switchScene(FxmlView.MOVIMENTACAOUSUARIO);  
			
			
			
		}
	//	
//		@FXML
//		private void movimentacaopagamento(){
//			
//			  stageManager.switchScene(FxmlView.MOVIMENTACAOPAGAMENTO);  
//			
//			
//			
//		}
	//	
	//	
		@FXML
		private void movimentacaoestoque(){
			
			  stageManager.switchScene(FxmlView.MOVIMENTACAOESTOQUE);  
			
			
			
		}
	//	
		@FXML
		private void movimentacaocontasreceber(){
			
			  stageManager.switchScene(FxmlView.MOVIMENTACAOCONTASRECEBER);  
			
			
			
		}
	//	
	//	
		@FXML
		private void movimentacaocontaspagar(){
			
			  stageManager.switchScene(FxmlView.MOVIMENTACAOCONTASPAGAR);  
			
			
			
		}
	//	
		@FXML
		private void movimentacaorecebimento(){
			
			  stageManager.switchScene(FxmlView.MOVIMENTACAORECEBIMENTO);  
			
			
			
		}
	//	
	//	
		@FXML
		private void movimentacaopedidocompra(){
			
			  stageManager.switchScene(FxmlView.MOVIMENTACAOPEDIDOCOMPRA);  
			
			
			
		}
	//	
		@FXML
		private void movimentacaopedidovenda(){
			
			  stageManager.switchScene(FxmlView.MOVIMENTACAOPEDIDOVENDA);  
			
			
			
		}
		
		@FXML
		private void movimentacaofinanceiro(){
			
			  stageManager.switchScene(FxmlView.MOVIMENTACAOFINANCEIRO);  
			
			
			
		}
		
		
			
			
			public void SetarUsuarioLoginAbstract() {
				
				usuarioUp = stageManager.GetaUsuarioStage();
				txtusuarionome.setText(usuarioUp.getUsername());
//				nomeusuario.setText(usuarioUp.getFirstName());

			
				
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
		        txtcontext.setText(entityAlias);
		        empresanome.setText("Empresa Tal");
				
				
				
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
			 
			 
			 
			 
			 Callback<TableColumn<Entity, Boolean>, TableCell<Entity, Boolean>> cellFactoryInfo = 
						new Callback<TableColumn<Entity, Boolean>, TableCell<Entity, Boolean>>()
				{
					@Override
					public TableCell<Entity, Boolean> call( final TableColumn<Entity, Boolean> param)
					{
						final TableCell<Entity, Boolean> cell = new TableCell<Entity, Boolean>()
						{
							
							Image imgInfo = new Image(getClass().getResourceAsStream("/images/info.png"));
//							
			//
//							Image imgDel = new Image(getClass().getResourceAsStream("/images/del.png"));

							
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
										Entity empresa = getTableView().getItems().get(getIndex());
										InfoEntity(empresa);
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

							@SuppressWarnings("unchecked")
							private void InfoEntity(Entity user) {

								
								

								try{
									
									FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/informacao"+ entityAlias +".fxml"));

							        Parent root =loader.load();
							        
//							        frameinterno = new FrameInternoController<Entity>(entityAlias);
//							        Label lb = new Label();
//							        lb.setText(entityAlias);
							        
							        frameinterno =(FrameInternoController<Entity>)loader.getController();
//							        frameinterno.initialize(arg0, arg1);
//							        frameinterno.init(lb);
//							        frameinterno.SetarItems(user);
//							        frameinterno.SetarItems(entity);
					
//							        controlestoque.initialize(user);
//							        controlestoque.setColumnPropertiesItem(user);
							        
//							        frameinterno.SetarItems(user);
//							        frameinterno.CarregarHeader();
							        
							        
							        
							        javafx.stage.Window win = new Popup() ;
							    	
							    		Stage s1 = new Stage();
							    		s1.initOwner(win);
							    		s1.initModality(Modality.APPLICATION_MODAL);
//							    		s1.initStyle(StageStyle.UNDECORATED);
							    		 Scene scene = new Scene(root);
							    		 
							    		 s1.setScene(scene);
							    		 s1.show();
							    		 
									}catch (Exception e) {

									System.out.println("erro ABSTRACT  chamar view INFO:"+ e + "entityalias" + entityAlias);
									
									}
								
								
								
								
							}
							

						};
						return cell;
					}
				};

				
				
				
				@FXML
				private void informacaoempresa(){
					
					  stageManager.switchScene(FxmlView.INFORMACAOEMPRESA);  
					
					
					
				}
				
				
				   
					@FXML
					private void informacaopdv(){
						
						  stageManager.switchScene(FxmlView.INFORMACAOPDV);  
						
						
						
					}
				
				@FXML
				private void informacaofornecedor(){
					
					  stageManager.switchScene(FxmlView.INFORMACAOFORNECEDOR);  
					
					
					
				}
				
				@FXML
				private void informacaofuncionario(){
					
					  stageManager.switchScene(FxmlView.INFORMACAOFUNCIONARIO);  
					
					
					
				}
				
				
				@FXML
				private void informacaocliente(){
					
					  stageManager.switchScene(FxmlView.INFORMACAOCLIENTE);  
					
					
					
				}
			//	
			//	
			//	
			//	
				@FXML
				private void informacaocategoria(){
					
					  stageManager.switchScene(FxmlView.INFORMACAOCATEGORIA);  
					
					
					
				}
			//	
			//	
				@FXML
				private void informacaoproduto(){
					
					  stageManager.switchScene(FxmlView.INFORMACAOPRODUTO);  
					
					
					
				}
			//	
			//	
			//	
//				@FXML
//				private void informacaoformapagamento(){
//					
//					  stageManager.switchScene(FxmlView.INFORMACAOFORMAPAGAMENTO);  
//					
//					
//					
//				}
			//	
				@FXML
				private void informacaoconfiguracao(){
					
					  stageManager.switchScene(FxmlView.INFORMACAOCONFIGURACAO);  
					
					
					
				}
			//	
//				  
//				@FXML
//				private void informacaocaixa(){
//					
//					  stageManager.switchScene(FxmlView.INFORMACAOCAIXA);  
//					
//					
//					
//				}
			//	
			//	
				@FXML
				private void informacaousuario(){
					
					  stageManager.switchScene(FxmlView.INFORMACAOUSUARIO);  
					
					
					
				}
			//	
//				@FXML
//				private void informacaopagamento(){
//					
//					  stageManager.switchScene(FxmlView.INFORMACAOPAGAMENTO);  
//					
//					
//					
//				}
			//	
			//	
				@FXML
				private void informacaoestoque(){
					
					  stageManager.switchScene(FxmlView.INFORMACAOESTOQUE);  
					
					
					
				}
			//	
				@FXML
				private void informacaocontasreceber(){
					
					  stageManager.switchScene(FxmlView.INFORMACAOCONTASRECEBER);  
					
					
					
				}
			//	
			//	
				@FXML
				private void informacaocontaspagar(){
					
					  stageManager.switchScene(FxmlView.INFORMACAOCONTASPAGAR);  
					
					
					
				}
			//	
				@FXML
				private void informacaorecebimento(){
					
					  stageManager.switchScene(FxmlView.INFORMACAORECEBIMENTO);  
					
					
					
				}
			//	
			//	
				@FXML
				private void informacaopedidocompra(){
					
					  stageManager.switchScene(FxmlView.INFORMACAOPEDIDOCOMPRA);  
					
					
					
				}
			//	
				@FXML
				private void informacaopedidovenda(){
					
					  stageManager.switchScene(FxmlView.INFORMACAOPEDIDOVENDA);  
					
					
					
				}
				
				@FXML
				private void informacaofinanceiro(){
					
					  stageManager.switchScene(FxmlView.INFORMACAOFINANCEIRO);  
					
					
					
				}
				
} 

