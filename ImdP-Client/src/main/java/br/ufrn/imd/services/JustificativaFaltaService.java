package br.ufrn.imd.services;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.ufrn.imd.dominio.JustificativaFalta;

public class JustificativaFaltaService extends GenericService {
	ResteasyClient client = new ResteasyClientBuilder().build();

	public JustificativaFaltaService() {
		
	}

	public String JustificativaFaltaListar() {
		ResteasyWebTarget target = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/justificativaFaltas");
		Response response = target.request().get();

		String value = response.readEntity(String.class);
		System.out.println(value);

		response.close();
		return value;
	}

	public int JustificativaFaltaCriar(JustificativaFalta justificativaFalta) {
		ResteasyWebTarget add = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/justificativaFaltas");

		Response addResponse = add.request().post(Entity.entity(justificativaFalta, "application/json"));
		System.out.println("HTTP Response Code:" + addResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(addResponse.readEntity(String.class));

		return addResponse.getStatus();
	}

	public String JustificativaFaltaBuscar(String nome) {
		ResteasyWebTarget target = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/justificativaFaltasFilter?nomeJustificativaFalta=" + nome);
		Response response = target.request().get();

		System.out.println("HTTP Response Code:" + response.getStatus());

		String value = response.readEntity(String.class);
		System.out.println(value);

		response.close();
		return value;
	}

	public int JustificativaFaltaDeletar(JustificativaFalta justificativaFalta) {
		ResteasyWebTarget delete = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/justificativaFaltas/" + justificativaFalta.getIdJustificativaFalta());

		Response deleteResponse = delete.request().delete();
		System.out.println("HTTP Response Code:" + deleteResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(deleteResponse.readEntity(String.class));

		return deleteResponse.getStatus();
	}
	
	public int JustificativaFaltaEditar(JustificativaFalta justificativaFalta){
		ResteasyWebTarget update = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/justificativaFaltas/");

		Response updateResponse = update.request().put(Entity.entity(justificativaFalta, "application/json"));
		System.out.println("HTTP Response Code:" + updateResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(updateResponse.readEntity(String.class));

		return updateResponse.getStatus();
		
	}
}
