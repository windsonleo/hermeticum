package com.teccsoluction.hermeticum.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@Getter
@Setter
@Entity
@Table(name = "PEDIDO_COMPRA")
public class PedidoCompra extends Pedido implements Serializable {


    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;


    private Recebimento recebimentos;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "itens_pedidocompra", joinColumns = @JoinColumn(name = "id"))
    private List<Item> items = new ArrayList<>();

    
    //CONSTRUTOR PADRAO
    public PedidoCompra() {
        super();
        this.items = new ArrayList<Item>();

        
    }

    @Override
    public String toString() {

    	return super.toString().toUpperCase();
    }
    
    
    public void addItem(Item item){
    	
    	
    	this.getItems().add(item);
    	
    	
    	
    }
    
    
    public void removeItem(Item item){
    	
    	
    	this.getItems().remove(item);
    	
    	
    	
    }
    
//    public BigDecimal getTotalCompra(){
//    	
//    	
//    	return  CalcularTotal(getItems());
//    }

    public BigDecimal getTotalCompra(){
    	
    	
    	return  CalcularTotal(getItems());
    }
}
