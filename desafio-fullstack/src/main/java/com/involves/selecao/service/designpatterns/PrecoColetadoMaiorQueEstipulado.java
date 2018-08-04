package com.involves.selecao.service.designpatterns;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.gateway.AlertaGateway;
import com.involves.selecao.service.designpatterns.enums.DescricaoTipoAlerta;
import com.involves.selecao.service.designpatterns.utils.Constantes;

public class PrecoColetadoMaiorQueEstipulado extends TipoAlertaBase {

	private final AlertaGateway gateway;

	public PrecoColetadoMaiorQueEstipulado(AlertaGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public void processarTipoAlerta(Pesquisa pesquisa) {
		pesquisa.getRespostas().forEach(resposta -> {
			if (Constantes.PERGUNTA_PRECO_PRODUTO.equals(resposta.getPergunta())) {
				final int precoColetado = Integer.parseInt(resposta.getResposta());
				final int precoEstipulado = Integer.parseInt(pesquisa.getPrecoEstipulado());
				if (precoColetado > precoEstipulado) {
					final Alerta alerta = new Alerta();
					final int margem = precoEstipulado - precoColetado;
					alerta.setMargem(margem);
					alerta.setDescricao(DescricaoTipoAlerta.ACIMA_DO_ESTIPULADO.getDescricao());
					alerta.setProduto(pesquisa.getProduto());
					alerta.setPontoDeVenda(pesquisa.getPontoDeVenda());
					alerta.setFlTipo(DescricaoTipoAlerta.ACIMA_DO_ESTIPULADO.getFlag());
					gateway.salvar(alerta);
				}
			}
		});
	}

}
