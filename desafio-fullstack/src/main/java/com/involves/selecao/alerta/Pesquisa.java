package com.involves.selecao.alerta;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Pesquisa {
	private int id;
	private String rotulo;
	private String notificante;
	// Anota��o adicionada para manter as convers�es Java de nomenclatura
	@SerializedName(value = "ponto_de_venda")
	private String pontoDeVenda;
	private String produto;
	// Anota��o adicionada para manter as convers�es Java de nomenclatura
	@SerializedName(value = "preco_estipulado")
	private String precoEstipulado;
	private List<Resposta> respostas;

	// Anota��o adicionada para manter as convers�es Java de nomenclatura
	// Atibutos adicionados para os novos alertas
	@SerializedName(value = "participacao_estipulada")
	private String participacaoEstipulada;
	private String categoria;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public String getNotificante() {
		return notificante;
	}

	public void setNotificante(String notificante) {
		this.notificante = notificante;
	}

	public String getPontoDeVenda() {
		return pontoDeVenda;
	}

	public void setPontoDeVenda(String pontoDeVenda) {
		this.pontoDeVenda = pontoDeVenda;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getPrecoEstipulado() {
		return precoEstipulado;
	}

	public void setPrecoEstipulado(String precoEstipulado) {
		this.precoEstipulado = precoEstipulado;
	}

	// Aadicionados para os novos alertas
	public String getParticipacaoEstipulada() {
		return participacaoEstipulada;
	}

	public void setParticipacaoEstipulada(String participacaoEstipulada) {
		this.participacaoEstipulada = participacaoEstipulada;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
