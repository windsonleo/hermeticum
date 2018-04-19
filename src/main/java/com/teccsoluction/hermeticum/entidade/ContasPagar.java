package com.teccsoluction.hermeticum.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.teccsoluction.hermeticum.util.StatusConta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CONTA_A_PAGAR")
public class ContasPagar extends Conta implements Serializable {


    private static final long serialVersionUID = 1L;

    
//    private Fornecedor fornecedor;
    
    @Transient
    private Recebimento recebimento;
    
   
    //CONSTRUTOR PADRAO
    public ContasPagar() {
        super();

        
    }
    
    
    public ContasPagar(Recebimento recebimento) {
        super();
        
//        FormaPagamento formapag = new FormaPagamento();

     
        
        this.setValor(recebimento.getTotal());
        this.setAtivo(true);
//        this.setNovo(true);
        this.setPago(false);
        this.setStatus(StatusConta.ABERTA);
//        this.setData_criacao(new Date());
        this.setData(new Date());
        this.setDatavencimento(new Date());
        this.setStatus(StatusConta.ABERTA);
        this.getPedidosAll().add(recebimento.getPedidocompra());

        
    }
    
    
    public BigDecimal CalcularTotal() {

    	BigDecimal totalpedido = new BigDecimal("0.00").setScale(2, RoundingMode.UP);
    	BigDecimal totalpedidoaux = new BigDecimal("0.00").setScale(2, RoundingMode.UP);


        for (Item key : recebimento.getItems()) {
        	
        	//QTD ITEM
        	BigDecimal qtd = key.getQtd();
        	
        	
        	
//        	totalpedidoaux = new  BigDecimal(total);
        	
        	BigDecimal totalped = key.getPrecoUnitario();
        	
        	totalped = totalped.multiply(qtd);
        	

        	totalpedido = totalpedido.add(totalped);
        }



        return totalpedido;
    }
    

    @Override
    public String toString() {

    	return super.toString().toUpperCase();
    }
    

}
