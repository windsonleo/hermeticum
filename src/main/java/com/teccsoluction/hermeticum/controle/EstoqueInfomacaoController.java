package com.teccsoluction.hermeticum.controle;

import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.teccsoluction.hermeticum.entidade.Cliente;
import com.teccsoluction.hermeticum.entidade.Estoque;
import com.teccsoluction.hermeticum.entidade.Item;
import com.teccsoluction.hermeticum.entidade.PedidoVenda;
import com.teccsoluction.hermeticum.entidade.Produto;
import com.teccsoluction.hermeticum.servico.ClienteServicoImpl;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Controller
public class EstoqueInfomacaoController  implements Initializable {


	@FXML
	 private TableColumn<Entry<Item, String>, Item> firstDataColumn ;
   
	@FXML
	 private TableColumn<Entry<Item, String>, String> secondDataColumn ;
	
	
   private ObservableList<Entry<Item, String>> items;
 
   @FXML
   private TableView<Entry<Item, String>> tabelaitem ;
	
	private Map<Item,String> map = new HashMap<Item,String>();
	
    
	
	private Estoque estoque;
	
	@FXML
    private Label txttotalprodutovalor;
	
	@FXML
    private Label txttotalitemvalor;
	
	@FXML
    private Label txttotalprecocusto;
	
	@FXML
    private Label txttotalprecovenda;
	
	
    
    public EstoqueInfomacaoController() {
     
        

    }
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		 
	}

	public void setColumnPropertiesItem() {
		
		
        
	}

	
	
	
	public void exit() {
		Platform.exit();
    }
	
	

	public void SetarItems(Estoque est){
				
		estoque = est;
		map = est.getItems();
		items = FXCollections.observableArrayList(est.getItems().entrySet()); 
		tabelaitem.setItems(items);
    	txttotalprodutovalor.setText(Integer.toString(est.getItems().size()));
    	txttotalitemvalor.setText(TotalItem(est.getItems()).toString());
    	
    	txttotalprecocusto.setText(est.CalcularTotalCusto().toString());
    	txttotalprecovenda.setText(est.CalcularTotalVenda().toString());
		PreencherTabela();
		
	}
	
	private BigDecimal TotalItem(Map<Item,String> set){
		
//	    Collection<String> itenstotal = estoque.getItems().values();
			
			BigDecimal total = new BigDecimal("0.00");

	    for (Item item : set.keySet()) {

	    	String totalString = set.get(item);
	    	
	    	BigDecimal totalaux = new BigDecimal(totalString) ;

	    	total = total.add(totalaux);

	    }
	    
	    return total;
	}

	public void loadItemDetails(){
		
//	map = estoque.getItems();	
	items.clear();
//	this.pedidovenda = venda;
//	itemList.addAll(venda.getFormas());
	  items = FXCollections.observableArrayList(map.entrySet());
	  
	  tabelaitem.setItems(items);
	
//	setColumnProperties();
}
	

	
	public void  PreencherTabela(){
		

		   firstDataColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Entry<Item, String>, Item>, ObservableValue<Item>>() {

	            @Override
	            public ObservableValue<Item> call(TableColumn.CellDataFeatures<Entry<Item, String>, Item> p) {
	                // this callback returns property for just one cell, you can't use a loop here
	                // for first column we use key
	                return new SimpleObjectProperty<Item>(p.getValue().getKey());
	            }
	        });

	        secondDataColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Entry<Item, String>, String>, ObservableValue<String>>() {

	            @Override
	            public ObservableValue<String> call(TableColumn.CellDataFeatures<Entry<Item, String>, String> p) {
	                // for second column we use value
	                return new SimpleStringProperty(p.getValue().getValue());
	            }
	        });
				
		
	        
//	        loadItemDetails(estoque);
		
	}
	
	
	
}
