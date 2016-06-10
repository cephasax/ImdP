package br.ufrn.imd.excecoes;

import java.io.Serializable;

public class DadoNaoEncontradoException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5456355585378484029L;
	
	public DadoNaoEncontradoException(String msg){
		super(msg);
	}
	
}
