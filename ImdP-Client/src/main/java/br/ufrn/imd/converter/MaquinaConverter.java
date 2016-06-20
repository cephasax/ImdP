package br.ufrn.imd.converter;

import br.ufrn.imd.dominio.Maquina;
import javafx.util.StringConverter;

public class MaquinaConverter extends StringConverter<Maquina> {
	// Method to convert a Maquina-Object to a String
	@Override
	public String toString(Maquina maquina) {
		return maquina == null ? null : maquina.getDenominacao();
	}

	// Method to convert a String to a Maquina-Object
	@Override
	public Maquina fromString(String string) {
		Maquina maquina = null;

		if (string == null) {
			return maquina;
		}
		return maquina;
	}
}
