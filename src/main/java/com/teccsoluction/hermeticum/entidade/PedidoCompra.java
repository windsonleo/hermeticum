package com.teccsoluction.hermeticum.entidade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
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


    @OneToMany(mappedBy = "pedidocompra")
    @JsonIgnore
    private List<Recebimento> recebimentos;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "itens_pedidocompra", joinColumns = @JoinColumn(name = "id"))
//    @JsonManagedReference
//    @Lob
    @Column(name = "qtd")
    @MapKeyColumn(name = "idit")
    private Map<Item, String> items = new HashMap<>();

    
    //CONSTRUTOR PADRAO
    public PedidoCompra() {
        super();
        this.items = new HashMap<Item, String>();

        
    }

    @Override
    public String toString() {

    	return super.toString().toUpperCase();
    }
    
    
    public void addItem(Item item, String qtd){
    	
    	
    	this.getItems().put(item, qtd);
    	
    	
    	
    }
    
    
    public void removeItem(Item item){
    	
    	
    	this.getItems().remove(item);
    	
    	
    	
    }
    
//    public BigDecimal getTotalCompra(){
//    	
//    	
//    	return  CalcularTotal(getItems());
//    }


}
