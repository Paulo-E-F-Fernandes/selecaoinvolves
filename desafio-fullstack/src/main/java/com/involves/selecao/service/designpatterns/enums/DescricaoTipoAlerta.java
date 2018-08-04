package com.involves.selecao.service.designpatterns.enums;

public enum DescricaoTipoAlerta {

	RUPTURA_DETECTADA(1, "Ruptura detectada!"),
	ACIMA_DO_ESTIPULADO(2, "Preço acima do estipulado!"),
	ABAIXO_DO_ESTIPULADO(3, "Preço abaixo do estipulado!"),
	PARTICIPACAO_INFERIOR_ESTIPULADO(4, "Participação inferior ao estipulado!"),
	PARTICIPACAO_SUPERIOR_ESTIPULADO(5, "Participação superior ao estipulado!");

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
