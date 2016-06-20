package br.ufrn.imd.converter;

import br.ufrn.imd.dominio.Unidade;
import javafx.util.StringConverter;

public class UnidadeConverter extends StringConverter<Unidade> {
	// Method to convert a Unidade-Object to a String
	@Override
	public String toString(Unidade unidade) {
		return unidade == null ? null : unidade.getNome();
	}

	// Method to convert a String to a Unidade-Object
	@Override
	public Unidade fromString(String string) {
		Unidade unidade = null;

		if (string == null) {
			return unidade;
		}
		return unidade;
	}
}
