package br.ufrn.imd.services;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.ufrn.imd.dominio.Vinculo;

public class VinculoService extends GenericService {
	ResteasyClient client = new ResteasyClientBuilder().build();

	public VinculoService() {
		
	}

	public String vinculoListar() {
		ResteasyWebTarget target = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/vinculos");
		Response response = target.request().get();

		String value = response.readEntity(String.class);
		System.out.println(value);

		response.close();
		return value;
	}

	public int vinculoCriar(Vinculo vinculo) {
		ResteasyWebTarget add = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/vinculos");

		Response addResponse = add.request().post(Entity.entity(vinculo, "application/json"));
		System.out.println("HTTP Response Code:" + addResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(addResponse.readEntity(String.class));

		return addResponse.getStatus();
	}

	public String vinculoBuscar(String nome) {
		ResteasyWebTarget target = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/vinculosFilter?nomeVinculo=" + nome);
		Response response = target.request().get();

		System.out.println("HTTP Response Code:" + response.getStatus());

		String value = response.readEntity(String.class);
		System.out.println(value);

		response.close();
		return value;
	}

	public int vinculoDeletar(Vinculo vinculo) {
		ResteasyWebTarget delete = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/vinculos/" + vinculo.getIdVinculo());

		Response deleteResponse = delete.request().delete();
		System.out.println("HTTP Response Code:" + deleteResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(deleteResponse.readEntity(String.class));

		return deleteResponse.getStatus();
	}
	
	public int vinculoEditar(Vinculo vinculo){
		ResteasyWebTarget update = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/vinculos/");

		Response updateResponse = update.request().put(Entity.entity(vinculo, "application/json"));
		System.out.println("HTTP Response Code:" + updateResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(updateResponse.readEntity(String.class));

		return updateResponse.getStatus();
		
	}
	
}
