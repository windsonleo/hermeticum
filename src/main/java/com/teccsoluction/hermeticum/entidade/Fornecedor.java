package com.teccsoluction.hermeticum.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.teccsoluction.hermeticum.framework.BaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "FORNECEDOR")
public class Fornecedor extends BaseEntity   implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    

    

   
    @Column(name = "nomefantasia")
    private String nomefantasia;

    @Column(name = "razaosocial")
    private String razaosocial;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "inscricaoestadual")
    private String inscricaoestadual;
    
    @Column(name = "foto")
    private String foto;

    @JsonIgnore
    @OneToMany(mappedBy = "fornecedor")
    private List<Produto> produtos;


//    @JsonIgnore
//    @OneToMany(mappedBy = "fornecedor",fetch=FetchType.EAGER)
//    private List<Recebimento> recebimento;

    
    
    
    public Fornecedor() {
        //    produtos = new ArrayList<>();
    }

    @Override
    public String toString() {
        return nomefantasia.toUpperCase();
    }

}
