package br.ufrn.imd.services;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class Generic {

	public void Get() throws Exception {
		ClientRequest request = new ClientRequest(
				"http://localhost:8080/RESTfulExample/json/product/get");
		request.accept("application/json");
		ClientResponse<String> response = request.get(String.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			new ByteArrayInputStream(response.getEntity().getBytes())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}
	  }
}
