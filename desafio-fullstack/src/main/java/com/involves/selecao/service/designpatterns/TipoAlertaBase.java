package com.involves.selecao.service.designpatterns;

import com.involves.selecao.alerta.Pesquisa;

/**
 * Classe criada para a utilização do design pattern <b>Method Template</b>.
 *
 * @author Paulo Fernandes
 *
 */
public abstract class TipoAlertaBase implements TipoAlerta {

	private TipoAlerta proximo;

	@Override
	public void executar(Pesquisa pesquisa) {
		processarTipoAlerta(pesquisa);

		if (proximo != null) {
			proximo.executar(pesquisa);
		}
	}

	@Override
	public void setProximo(TipoAlerta proximo) {
		this.proximo = proximo;
	}

	public abstract void processarTipoAlerta(Pesquisa pesquisa);

}
