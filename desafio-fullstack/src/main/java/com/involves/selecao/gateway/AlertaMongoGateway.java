package com.involves.selecao.gateway;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.gateway.mongo.MongoDbFactory;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class AlertaMongoGateway implements AlertaGateway{

	@Autowired
	private MongoDbFactory mongoFactory;

	@Override
	public void salvar(Alerta alerta) {
		final MongoDatabase database = mongoFactory.getDb();
		final MongoCollection<Document> collection = database.getCollection("Alertas");
		final Document doc = new Document("ponto_de_venda", alerta.getPontoDeVenda())
				.append("descricao", alerta.getDescricao())
				.append("tipo", alerta.getFlTipo())
				.append("margem", alerta.getMargem())
				.append("produto", alerta.getProduto())
				// Adicionado para os novos alertas
				.append("categoria", alerta.getCategoria());
		collection.insertOne(doc);
	}

	@Override
	public List<Alerta> buscarTodos() {
		final MongoDatabase database = mongoFactory.getDb();
		final MongoCollection<Document> collection = database.getCollection("Alertas");
		final FindIterable<Document> db = collection.find();
		final List<Alerta> alertas = new ArrayList<>();
		for (final Document document : db) {
			final Alerta alerta = new Alerta()
					.setDescricao(document.getString("descricao"))
					.setFlTipo(document.getInteger("tipo"))
					.setMargem(document.getInteger("margem"))
					.setPontoDeVenda(document.getString("ponto_de_venda"))
					.setProduto(document.getString("produto"))
					// Adicionado para os novos alertas
					.setCategoria(document.getString("categoria"));
			alertas.add(alerta);
		}
		return alertas;
	}
}
