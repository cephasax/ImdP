package br.ufrn.imd.services;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.ufrn.imd.dominio.ImpressaoDigital;
import br.ufrn.imd.dominio.Usuario;

public class ImpressaoDigitalService extends GenericService {
	ResteasyClient client = new ResteasyClientBuilder().build();

	public ImpressaoDigitalService() {

	}

	public String impressaoDigitalListar(Usuario usuario) {
		ResteasyWebTarget target = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/digitais/usuarios/" + usuario.getIdUsuario());
		Response response = target.request().get();

		String value = response.readEntity(String.class);
		System.out.println(value);

		response.close();
		return value;
	}

	public int impressaoDigitalCriar(ImpressaoDigital impressaoDigital) {
		ResteasyWebTarget add = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/digitais");

		Response addResponse = add.request().post(Entity.entity(impressaoDigital, "application/json"));
		System.out.println("HTTP Response Code:" + addResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(addResponse.readEntity(String.class));

		return addResponse.getStatus();
	}

	public int impressaoDigitalDeletar(ImpressaoDigital impressaoDigital) {
		ResteasyWebTarget delete = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/digitals/" + impressaoDigital.getIdImpressaoDigital());

		Response deleteResponse = delete.request().delete();
		System.out.println("HTTP Response Code:" + deleteResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(deleteResponse.readEntity(String.class));

		return deleteResponse.getStatus();
	}
	
	public int impressaoDigitalEditar(ImpressaoDigital impressaoDigital){
		ResteasyWebTarget update = client.target(
				getUrl() + getDomain() + getComplement() + getVersion() + "consulta/digitais");

		Response updateResponse = update.request().put(Entity.entity(impressaoDigital, "application/json"));
		System.out.println("HTTP Response Code:" + updateResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(updateResponse.readEntity(String.class));

		return updateResponse.getStatus();
		
	}
}
