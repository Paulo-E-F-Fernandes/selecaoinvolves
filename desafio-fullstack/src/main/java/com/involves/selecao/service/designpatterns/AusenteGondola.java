package com.involves.selecao.service.designpatterns;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.gateway.AlertaGateway;
import com.involves.selecao.service.designpatterns.enums.DescricaoTipoAlerta;
import com.involves.selecao.service.designpatterns.utils.Constantes;

public class AusenteGondola extends TipoAlertaBase {

	private final AlertaGateway gateway;

	public AusenteGondola(AlertaGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public void processarTipoAlerta(Pesquisa pesquisa) {
		pesquisa.getRespostas().forEach(resposta -> {
			if (Constantes.PERGUNTA_SITUACAO_PRODUTO.equals(resposta.getPergunta()) && Constantes.PERGUNTA_AUSENTE_GONDOLA.equals(resposta.getResposta())) {
				final Alerta alerta = new Alerta();
				alerta.setPontoDeVenda(pesquisa.getPontoDeVenda());
				alerta.setDescricao(DescricaoTipoAlerta.RUPTURA_DETECTADA.getDescricao());
				alerta.setProduto(pesquisa.getProduto());
				alerta.setFlTipo(DescricaoTipoAlerta.RUPTURA_DETECTADA.getFlag());
				gateway.salvar(alerta);
			}
		});
	}

}
