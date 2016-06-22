package br.ufrn.imd.services;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.ufrn.imd.dominio.Setor;

public class SetorService extends GenericService {
	ResteasyClient client = new ResteasyClientBuilder().build();;

	public SetorService() {

	}

	public String setorListar() {
		ResteasyWebTarget target = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/setores");
		Response response = target.request().get();

		String value = response.readEntity(String.class);
		System.out.println(value);

		response.close();
		return value;
	}

	public int setorCriar(Setor setor) {
		ResteasyWebTarget add = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/setores");

		Response addResponse = add.request().post(Entity.entity(setor, "application/json"));
		System.out.println("HTTP Response Code:" + addResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(addResponse.readEntity(String.class));

		return addResponse.getStatus();
	}

	public String setorBuscar(String nome) {
		ResteasyWebTarget target = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/setoresFilter?nomeSetor=" + nome);
		Response response = target.request().get();

		System.out.println("HTTP Response Code:" + response.getStatus());

		String value = response.readEntity(String.class);
		System.out.println(value);

		response.close();
		return value;
	}

	public int setorDeletar(Setor setor) {
		ResteasyWebTarget delete = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/setores/" + setor.getIdSetor());

		Response deleteResponse = delete.request().delete();
		System.out.println("HTTP Response Code:" + deleteResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(deleteResponse.readEntity(String.class));

		return deleteResponse.getStatus();
	}
	
	public int setorEditar(Setor setor){
		ResteasyWebTarget update = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/setores/");

		Response updateResponse = update.request().put(Entity.entity(setor, "application/json"));
		System.out.println("HTTP Response Code:" + updateResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(updateResponse.readEntity(String.class));

		return updateResponse.getStatus();
		
	}
}
