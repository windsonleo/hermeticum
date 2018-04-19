package com.teccsoluction.hermeticum.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.teccsoluction.hermeticum.util.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PEDIDO_VENDA")
public class PedidoVenda extends Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


//    @ManyToOne
//    @JoinColumn(name = "mesa_id")
//    private Mesa mesa;

//    @ManyToOne
//    @JoinColumn(name = "garcon_id")
//    private Garcon garcon;


//    // VENDA OOU COMPRA
//    @Enumerated(EnumType.STRING)
//    private OrigemPedido origempedido;

//    private boolean ispago = false;

    //aberto,pendente,fechado,cancelado
    @Enumerated(EnumType.STRING)
    private StatusPedido status;


    
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "itens_pedidovenda", joinColumns = @JoinColumn(name = "id"))
    private List<Item> items = new ArrayList<Item>();


    public PedidoVenda() {
        super();
        
        this.items = new ArrayList<Item>();


    }



    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString().toUpperCase();
    }

    public void addItem(Item item){
    	
    	
    	this.getItems().add(item);
    	
    	
    	
    }
    
    
    public void removeItem(Item item){
    	
    	
    	this.getItems().remove(item);
	
    	
    }
    
    
    
    public BigDecimal getTotalVenda(){
    	
    	
    	return  CalcularTotal(getItems());
    }
    
    
    public BigDecimal getTotalVendaPaga(){
    	
    	
    	return  CalculaTotalPago(getFormas());
    }

}
