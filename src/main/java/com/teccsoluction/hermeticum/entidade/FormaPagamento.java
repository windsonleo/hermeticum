package com.teccsoluction.hermeticum.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.teccsoluction.hermeticum.util.TipoFormaPagamento;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
//@Entity
//@Table(name = "FORMAPAGAMENTO")
public class FormaPagamento  implements Serializable {

    /**
     *
     */
	
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "ativo")
    private boolean ativo;

    private String nome;

    // avista,cartao credito, cartao debito,boleto,crediario
    @Enumerated(EnumType.STRING)
    private TipoFormaPagamento tipo;


    private int parcelas;


    private float percdesconto;
    
    
    private BigDecimal valorpago;
    

//    @ManyToMany(mappedBy = "formaPagamentos",fetch=FetchType.LAZY)
//    private Set<Pagamento> pagamentos;
//    private Set<Pagamento> pagamentos;
    //
//    @ManyToOne
//    @JoinColumn(name = "pedido_id", nullable = true)
//    private Pedido pedido;,
    
//    @ElementCollection(fetch=FetchType.EAGER)
//    @CollectionTable(name = "pedidos_formas", joinColumns = @JoinColumn(name = "id"))
    @ManyToMany(mappedBy = "formas",fetch=FetchType.LAZY)
    private Set<PedidoVenda> pedidos;

    public FormaPagamento() {
        // TODO Auto-generated constructor stub
    }
    
    
    


    @Override
    public String toString() {
        return nome;
    }


}
