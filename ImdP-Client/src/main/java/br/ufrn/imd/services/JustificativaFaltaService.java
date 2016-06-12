package br.ufrn.imd.services;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class JustificativaFaltaService extends GenericService {
	ResteasyClient client = new ResteasyClientBuilder().build();

	public JustificativaFaltaService() {
		
	}

	public String justificativaFaltaListar() {
		ResteasyWebTarget target = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/justificativasFalta");
		Response response = target.request().get();

		String value = response.readEntity(String.class);
		System.out.println(value);

		response.close();
		return value;
	}
}
