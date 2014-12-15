package antonio;

import java.util.*;

public class KMeans {
	private Instancia[] centroides;
	private final Instancia[] conjuntoDeTreinamento;
	private HashMap<Instancia, Set<Instancia>> grupos;
	private final int k;
	private final int p = 2; // TODO Generalizar
	private final int quantidadeDeAtributos;

	public KMeans(Instancia[] conjuntoDeTreinamento, int k) {
		this(conjuntoDeTreinamento, k, escolherCentroidesIniciais(conjuntoDeTreinamento, k));
	}
	
	public KMeans(Instancia[] conjuntoDeTreinamento, int k, Instancia[] centroides) {
		this.centroides = centroides;
		this.conjuntoDeTreinamento = conjuntoDeTreinamento;
		this.k = k;
		quantidadeDeAtributos = conjuntoDeTreinamento[0].getAtributos().length;
		criarNovosGrupos();
	}
	
	private void criarNovosGrupos() {
		grupos = new HashMap<Instancia, Set<Instancia>>();
		for (Instancia centroide : centroides) {
			grupos.put(centroide, new HashSet<Instancia>());
		}
		for (Instancia instancia : conjuntoDeTreinamento) {
			Instancia centroideMaisProxima = centroides[0];
			double menorDistancia = distancia(instancia, centroideMaisProxima);
			for (int c = 1; c < k; c++) {
				Instancia centroide = centroides[c];
				double distancia = distancia(instancia, centroide);
				if (distancia < menorDistancia) {
					centroideMaisProxima = centroide;
					menorDistancia = distancia;
				}
			}
			grupos.get(centroideMaisProxima).add(instancia);
		}
	}
	
	private double distancia(Instancia instancia1, Instancia instancia2) {
		return distancia(instancia1, instancia2, p );
	}
	
	private double distancia(Instancia instancia1, Instancia instancia2, int p) {
		double somatorio = 0;
		for (int a = 0; a < quantidadeDeAtributos; a++) {
			somatorio += Math.pow(Math.abs((Double) instancia1.getAtributos()[a] - (Double) instancia2.getAtributos()[a]), p);
		}
		return Math.pow(somatorio, 1 / (double) p);
	}
	
	private static Instancia[] escolherCentroidesIniciais(Instancia[] conjuntoDeTreinamento, int k) {
		// Escolha aleatória
		Set<Instancia> centroidesIniciais = new HashSet<Instancia>();
		do {
			int numeroAleatorio = (int) Math.round(Math.random() * (conjuntoDeTreinamento.length - 1));
			Instancia centroide = conjuntoDeTreinamento[numeroAleatorio];
			if (!centroidesIniciais.contains(centroide)) {
				centroidesIniciais.add(centroide);
			}
		} while (centroidesIniciais.size() < k);
		return (Instancia[]) centroidesIniciais.toArray();
	}
	
	public HashMap<Instancia, Set<Instancia>> getGrupos() {
		return grupos;
	}
	
	private Instancia novaCentroide(Set<Instancia> instancias) {
		Double[] atributos = new Double[quantidadeDeAtributos];
		for (int a = 0; a < quantidadeDeAtributos; a++) {
			atributos[a] = 0d;
			for (Instancia instancia : instancias) {
				atributos[a] += (Double) instancia.getAtributos()[a];
			}
			atributos[a] = atributos[a] / instancias.size();
		}
		return new Instancia(atributos);
	}
	
	public boolean melhorarAgrupamento() {
		Instancia[] novasCentroides = new Instancia[k];
		for (int c = 0; c < k; c++) {
			Instancia centroide = centroides[c];
			Set<Instancia> grupo = grupos.get(centroide);
			novasCentroides[c] = novaCentroide(grupo);
		}
		HashSet<Instancia> centroidesAntigas = new HashSet<Instancia>();
		for (Instancia centroide : centroides) {
			centroidesAntigas.add(centroide);
		}
		
		boolean houveMudancaNasCentroides = false;
		for (int c = 0; c < k; c++) {
			if (!centroidesAntigas.contains(novasCentroides[c])) {
				houveMudancaNasCentroides = true;
				break;
			}
		}
		if (houveMudancaNasCentroides) {
			centroides = novasCentroides;
			criarNovosGrupos();
		}
		return houveMudancaNasCentroides;
	}
	
}