package com.involves.selecao.service.designpatterns;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.gateway.AlertaGateway;
import com.involves.selecao.service.designpatterns.enums.DescricaoTipoAlerta;
import com.involves.selecao.service.designpatterns.utils.Constantes;

public class ParticipacaoInferiorEstipulado extends TipoAlertaBase {

	private final AlertaGateway gateway;

	public ParticipacaoInferiorEstipulado(AlertaGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public void processarTipoAlerta(Pesquisa pesquisa) {
		pesquisa.getRespostas().forEach(resposta -> {
			if (Constantes.PARTICIPACAO.equals(resposta.getPergunta())) {
				final int participacaoEstipulada = Integer.parseInt(pesquisa.getParticipacaoEstipulada());
				final int participacaoEfetiva = Integer.parseInt(resposta.getResposta());

				if (participacaoEfetiva < participacaoEstipulada) {
					final Alerta alerta = new Alerta();
					alerta.setDescricao(DescricaoTipoAlerta.PARTICIPACAO_INFERIOR_ESTIPULADO.getDescricao())
					.setFlTipo(DescricaoTipoAlerta.PARTICIPACAO_INFERIOR_ESTIPULADO.getFlag())
					.setPontoDeVenda(pesquisa.getPontoDeVenda())
					.setCategoria(pesquisa.getCategoria())
					.setMargem(participacaoEstipulada - participacaoEfetiva);

					gateway.salvar(alerta);
				}
			}
		});
	}

}
