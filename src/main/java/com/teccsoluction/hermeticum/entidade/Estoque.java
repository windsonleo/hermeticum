package com.teccsoluction.hermeticum.entidade;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.teccsoluction.hermeticum.framework.BaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ESTOQUE")
public class Estoque extends BaseEntity  implements Serializable {


    private static final long serialVersionUID = 1L;


    @Column(name = "nome", nullable = false)
    private String nome;
 

//    @OneToMany(fetch=FetchType.EAGER)
//    @ElementCollection(fetch=FetchType.EAGER,targetClass=String.class)
//    @Column(name = "qtd")
//    @MapKeyColumn(name = "idit")
    
    @ElementCollection(fetch=FetchType.EAGER)
    @JoinTable  
    @Column(name = "qtd")
    private Map<Item,String> items;


    public Estoque() {
    	
    	items = new HashMap<Item,String>();

    	
    }



    @Override
    public String toString() {
        return nome.toUpperCase();
    }

    
    
    public void AddProdutoEstoque(Item produto) {

    	String vantigo= "0.00";
        BigDecimal vnovo = produto.getQtd();
        BigDecimal novo = new BigDecimal("0.00");
        BigDecimal antigo = new BigDecimal("0.00");
        
 if (getItems().containsKey(produto)) {
            
            vantigo = getItems().get(produto);

            antigo = new BigDecimal(vantigo);

            novo = novo.add(antigo).add(vnovo);

            items.replace(produto,vantigo.toString(),novo.toString());
//            items.remove(produto);
        	
        }else {
        	
        	
        	  novo = novo.add(antigo).add(vnovo);	
        	  items.put(produto, novo.toString());
        	
        }

    }	


    public void RetirarProdutoEstoque(Item produto) {

    	String vantigo= "0.00";
        BigDecimal vnovo = produto.getQtd();
        BigDecimal novo = new BigDecimal("0.00");
        BigDecimal antigo = new BigDecimal("0.00");
        
        if (getItems().containsKey(produto)) {
//          
              vantigo = getItems().get(produto);
  //
              antigo = new BigDecimal(vantigo);
  //
              novo = novo.add(antigo).subtract(vnovo);
  //
              items.replace(produto,vantigo.toString(),novo.toString());
////              items.remove(produto);
//          	
//          	
         }else {
//          	
////          	
         	  novo = novo.add(antigo).subtract(vnovo);
         	  items.put(produto, novo.toString());
//          	
          	
          }

          
      }

    public BigDecimal CalcularTotalCusto() {

        BigDecimal totalpedido = new BigDecimal(0.00).setScale(2, RoundingMode.UP);


        for (Item key : getItems().keySet()) {

            String qtd = getItems().get(key);

//            String qtdstring = BigDecimal.valueOf(qtd);

            BigDecimal quantidadef = new BigDecimal(qtd);

            totalpedido = totalpedido.add(key.getPrecoCusto().multiply(quantidadef));
        }

        return totalpedido;
    }
    public BigDecimal CalcularTotalVenda() {

        BigDecimal totalpedido = new BigDecimal(0.00).setScale(2, RoundingMode.UP);

        for (Item key : getItems().keySet()) {

        	String qtd = getItems().get(key);

            BigDecimal quantidadef = new BigDecimal(qtd);

            totalpedido = totalpedido.add(key.getPrecoUnitario().multiply(quantidadef));

        }
        return totalpedido;

    }
    
    public void OperacaoEstoqueVenda(PedidoVenda pedido){
    	
    	for (int i = 0; i < pedido.getItems().size(); i++) {
    		
    		Item item = pedido.getItems().get(i);
    		
    		RetirarProdutoEstoque(item);
    		
    		System.out.println("Nome do item Retirado do estoque:" + item.getDescricao());
    		System.out.println("Quantidade do item Retirado do estoque:" + item.getQtd());
			
		}
    	
    	
    }
    
    
    public void OperacaoEstoqueCompra(PedidoCompra pedido){
    	
    	for (int i = 0; i < pedido.getItems().size(); i++) {
    		
    		Item item = pedido.getItems().get(i);
    		
    		AddProdutoEstoque(item);
    		
    		System.out.println("Nome do item Adicionado do estoque:" + item.getDescricao());
    		System.out.println("Quantidade do item Adicionado do estoque:" + item.getQtd());
			
		}
    	
    	
    }
    
//	private BigDecimal TotalItem(List<Item> itens){
//		
////	    Collection<String> itenstotal = estoque.getItems().values();
//			
//			BigDecimal total = new BigDecimal("0.00");
//
//	    for (Item item : items) {
//
//	    	BigDecimal totalaux = item.getQtd();
//
//	    	total = total.add(totalaux);
//
//	    }
//	    
//	    return total;
//	}
	
	
	

}

