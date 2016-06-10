package br.ufrn.imd.excecoes;

import java.io.Serializable;

public class DadoIncompletoException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7622016654528213747L;

	public DadoIncompletoException(String msg){
		super(msg);
	}
	
}
