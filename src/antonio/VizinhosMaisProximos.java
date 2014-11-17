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
		
		public Instancia getInstancia() {
			return instancia;
		}
		
		@Override
		public int compareTo(Vizinho outroVizinho) {
			return Double.compare(this.distancia, outroVizinho.distancia);
		}
	}
	
	private static class ClasseProvavel implements Comparable<ClasseProvavel> {
		private final Object classe;
		private int frequencia;

		public ClasseProvavel(Object classe, int frequencia) {
			this.classe = classe;
			this.frequencia = frequencia;
		}
		
		public Object getClasse() {
			return classe;
		}
		
		@Override
		public int compareTo(ClasseProvavel outraClasseProvavel) {
			return Integer.compare(this.frequencia, outraClasseProvavel.frequencia);
		}
	}
	
	// private static final int P_PADRAO = 1; // Manhattan distance
	private static final int P_PADRAO = 2; // Euclidean distance
	
	private final Instancia[] conjuntoDeTreinamento;
	private final int p;
	private final int quantidadeDeAtributos;

	public VizinhosMaisProximos(Instancia[] conjuntoDeTreinamento) {
		this(conjuntoDeTreinamento, P_PADRAO);
	}
	
	public VizinhosMaisProximos(Instancia[] conjuntoDeTreinamento, int p) {
		this.conjuntoDeTreinamento = conjuntoDeTreinamento;
		this.p = p;
		quantidadeDeAtributos = conjuntoDeTreinamento[0].getAtributos().length;
	}
	
	private double distancia(Instancia instancia1, Instancia instancia2) {
		return distancia(instancia1, instancia2, p);
	}
	
	private double distancia(Instancia instancia1, Instancia instancia2, int p) {
		double somatorio = 0;
		for (int a = 0; a < quantidadeDeAtributos; a++) {
			somatorio += Math.pow(Math.abs((Double) instancia1.getAtributos()[a] - (Double) instancia2.getAtributos()[a]), p);
		}
		return Math.pow(somatorio, 1 / (double) p);
	}
	
	public Object classificar(Instancia instancia, int k) {
		List<Vizinho> listaDeVizinhos = new ArrayList<Vizinho>();
		for (Instancia outraInstancia : conjuntoDeTreinamento) {
			listaDeVizinhos.add(new Vizinho(outraInstancia, distancia(instancia, outraInstancia)));
		}
		Collections.sort(listaDeVizinhos);
		if (k == 1) {
			return listaDeVizinhos.get(0).getInstancia().getValor();
		} else {
			HashMap<Object, Integer> frequenciasDasClasses = new HashMap<Object, Integer>();
			// Contabiliza as classes dos vizinhos mais próximos
			for (int v = 0; v < k; v++) {
				Object classe = listaDeVizinhos.get(v).getInstancia().getValor();
				int frequenciaDaClasse;
				if (frequenciasDasClasses.containsKey(classe)) { 
					frequenciaDaClasse = frequenciasDasClasses.get(classe);
				} else {
					frequenciaDaClasse = 0;
				}
				frequenciaDaClasse++;
				frequenciasDasClasses.put(classe, frequenciaDaClasse);
			}
			List<ClasseProvavel> listaDeClassesProvaveis = new ArrayList<ClasseProvavel>(frequenciasDasClasses.size());
			for (Object classe : frequenciasDasClasses.keySet()) {
				int frequenciaDaClasse = frequenciasDasClasses.get(classe);
				listaDeClassesProvaveis.add(new ClasseProvavel(classe, frequenciaDaClasse));
			}
			// Retorna a classe que aparece na maior quantidade de vizinhos mais próximos
			Collections.sort(listaDeClassesProvaveis);
			return listaDeClassesProvaveis.get(0).getClasse();
		}
	}

}