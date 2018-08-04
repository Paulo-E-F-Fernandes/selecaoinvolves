package com.involves.selecao.resource;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.service.BuscaAlertasService;
import com.involves.selecao.service.ProcessadorAlertas;

@RestController
@RequestMapping("/alertas")
@CrossOrigin("${origem-permitida}")
public class AlertaResource {

	@Autowired
	private BuscaAlertasService buscaAlertasService;

	@Autowired
	private ProcessadorAlertas processador;

	@GetMapping
	public List<Alerta> alertas() {
		return buscaAlertasService.buscarTodos();
	}

	@GetMapping("/processar")
	public void processar() {
		try {
			processador.processa();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo criado para expor o servi�o que retorna todos os {@link Alerta} persistidos ap�s
	 *  a chamada ao servi�o <b>{dom�nio}/alertas/processar</b>.<br><br>
	 *
	 * Para chamar esse servi�o � necess�rio fazer um <b>GET</b> em <b>{dom�nio}/alertas/</b>.
	 *
	 * @return {@link List} de {@link Alerta} persistidos.
	 */
	@GetMapping("/")
	public List<Alerta> listar() {
		return buscaAlertasService.buscarTodos();
	}
}
