package br.ufrn.imd.converter;

import br.ufrn.imd.dominio.Vinculo;
import javafx.util.StringConverter;

public class VinculoConverter extends StringConverter<Vinculo> {
	// Method to convert a Vinculo-Object to a String
	@Override
	public String toString(Vinculo vinculo) {
		return vinculo == null ? null : vinculo.getDescricao();
	}

	// Method to convert a String to a Vinculo-Object
	@Override
	public Vinculo fromString(String string) {
		Vinculo vinculo = null;

		if (string == null) {
			return vinculo;
		}
		return vinculo;
	}
}
