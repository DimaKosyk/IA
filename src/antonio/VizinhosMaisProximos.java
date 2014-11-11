package antonio;

import java.util.*;

public class VizinhosMaisProximos {
	private static class Vizinho implements Comparable<Vizinho> {
		private final double distancia;
		private final Instancia instancia;

		public Vizinho(Instancia instancia, double distancia) {
			this.instancia = instancia;
			this.distancia = distancia;
		}
		
		/* public double getDistancia() {
			return distancia;
		} */
		
		public Instancia getInstancia() {
			return instancia;
		}
		
		@Override
		public int compareTo(Vizinho outroVizinho) {
			return Double.compare(this.distancia, outroVizinho.distancia);
		}
	}
	
	// private static final int P = 1; // Manhattan distance
	private static final int P = 2; // Euclidean distance
	
	private final Instancia[] conjuntoDeTreinamento;
	private final int k;
	private final int quantidadeDeAtributos;

	public VizinhosMaisProximos(Instancia[] conjuntoDeTreinamento, int k) {
		this.conjuntoDeTreinamento = conjuntoDeTreinamento;
		this.k = k;
		quantidadeDeAtributos = conjuntoDeTreinamento[0].getAtributos().length;
	}
	
	private double distancia(Instancia instancia1, Instancia instancia2) {
		return distancia(instancia1, instancia2, P);
	}
	
	private double distancia(Instancia instancia1, Instancia instancia2, int p) {
		double somatorio = 0;
		for (int a = 0; a < quantidadeDeAtributos; a++) {
			somatorio += Math.pow(Math.abs((Double) instancia1.getAtributos()[a] - (Double) instancia2.getAtributos()[a]), p);
		}
		return Math.pow(somatorio, 1 / (double) p);
	}
	
	public Object classificar(Instancia instancia) {
		List<Vizinho> listaDeVizinhos = new ArrayList<Vizinho>();
		for (Instancia outraInstancia : conjuntoDeTreinamento) {
			listaDeVizinhos.add(new Vizinho(outraInstancia, distancia(instancia, outraInstancia)));
		}
		Collections.sort(listaDeVizinhos);
		if (k == 1) {
			return listaDeVizinhos.get(0).getInstancia().getValor();
		} else {
			return null;
		}
	}

}