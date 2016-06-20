package br.ufrn.imd.converter;

import br.ufrn.imd.dominio.TipoJustificativa;
import javafx.util.StringConverter;

public class TipoJustificativaConverter extends StringConverter<TipoJustificativa> {
	// Method to convert a TipoJustificativa-Object to a String
	@Override
	public String toString(TipoJustificativa tipoJustificativa) {
		return tipoJustificativa == null ? null : tipoJustificativa.getNome();
	}

	// Method to convert a String to a TipoJustificativa-Object
	@Override
	public TipoJustificativa fromString(String string) {
		TipoJustificativa tipoJustificativa = null;

		if (string == null) {
			return tipoJustificativa;
		}
		return tipoJustificativa;
	}
}
