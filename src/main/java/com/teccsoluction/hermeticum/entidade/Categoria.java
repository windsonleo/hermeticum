package com.teccsoluction.hermeticum.entidade;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CATEGORIA")
public class Categoria extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    
    @Column(name = "nome", nullable = false)
    private String nome;

    //    (cascade = { CascadeType.ALL })
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Categoria.class, optional = true)
    @JoinColumn(name = "catpai_id", nullable = true)
    private Categoria catpai;


    @JsonIgnore
    @OneToMany(mappedBy = "categoria", cascade = {CascadeType.REFRESH},fetch=FetchType.EAGER)
    private List<Produto> produtos;


    //CONSTRUTOR PADRAO

    public Categoria() {
        // TODO Auto-generated constructor stub
        produtos = new ArrayList<>();
    }


//    public Categoria(String id, String nome,Categoria catpai,boolean ativo) {
//        // TODO Auto-generated constructor stub
//    	this.id = id;
//    	this.nome = nome;
//    	this.catpai=catpai;
//    	this.ativo = ativo;
//    }
//
//
//    public Categoria(String id, String nome,boolean ativo) {
//        // TODO Auto-generated constructor stub
//    	this.id = id;
//    	this.nome = nome;
//    	this.ativo = ativo;
//    }


    @Override
    public String toString() {
        return nome.toUpperCase();
    }
}
