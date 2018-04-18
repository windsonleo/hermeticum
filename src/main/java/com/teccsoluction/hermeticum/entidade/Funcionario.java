package com.teccsoluction.hermeticum.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.teccsoluction.hermeticum.framework.BaseEntity;
import com.teccsoluction.hermeticum.util.Genero;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "FUNCIONARIO")
//@XmlRootElement(name = "garcon")
public class Funcionario extends BaseEntity  implements Serializable {

    private static final long serialVersionUID = 121L;
    
    

    @Column(name = "nome")
    private String nome;

    @Column(name = "foto")
    private String foto;
    
    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    private Genero genero;

//    @JsonIgnore
//    @OneToMany(mappedBy = "garcon",fetch=FetchType.EAGER)
//    private List<PedidoVenda> pedidos;
    
    @OneToOne
    private Usuario usuario;


    public Funcionario() {

    	
    }


    public Funcionario(Long id, String nome, String foto, boolean ativo) {
        super();
//        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return nome.toUpperCase();
    }

}
