package com.gabriel.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String logadouru;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	
    @JsonIgnore
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="cidade_id")
	private Cidade cidade;
   
   public Endereco() {
	   
   }



public Endereco(Integer id, String logadouru, String numero, String complemento, String bairro, String cep, Cidade cidade, Cliente cliente) {
	super();
	this.id = id;
	this.logadouru = logadouru;
	this.numero = numero;
	this.complemento = complemento;
	this.bairro = bairro;
	this.cep = cep;
	this.cliente = cliente;
	this.setCidade(cidade);
	
	
}



public Integer getId() {
	return id;
}



public void setId(Integer id) {
	this.id = id;
}



public String getLogadouru() {
	return logadouru;
}



public void setLogadouru(String logadouru) {
	this.logadouru = logadouru;
}



public String getNumero() {
	return numero;
}



public void setNumero(String numero) {
	this.numero = numero;
}



public String getComplemento() {
	return complemento;
}



public void setComplemento(String complemento) {
	this.complemento = complemento;
}



public String getBairro() {
	return bairro;
}



public void setBairro(String bairro) {
	this.bairro = bairro;
}



public String getCep() {
	return cep;
}



public void setCep(String cep) {
	this.cep = cep;
}



public Cliente getCliente() {
	return cliente;
}



public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}



public Cidade getCidade() {
	return cidade;
}



public void setCidade(Cidade cidade) {
	this.cidade = cidade;
}



@Override
public int hashCode() {
	return Objects.hash(id);
}



@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Endereco other = (Endereco) obj;
	return Objects.equals(id, other.id);
}
   
   
   
}
