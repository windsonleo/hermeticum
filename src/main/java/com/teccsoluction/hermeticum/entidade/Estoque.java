package com.teccsoluction.hermeticum.entidade;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.teccsoluction.hermeticum.framework.BaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ESTOQUE")
public class Estoque extends BaseEntity  implements Serializable {


    private static final long serialVersionUID = 1L;


    @Column(name = "nome", nullable = false)
    private String nome;
 
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "itens_estoque", joinColumns = @JoinColumn(name = "id"))
//    @Lob
//    @Column(name = "qtd")
//    @MapKeyColumn(name = "idit")
    private List<Item> items;


    public Estoque() {

    	
    }



    @Override
    public String toString() {
        return nome.toUpperCase();
    }

    
    
    public void AddProdutoEstoque(Item produto) {

    	BigDecimal vantigo= new BigDecimal("0.00");
        BigDecimal vnovo = produto.getQtd();
        BigDecimal novo = new BigDecimal("0.00");
        BigDecimal antigo = new BigDecimal("0.00");
        
        if (getItems().equals(produto)) {
        	
			System.out.println("entrou no if fora do for estoque add produto" + produto.getCodigo());

        	
//        	for(Item it : items){
//        		
//        		if(produto.getCodigo().equals(it.getCodigo())){
        			
//        			vantigo = it.getQtd();
			
					int indexlist = getItems().indexOf(produto);
			
					vantigo = getItems().get(indexlist).getQtd();
                    novo = novo.add(vantigo).add(vnovo);
                    produto.setQtd(novo);
                    
//                	System.out.println("entrou no if dentro do for estoque add produto" + it.getCodigo());
//        			System.out.println("entrou no if dentro do for estoque add produto qtd it" + it.getQtd());
        			System.out.println("entrou no if dentro do for estoque add produto qtd prod" + produto.getQtd());

                   
//        			
//        		}
//        		
//        	}
        	
        	 items.add(produto);
            

        	
        }else {
			System.out.println("não entrou no if dentro estoqueAdd qtd prod" + produto.getQtd());

        	
        	  novo = novo.add(antigo).add(vnovo);	
        	  produto.setQtd(novo);
        	  items.add(produto);
        	
        }
    	
//    	getItems().add(produto);

    }

    public void RetirarProdutoEstoque(Item produto) {

    	BigDecimal vantigo= new BigDecimal("0.00");
        BigDecimal vnovo = produto.getQtd();
        BigDecimal novo = new BigDecimal("0.00");
        BigDecimal antigo = new BigDecimal("0.00");
        
        if (getItems().contains(produto)) {
        	
			System.out.println("entrou no if fora do for estoque retirar produto" + produto.getCodigo());


			
					int indexlist = getItems().indexOf(produto);
        			
					vantigo = getItems().get(indexlist).getQtd();
                   
        			novo = novo.add(vantigo).subtract(vnovo);
                    
                    produto.setQtd(novo);
                   
        			System.out.println("entrou no if dentro do for estoque retirar produto qtd prod" + produto.getQtd());

        	
        	 items.add(produto);

       	
       }else {
       	  novo = novo.add(antigo).subtract(vnovo);
       	  produto.setQtd(novo);
       	  items.add(produto);
        	
        }
    	
//    	items.add(produto);

        
    }


    public BigDecimal CalcularTotalCusto() {

        BigDecimal totalpedido = new BigDecimal(0.000).setScale(4, RoundingMode.UP);


        for (Item key : getItems()) {

        	BigDecimal qtd = key.getQtd();

//            BigDecimal quantidadef = new BigDecimal(qtd);

            totalpedido = totalpedido.add(key.getPrecoCusto().multiply(qtd));
        }

        return totalpedido;
    }

    public BigDecimal CalcularTotalVenda() {

        BigDecimal totalpedido = new BigDecimal(0.000).setScale(4, RoundingMode.UP);

        for (Item key : getItems()) {

        	BigDecimal qtd = key.getQtd();

//            BigDecimal quantidadef = new BigDecimal(qtd);

            totalpedido = totalpedido.add(key.getPrecoUnitario().multiply(qtd));

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

