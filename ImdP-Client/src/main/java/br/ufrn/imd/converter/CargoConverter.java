package br.ufrn.imd.converter;

import br.ufrn.imd.dominio.Cargo;
import javafx.util.StringConverter;

public class CargoConverter extends StringConverter<Cargo> {
	// Method to convert a Cargo-Object to a String
	@Override
	public String toString(Cargo cargo) {
		return cargo == null ? null : cargo.getNome();
	}

	// Method to convert a String to a Cargo-Object
	@Override
	public Cargo fromString(String string) {
		Cargo cargo = null;

		if (string == null) {
			return cargo;
		}
		return cargo;
	}
}
