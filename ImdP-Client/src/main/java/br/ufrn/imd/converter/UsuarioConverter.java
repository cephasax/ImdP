package br.ufrn.imd.converter;

import br.ufrn.imd.dominio.Usuario;
import javafx.util.StringConverter;

public class UsuarioConverter extends StringConverter<Usuario> {
	// Method to convert a Usuario-Object to a String
	@Override
	public String toString(Usuario usuario) {
		return usuario == null ? null : usuario.getNome();
	}

	// Method to convert a String to a Usuario-Object
	@Override
	public Usuario fromString(String string) {
		Usuario usuario = null;

		if (string == null) {
			return usuario;
		}
		return usuario;
	}
}
