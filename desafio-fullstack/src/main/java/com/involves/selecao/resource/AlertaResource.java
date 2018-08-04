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

	//
	@GetMapping("/")
	public List<Alerta> listar() {
		return buscaAlertasService.buscarTodos();
	}
}
