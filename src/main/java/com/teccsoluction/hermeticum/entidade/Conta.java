package com.teccsoluction.hermeticum.entidade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.teccsoluction.hermeticum.framework.BaseEntity;
import com.teccsoluction.hermeticum.util.StatusConta;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Conta extends BaseEntity   {

	
    

	
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date data;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date datavencimento;
    

    @Column(name = "valor")
    private BigDecimal  valor ;

    
    @Enumerated(EnumType.STRING)
    private StatusConta status;

    
    private boolean pago = false;
    
//    @ManyToOne
//    @JoinColumn(name = "formapagamento_id")
//    private FormaPagamento formapagamento;
    
//    @OneToOne
//    @JoinColumn(name = "pagamento_id", referencedColumnName = "id")
//    private Pagamento pagamento;
    
    

  @OneToMany( mappedBy="conta", fetch=FetchType.LAZY)
  private List<Pedido> pedidosAll = new ArrayList<Pedido>();
    
    public Conta() {

    	
    }

  

    @Override
    public String toString() {

        return String.valueOf(id);
    }    
    
}
