package br.ufrn.imd.services;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.ufrn.imd.dominio.Usuario;

public class UsuarioService extends GenericService {
	ResteasyClient client = new ResteasyClientBuilder().build();

	public UsuarioService() {

	}

	public String usuarioListar() {
		ResteasyWebTarget target = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/usuarios");
		Response response = target.request().get();

		String value = response.readEntity(String.class);
		System.out.println(value);

		response.close();
		return value;
	}

	public int usuarioCriar(Usuario usuario) {
		ResteasyWebTarget add = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/usuarios");

		Response addResponse = add.request().post(Entity.entity(usuario, "application/json"));
		System.out.println("HTTP Response Code:" + addResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(addResponse.readEntity(String.class));

		return addResponse.getStatus();
	}

	public String usuarioBuscar(String nome) {
		ResteasyWebTarget target = client.target(getUrl() + getDomain() + getComplement() + getVersion()
				+ "consulta/usuariosFilter?nomeUsuario=" + nome);
		Response response = target.request().get();

		System.out.println("HTTP Response Code:" + response.getStatus());

		String value = response.readEntity(String.class);
		System.out.println(value);

		response.close();
		return value;
	}

	public int usuarioDeletar(Usuario usuario) {
		ResteasyWebTarget delete = client.target(getUrl() + getDomain() + getComplement() + getVersion()
				+ "consulta/usuarios/" + usuario.getIdUsuario());

		Response deleteResponse = delete.request().delete();
		System.out.println("HTTP Response Code:" + deleteResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(deleteResponse.readEntity(String.class));

		return deleteResponse.getStatus();
	}

	public int usuarioEditar(Usuario usuario) {
		ResteasyWebTarget update = client
				.target(getUrl() + getDomain() + getComplement() + getVersion() + "consulta/usuarios/");

		Response updateResponse = update.request().put(Entity.entity(usuario, "application/json"));
		System.out.println("HTTP Response Code:" + updateResponse.getStatus());

		System.out.println("Server response : \n");
		System.out.println(updateResponse.readEntity(String.class));

		return updateResponse.getStatus();

	}
}
