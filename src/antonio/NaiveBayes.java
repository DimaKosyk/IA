package antonio;

import java.util.HashMap;

public class NaiveBayes {

	private final Instancia[] conjuntoDeTreinameto;
	private final Object[] possiveisClasses;
	private final HashMap<Object, Double> probabilidadesDasClasses;
	private final int quantidadeDeAtributos;
	private final HashMap<Object, Integer> quantidadesDeInstanciasDasClasses;

	public NaiveBayes(Instancia[] conjuntoDeTreinameto,
			Object[] possiveisClasses) {
		// Inicializa os atributos privados
		this.conjuntoDeTreinameto = conjuntoDeTreinameto;
		this.possiveisClasses = possiveisClasses;
		probabilidadesDasClasses = new HashMap<Object, Double>(
				possiveisClasses.length);
		quantidadeDeAtributos = conjuntoDeTreinameto[0].getAtributos().length;
		quantidadesDeInstanciasDasClasses = new HashMap<Object, Integer>(
				possiveisClasses.length);
		// Conta quantas instâncias há em cada classe
		for (Object classe : possiveisClasses) {
			quantidadesDeInstanciasDasClasses.put(classe, 0);
		}
		for (Instancia instancia : conjuntoDeTreinameto) {
			Object classeDaInstancia = instancia.getValor();
			int novaQuantidadeDeInstanciasDaClasse = quantidadesDeInstanciasDasClasses
					.get(classeDaInstancia) + 1;
			quantidadesDeInstanciasDasClasses.put(classeDaInstancia,
					novaQuantidadeDeInstanciasDaClasse);
		}
		// Calcula a probabilidade de cada classe
		int quantidadeDeInstancias = conjuntoDeTreinameto.length;
		for (Object classe : possiveisClasses) {
			int quantidadeDeInstanciasDaClasse = quantidadesDeInstanciasDasClasses
					.get(classe);
			double probabilidadeDaClasse = quantidadeDeInstanciasDaClasse
					/ (double) quantidadeDeInstancias;
			probabilidadesDasClasses.put(classe, probabilidadeDaClasse);
		}
	}

	private double probabilidadeDaClasse(Object classe) {
		return probabilidadesDasClasses.get(classe);
	}

	private int quantidadeDeInstanciasDaClasse(Object classe) {
		return quantidadesDeInstanciasDasClasses.get(classe);
	}

	private double probabilidadeDaClasseDadoValorDoAtributo(Object classe,
			int atributo, Object valorDoAtributo) {
		int numeroDeInstanciasComValor = 0;
		int numeroDeInstanciasDaClasse = quantidadeDeInstanciasDaClasse(classe);
		if (numeroDeInstanciasDaClasse == 0) {
			return 0; // Evita divisão por zero
		}
		for (Instancia instancia : conjuntoDeTreinameto) {
			if (instancia.getValor().equals(classe)
					&& instancia.getAtributos()[atributo]
							.equals(valorDoAtributo)) {
				numeroDeInstanciasComValor++;
			}
		}
		return numeroDeInstanciasComValor / (double) numeroDeInstanciasDaClasse;
	}

	private double probabilidade(Instancia instancia, Object classe) {
		Object[] atributos = instancia.getAtributos();
		double probabilidade = 1;
		for (int a = 0; a < quantidadeDeAtributos; a++) {
			double probabilidadeClasseAtributo = probabilidadeDaClasseDadoValorDoAtributo(
					classe, a, atributos[a]);
			if (probabilidadeClasseAtributo == 0) {
				return 0; // Otimização
			}
			probabilidade = probabilidade * probabilidadeClasseAtributo;
		}
		probabilidade = probabilidade * probabilidadeDaClasse(classe);
		return probabilidade;
	}

	public Object classificar(Instancia instancia) {
		Object classeFinal = null;
		double probabilidade = 0;
		for (Object classe : possiveisClasses) {
			double probabilidadeDeSerEssaClasse = probabilidade(instancia,
					classe);
			System.out.println("Probabilidade de ser a classe " + classe
					+ " = " + probabilidadeDeSerEssaClasse);
			if (probabilidadeDeSerEssaClasse > probabilidade) {
				classeFinal = classe;
				probabilidade = probabilidadeDeSerEssaClasse;
			}
		}
		return classeFinal;
	}

}