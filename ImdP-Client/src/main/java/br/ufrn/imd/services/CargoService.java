package br.ufrn.imd.services;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
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

	public void CargoCriar(Cargo cargo) {
		ResteasyWebTarget add = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/cargos");

		Response addResponse = add.request().post(Entity.entity(cargo, MediaType.APPLICATION_XML));
		System.out.println("HTTP Response Code:" + addResponse.getStatus());
		addResponse.close();

		System.out.println(cargo.getNome());

		addResponse = add.request().post(Entity.entity(cargo, MediaType.APPLICATION_XML));
		System.out.println("HTTP Response Code:" + addResponse.getStatus());
		addResponse.close();

	}
}
