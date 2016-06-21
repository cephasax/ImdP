package br.ufrn.imd.services;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.ufrn.imd.dominio.Ponto;

public class PontoService extends GenericService {
	ResteasyClient client = new ResteasyClientBuilder().build();

	public PontoService() {

	}

	public String pontoListar() {
		ResteasyWebTarget target = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/pontos");
		Response response = target.request().get();

		String value = response.readEntity(String.class);
		System.out.println(value);

		response.close();
		return value;
	}

	public int pontoCriar(Ponto ponto) {
		ResteasyWebTarget add = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/pontos");

		Response addResponse = add.request().post(Entity.entity(ponto, "application/json"));
		System.out.println("HTTP Response Code:" + addResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(addResponse.readEntity(String.class));

		return addResponse.getStatus();
	}

	public String pontoBuscar(String nome) {
		ResteasyWebTarget target = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/pontosFilter?nomePonto=" + nome);
		Response response = target.request().get();

		System.out.println("HTTP Response Code:" + response.getStatus());

		String value = response.readEntity(String.class);
		System.out.println(value);

		response.close();
		return value;
	}

	public int pontoDeletar(Ponto ponto) {
		ResteasyWebTarget delete = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/pontos" + ponto.getIdPonto());

		Response deleteResponse = delete.request().delete();
		System.out.println("HTTP Response Code:" + deleteResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(deleteResponse.readEntity(String.class));

		return deleteResponse.getStatus();
	}

	public int pontoEditar(Ponto ponto) {
		ResteasyWebTarget update = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/pontos");

		Response updateResponse = update.request().put(Entity.entity(ponto, "application/json"));
		System.out.println("HTTP Response Code:" + updateResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(updateResponse.readEntity(String.class));

		return updateResponse.getStatus();

	}

}
