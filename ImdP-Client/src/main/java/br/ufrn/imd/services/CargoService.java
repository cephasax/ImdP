package br.ufrn.imd.services;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.ufrn.imd.dominio.Cargo;

public class CargoService extends GenericService {
	ResteasyClient client = new ResteasyClientBuilder().build();

	public CargoService() {

	}

	public String CargoListar() {
		ResteasyWebTarget target = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/cargos");
		Response response = target.request().get();

		String value = response.readEntity(String.class);
		System.out.println(value);

		response.close();
		return value;
	}

	public int CargoCriar(Cargo cargo) {
		ResteasyWebTarget add = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/cargos");

		Response addResponse = add.request().post(Entity.entity(cargo, "application/json"));
		System.out.println("HTTP Response Code:" + addResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(addResponse.readEntity(String.class));

		return addResponse.getStatus();
	}

	public String CargoBuscar(String nome) {
		ResteasyWebTarget target = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/cargosFilter?nomeCargo=" + nome);
		Response response = target.request().get();

		System.out.println("HTTP Response Code:" + response.getStatus());

		String value = response.readEntity(String.class);
		System.out.println(value);

		response.close();
		return value;
	}

	public int CargoDeletar(Cargo cargo) {
		ResteasyWebTarget delete = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/cargos/" + cargo.getIdCargo());

		Response deleteResponse = delete.request().delete();
		System.out.println("HTTP Response Code:" + deleteResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(deleteResponse.readEntity(String.class));

		return deleteResponse.getStatus();
	}
	
	public int CargoEditar(Cargo cargo){
		ResteasyWebTarget update = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/cargos/");

		Response updateResponse = update.request().put(Entity.entity(cargo, "application/json"));
		System.out.println("HTTP Response Code:" + updateResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(updateResponse.readEntity(String.class));

		return updateResponse.getStatus();
		
	}
}
