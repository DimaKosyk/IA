package antonio;

import java.util.Arrays;
import java.util.Random;

import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;

public class VizinhosMaisProximosExemplo {
	private static Instancia converterInstanciaDoWeka(Instance instance) {
		int quantidadeDeAtributos = instance.numAttributes() - 1;
		Double[] atributos = new Double[quantidadeDeAtributos];
		int a = 0;
		for (double atributo : instance.toDoubleArray()) {
			atributos[a] = atributo;
			a++;
			if (a == quantidadeDeAtributos) {
				break;
			}
		}
		Double classe = instance.classValue();
		return new Instancia(atributos, classe);
	}

	public static void main(String[] args) throws Exception {
		// Lê a base de dados de treinamento
		String arquivoDaBaseDeTreinamento = "bases/seismic-bumps.arff";
		Instances baseDeTreinamento = DataSource
				.read(arquivoDaBaseDeTreinamento);
		baseDeTreinamento.setClassIndex(baseDeTreinamento.numAttributes() - 1);
		final int NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TREINAMENTO = 100;
		// final int NUMERO_DE_EXEMPLOS_CONSIDERADOS = baseDeTreinamento.numInstances(); // A base inteira
		final Instancia[] CONJUNTO_DE_TREINAMENTO = new Instancia[NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TREINAMENTO];

		// Obtém os atributos e a classe de cada instância
		for (int i = 0; i < NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TREINAMENTO; i++) {
			CONJUNTO_DE_TREINAMENTO[i] = converterInstanciaDoWeka(baseDeTreinamento
					.instance(i));
		}

		// Constrói o modelo
		VizinhosMaisProximos vmp = new VizinhosMaisProximos(
				CONJUNTO_DE_TREINAMENTO, 1);

		// Lê a base de dados de teste
		String arquivoDaBaseDeTeste = "bases/seismic-bumps.arff";
		Instances baseDeTeste = DataSource.read(arquivoDaBaseDeTeste);
		baseDeTeste.setClassIndex(baseDeTeste.numAttributes() - 1);

		// Seleciona uma instância aleatória e testa
		// final int NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TESTE = 100;
		final int NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TESTE = baseDeTreinamento.numInstances(); // A base inteira
		int i = new Random().nextInt(NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TESTE);
		Instancia teste = converterInstanciaDoWeka(baseDeTeste.instance(i));
		System.out
				.println("Características da instância escolhida para teste: "
						+ Arrays.toString(teste.getAtributos()));
		System.out.println("Classe esperada: "
				+ ((Double) teste.getValor() == 0 ? "terremoto" : "explosão"));
		double classe = (Double) vmp.classificar(teste);
		System.out.println("Classe obtida: "
				+ (classe == 0 ? "terremoto" : "explosão"));
	}
}