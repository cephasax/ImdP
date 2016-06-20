package br.ufrn.imd.converter;

import br.ufrn.imd.dominio.JustificativaFalta;
import javafx.util.StringConverter;

public class JustificativaFaltaConverter extends StringConverter<JustificativaFalta> {
	// Method to convert a JustificativaFalta-Object to a String
	@Override
	public String toString(JustificativaFalta justificativaFalta) {
		return justificativaFalta == null ? null : justificativaFalta.getObservacaoAnalise();
	}

	// Method to convert a String to a JustificativaFalta-Object
	@Override
	public JustificativaFalta fromString(String string) {
		JustificativaFalta justificativaFalta = null;

		if (string == null) {
			return justificativaFalta;
		}
		return justificativaFalta;
	}
}
