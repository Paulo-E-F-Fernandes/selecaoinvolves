package com.involves.selecao.service.designpatterns.enums;

public enum DescricaoTipoAlerta {

	RUPTURA_DETECTADA(1, "Ruptura detectada!"),
	ACIMA_DO_ESTIPULADO(2, "Pre�o acima do estipulado!"),
	ABAIXO_DO_ESTIPULADO(3, "Pre�o abaixo do estipulado!"),
	PARTICIPACAO_INFERIOR_ESTIPULADO(4, "Participa��o inferior ao estipulado!"),
	PARTICIPACAO_SUPERIOR_ESTIPULADO(5, "Participa��o superior ao estipulado!");

	private final Integer flag;
	private final String descricao;

	private DescricaoTipoAlerta(Integer flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Integer getFlag() {
		return flag;
	}

	public String getDescricao() {
		return descricao;
	}

}
