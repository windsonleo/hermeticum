package com.teccsoluction.hermeticum.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.teccsoluction.hermeticum.framework.BaseEntity;
import com.teccsoluction.hermeticum.util.Genero;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "CLIENTE")
public class Cliente extends BaseEntity implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_nascimento")
    private Date datanascimento;

    @Column(name = "foto")
    private String foto;

    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    private Genero genero;

//    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
//    private Endereco endereco;
//
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "cliente",fetch=FetchType.EAGER)
//    private Set<PedidoVenda> listaPedidoVenda;
//    
//    
//    @OneToMany(mappedBy = "cliente",fetch=FetchType.EAGER)
//    private Set<Reserva> reservas;


//    @JsonIgnore
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @OneToMany
//    private List<DevolucaoVenda> listaDevolucaoVenda;
//

    public Cliente() {

    }

//    public Cliente(Endereco endereco) {
//
//        this.endereco = endereco;
//    }


    @Override
    public String toString() {
        return nome.toUpperCase();
    }


}