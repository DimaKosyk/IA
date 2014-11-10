package antonio;

public class NaiveBayes {
	
	private final int ATRIBUTO_CLASSE;  

	private final int[][] trainingSet;
	
	public NaiveBayes(int[][] trainingSet) {
		this.trainingSet = trainingSet;
		ATRIBUTO_CLASSE = trainingSet[0].length - 1;
	}
	
	private double probabilidadeDaClasse(int classe) {
		int numeroDeInstancias = 0, totalDeInstancias = trainingSet.length;
		for (int i = 0; i < trainingSet.length; i++) {
			int[] instancia = trainingSet[i];
			if (instancia[ATRIBUTO_CLASSE] == classe) {
				numeroDeInstancias++;
			}
		}
		return numeroDeInstancias / (double) totalDeInstancias;
	}
	
	private double probabilidadeDaClasseDadoValorDoAtributo(int classe, int atributo, int valorDoAtributo) {
		int numeroDeInstancias = 0, numeroDeInstanciasDaClasse = 0;
		for (int i = 0; i < trainingSet.length; i++) {
			int[] instancia = trainingSet[i];
			if (instancia[instancia.length - 1] == classe) {
				numeroDeInstanciasDaClasse++;
				if (instancia[atributo] == valorDoAtributo) {
					numeroDeInstancias++;
				}
			}
		}
		return numeroDeInstancias / (double) numeroDeInstanciasDaClasse;
	}
	
	private double probabilidadeDoResultado(int classe, int[] atributos) {
		double probabilidade = 1;
		for (int a = 0; a < atributos.length; a++) {
			probabilidade = probabilidade * probabilidadeDaClasseDadoValorDoAtributo(classe, a, atributos[a]);
		}
		probabilidade = probabilidade * probabilidadeDaClasse(classe);
		return probabilidade;
	}
	
	public int classificar(int[] atributos, int[] possiveisClasses) {
		int classe = -1;
		double probabilidade = 0;
		for (int c = 0; c < possiveisClasses.length; c++) {
			double probabilidadeDeSerAClasseAtual = probabilidadeDoResultado(possiveisClasses[c], atributos);
			System.out.println("Probabilidade de ser a classe " + possiveisClasses[c] + " = " + probabilidadeDeSerAClasseAtual);
			if (probabilidadeDeSerAClasseAtual > probabilidade) {
				classe = c;
				probabilidade = probabilidadeDeSerAClasseAtual;
			}
		}
		if (classe == -1) {
			return -1;
		} else {
			return possiveisClasses[classe];
		}
	}
	
}