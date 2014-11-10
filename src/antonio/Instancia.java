package antonio;

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
}