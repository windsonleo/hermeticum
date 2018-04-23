package com.teccsoluction.hermeticum.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.teccsoluction.hermeticum.framework.BaseEntity;
import com.teccsoluction.hermeticum.util.SituacaoItem;
import com.teccsoluction.hermeticum.util.UnidadeMedida;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@EqualsAndHashCode(exclude={"codigo","nome","descricao","precoUnitario","precoCusto","un_medida","totalItem","situacao"})
//@ToString(includeFieldNames=false,exclude={"id", "codigo","descricao","precoUnitario","precoCusto","un_medida","totalItem","situacao"})
//@EqualsAndHashCode(exclude={"qtd","nome","precoUnitario","precoCusto","situacao","id"})
//@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ITEM")
public  class Item extends BaseEntity implements Serializable, Comparable<Item>{






	private static final long serialVersionUID = 1L;
    
    private String codigo;

    private String nome;

    private String descricao;

    private BigDecimal precoUnitario;
    
    private BigDecimal precoCusto;
    
  
    @Enumerated(EnumType.STRING)
    private UnidadeMedida un_medida;

    @Transient
    private BigDecimal totalItem ;
    

    @Enumerated(EnumType.STRING)
    private SituacaoItem situacao;
    
    
    private BigDecimal qtd;
    
    @ManyToOne
    private Pedido pedido;
    
    @ManyToOne(targetEntity=Estoque.class,optional=true)
    @JoinColumn(name="estoque_id")
    private Estoque estoque;

    

    public Item(Produto produto) {
    	super();
    	this.id = produto.getId();
        this.codigo = produto.getCodebar();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.precoUnitario = produto.getPrecovenda();
        this.precoCusto = produto.getPrecocusto();
        this.un_medida = produto.getUn_medida();
//        this.situacao = SituacaoItem.AGUARDANDO;

        

    }
    
@Override
public String toString() {

	return codigo.toString();
}

@Override
public int compareTo(Item arg0) {

	return this.codigo.compareTo(arg0.getCodigo());
}

public BigDecimal CalcularTotaItem(String qtd) {

	BigDecimal qtdAuxBig = new BigDecimal(qtd).setScale(2, RoundingMode.UP);


    
  return precoUnitario.multiply(qtdAuxBig);

}


public String TotalizacaoPoritem(String qtd){
	BigDecimal total = getPrecoUnitario();
	BigDecimal qtdaux = new BigDecimal(qtd);
	
	
	return total.multiply(qtdaux).toString();
	
	
}



/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
//@Override
//public int hashCode() {
//	final int prime = 31;
//	int result = 1;
//	result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
//	return result;
//}
//
//
//
///* (non-Javadoc)
// * @see java.lang.Object#equals(java.lang.Object)
// */
//@Override
//public boolean equals(Object obj) {
//	if (this == obj)
//		return true;
//	if (obj == null)
//		return false;
//	if (getClass() != obj.getClass())
//		return false;
//	Item other = (Item) obj;
//	if (codigo == null) {
//		if (other.codigo != null)
//			return false;
//	} else if (!codigo.equals(other.codigo))
//		return false;
//	return true;
//}


/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
	result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	return result;
}

/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Item other = (Item) obj;
	if (codigo == null) {
		if (other.codigo != null)
			return false;
	} else if (!codigo.equals(other.codigo))
		return false;
	if (descricao == null) {
		if (other.descricao != null)
			return false;
	} else if (!descricao.equals(other.descricao))
		return false;
	if (nome == null) {
		if (other.nome != null)
			return false;
	} else if (!nome.equals(other.nome))
		return false;
	return true;
}


}
