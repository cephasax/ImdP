package br.ufrn.imd.main;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.ufrn.imd.dominio.Cargo;

public class main {

	public static void main(String[] args) {
		String url = "http://localhost:8080/";
		String domain = "ImdP-Server/";
		String complement = "api/";
		String version = "v1/";
		ResteasyClient client = new ResteasyClientBuilder().build();
		Cargo cargo = new Cargo("nome");
		
//		
//		ResteasyWebTarget add = client
//				.target(url + domain + complement + version + "consulta/cargos");
//
//		Response addResponse = add.request().post(Entity.entity(cargo, "application/json; charset=UTF-8"));
//		System.out.println("HTTP Response Code:" + addResponse.getStatus());
//
//		if (addResponse.getStatus() != 200) {
//			throw new RuntimeException("Failed : HTTP error code : " + addResponse.getStatus());
//		}
//
//		System.out.println("Server response : \n");
//		System.out.println(addResponse.readEntity(String.class));
//
//		addResponse.close();
//		System.out.println(cargo.getNome());

		
		
	}

}
