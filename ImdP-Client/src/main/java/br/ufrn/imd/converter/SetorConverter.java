package br.ufrn.imd.converter;

import br.ufrn.imd.dominio.Setor;
import javafx.util.StringConverter;

public class SetorConverter extends StringConverter<Setor> {
	// Method to convert a Setor-Object to a String
	@Override
	public String toString(Setor setor) {
		return setor == null ? null : setor.getNome();
	}

	// Method to convert a String to a Setor-Object
	@Override
	public Setor fromString(String string) {
		Setor setor = null;

		if (string == null) {
			return setor;
		}
		return setor;
	}
}
