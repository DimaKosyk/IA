package antonio;

import java.util.Arrays;

public class Instancia {
	private Object[] atributos;
	private Object valor;

	public Instancia(Object[] atributos) {
		this.atributos = atributos;
	}

	public Instancia(Object[] atributos, Object valor) {
		this.atributos = atributos;
		this.valor = valor;
	}
	
	public Object[] getAtributos() {
		return atributos;
	}
	
	public Object getValor() {
		return valor;
	}
	
	@Override
	public boolean equals(Object objeto) {
		if (!(objeto instanceof Instancia)) {
			return false;
		}
		Instancia outraInstancia = (Instancia) objeto;
		for (int a = 0; a < atributos.length; a++) {
			if (!atributos[a].equals(outraInstancia.atributos[a])) {
				return false;
			}
		}
		// TODO Comparar valor?
		return true;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public String toString() {
		return "(" + Arrays.toString(atributos) + ", " + (valor != null ? valor.toString() : "null") + ")";
	}
}