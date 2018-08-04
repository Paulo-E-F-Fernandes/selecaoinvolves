package com.involves.selecao.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.gateway.AlertaGateway;
import com.involves.selecao.service.designpatterns.AusenteGondola;
import com.involves.selecao.service.designpatterns.ParticipacaoInferiorEstipulado;
import com.involves.selecao.service.designpatterns.ParticipacaoSuperiorEstipulado;
import com.involves.selecao.service.designpatterns.PrecoColetadoMaiorQueEstipulado;
import com.involves.selecao.service.designpatterns.PrecoColetadoMenorQueEstipulado;
import com.involves.selecao.service.designpatterns.TipoAlerta;

@Service
public class ProcessadorAlertas {

	private static final String URL_PESQUISAS = "https://selecao-involves.agilepromoter.com/pesquisas";

	@Autowired
	private AlertaGateway gateway;

	public void processa() throws IOException {
		final List<Pesquisa> ps = buscarListaDePesquisas();

		final TipoAlerta tipoAlertaInicial = montarCadeiaDeExecucao();

		ps.forEach(pesquisa -> tipoAlertaInicial.executar(pesquisa));
	}

	private List<Pesquisa> buscarListaDePesquisas() throws IOException {
		final URL url = new URL(URL_PESQUISAS);
		final HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		final StringBuilder content = new StringBuilder();
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}

		in.close();

		try {
			final Type pesquisaListType = new TypeToken<ArrayList<Pesquisa>>() {}.getType();
			return new Gson().fromJson(content.toString(), pesquisaListType);
		} catch (final IllegalStateException e) {
			return new ArrayList<>();
		}
	}

	/**
	 * A lógica foi alterada para utilizar o design pattern Chains of Responsability.
	 *
	 * @return o primeiro {@link TipoAlerta} que será executado e apartir desse é chamado os demais.
	 */
	private TipoAlerta montarCadeiaDeExecucao() {
		final TipoAlerta ausenteGondola = new AusenteGondola(gateway);
		final TipoAlerta precoColetadoMaiorQueEstipulado = new PrecoColetadoMaiorQueEstipulado(gateway);
		final TipoAlerta precoColetadoMenorQueEstipulado = new PrecoColetadoMenorQueEstipulado(gateway);
		final TipoAlerta participacaoInferiorEstipulado = new ParticipacaoInferiorEstipulado(gateway);
		final TipoAlerta participacaoSuperiorEstipulado = new ParticipacaoSuperiorEstipulado(gateway);

		ausenteGondola.setProximo(precoColetadoMaiorQueEstipulado);
		precoColetadoMaiorQueEstipulado.setProximo(precoColetadoMenorQueEstipulado);
		precoColetadoMenorQueEstipulado.setProximo(participacaoInferiorEstipulado);
		participacaoInferiorEstipulado.setProximo(participacaoSuperiorEstipulado);
		participacaoSuperiorEstipulado.setProximo(null);

		return ausenteGondola;
	}

}
