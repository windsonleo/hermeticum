package com.teccsoluction.hermeticum.entidade;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.teccsoluction.hermeticum.framework.BaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "EMPRESA")
public class Empresa extends BaseEntity  implements Serializable {

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
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "telefone")
    private String telefone;
    
    @Column(name = "foto")
    private String foto;
    
    
    public Empresa() {
        // TODO Auto-generated constructor stub

    }

    @Override
    public String toString() {
        return nomefantasia.toUpperCase();
    }

}
