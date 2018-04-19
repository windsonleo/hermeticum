package com.teccsoluction.hermeticum.entidade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.teccsoluction.hermeticum.framework.BaseEntity;
import com.teccsoluction.hermeticum.util.StatusPedido;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pedido extends BaseEntity   {
	
    

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date data;


    @Column(name = "total")
    private BigDecimal  total ;

//    @ManyToMany(mappedBy = "pedidos",fetch=FetchType.EAGER)
//    @JsonIgnore
//    private List<Pagamento> pagamento;

    //aberto,pendente,fechado,cancelado
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    
    private boolean ispago = false;
    
    @ManyToOne
    @JoinColumn(name="conta_id")
    private Conta conta;
    
    
    private String obs;
    
//    @OneToMany(fetch=FetchType.EAGER)
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "pedido_formas", 
    joinColumns = @JoinColumn(name = "id"))
    private Set<FormaPagamento> formas;
    
    @Transient
    private BigDecimal totalpago;

    
    public Pedido() {
    	
    	formas = new HashSet<FormaPagamento>();

    	
    }

    public BigDecimal getTotal() {


        return total;
    }

    public void setTotal(BigDecimal total) {
       
    	this.total = total;
    }


    @Override
    public String toString() {

        return String.valueOf(id);
    }
    
    

    public BigDecimal CalcularTotal(List<Item> itens) {

    	BigDecimal totalpedido = new BigDecimal("0.00").setScale(2, RoundingMode.UP);
    	BigDecimal totalpedidoaux = new BigDecimal("0.00").setScale(2, RoundingMode.UP);


        for (Item key : itens) {
        	
        	//QTD ITEM
        	BigDecimal total = key.getTotalItem();
        	
        	totalpedidoaux = new  BigDecimal("0.00");
        	
        	BigDecimal qtd = key.getQtd();
        	
        	BigDecimal totalped = new BigDecimal(key.getPrecoUnitario().toString());
        	
        	totalped = totalped.multiply(qtd);
        	

        	totalpedido = totalpedido.add(totalped);
        }



        return totalpedido;
    }
    
    
    public void addForma(FormaPagamento item){
    	
    	
    	this.getFormas().add(item);
    	
    	
    	
    }
    
    
    public void removeForma(FormaPagamento item){
    	
    	
    	this.getFormas().remove(item);
	
    	
    }
    
    
    public BigDecimal CalculaTotalPago(Set<FormaPagamento> formas){
    	
    	BigDecimal totalpedido = new BigDecimal("0.00").setScale(2, RoundingMode.UP);
//    	BigDecimal totalpedidoaux = new BigDecimal("0.00").setScale(2, RoundingMode.UP);


        for (FormaPagamento key : formas) {
        	
        	//QTD ITEM
//        	BigDecimal total = key.getValorpago();
        	
//        	totalpedidoaux = new  BigDecimal("0.00");
        	
//        	BigDecimal qtd = key.getQtd();
        	
        	BigDecimal totalped = new BigDecimal(key.getValorpago().toString());
        	
//        	totalped = totalped.add(augend);
        	

        	totalpedido = totalpedido.add(totalped);
        }
    	
    	
    	
    	return totalpedido;
    }
    
    
    
    
}
