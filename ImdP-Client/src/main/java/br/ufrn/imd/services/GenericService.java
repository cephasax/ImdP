package br.ufrn.imd.services;

public class GenericService {

	private String url = "http://localhost:8080/";
	private String domain = "ImdP-Server/";
	private String complement = "api/";
	private String version = "v1/";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return url + domain + complement + version;
	}

}
