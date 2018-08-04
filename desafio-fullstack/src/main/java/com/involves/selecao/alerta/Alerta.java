package com.involves.selecao.alerta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Alerta {

	private String pontoDeVenda;
	private String descricao;
	private String produto;
	// Anotação adicionada para alterar o nome usado no JSON
	@JsonProperty(value = "flagTipo")
	private Integer flTipo;
	private Integer margem;
	// Incluído para fazer a persistência dos novos alertas
	private String categoria;

	public String getPontoDeVenda() {
		return pontoDeVenda;
	}

	public Alerta setPontoDeVenda(String pontoDeVenda) {
		this.pontoDeVenda = pontoDeVenda;
		return this;
	}

	public String getDescricao() {
		return descricao;
	}

	public Alerta setDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	public String getProduto() {
		return produto;
	}

	public Alerta setProduto(String produto) {
		this.produto = produto;
		return this;
	}

	public Integer getFlTipo() {
		return flTipo;
	}

	public Alerta setFlTipo(Integer flTipo) {
		this.flTipo = flTipo;
		return this;
	}

	public Integer getMargem(){
		return margem;
	}

	public Alerta setMargem(Integer margem){
		this.margem = margem;
		return this;
	}

	// Incluído para os novos alertas
	public String getCategoria() {
		return categoria;
	}

	public Alerta setCategoria(String categoria) {
		this.categoria = categoria;
		return this;
	}

}
