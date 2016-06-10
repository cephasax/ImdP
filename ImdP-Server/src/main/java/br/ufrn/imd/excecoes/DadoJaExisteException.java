package br.ufrn.imd.excecoes;

import java.io.Serializable;

public class DadoJaExisteException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5456355585378484029L;
	
	public DadoJaExisteException(String msg){
		super(msg);
	}
	
}