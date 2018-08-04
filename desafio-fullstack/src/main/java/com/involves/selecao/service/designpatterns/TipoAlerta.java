package com.involves.selecao.service.designpatterns;

import com.involves.selecao.alerta.Pesquisa;

public interface TipoAlerta {

	void executar(Pesquisa pesquisa);

	void setProximo(TipoAlerta proximo);

}
