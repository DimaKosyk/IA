package antonio;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;

public class VizinhosMaisProximosQuestao {
	// Converte uma instância lida da base de dados do Weka para uma instância
	// no formato próprio
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
		// Parâmetros
		final int P = 1; // Manhattan distance
		// final int P = 2; // Euclidean distance
		final int PRIMEIRA_INSTANCIA_CONSIDERADA_NO_TREINAMENTO = 0;
		final int NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TREINAMENTO = 1000;
		// final int NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TREINAMENTO =
		// baseDeTreinamento.numInstances(); // A base inteira
		final int PRIMEIRA_INSTANCIA_CONSIDERADA_NO_TESTE = 1000;
		final int NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TESTE = 1000;
		// final int NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TESTE =
		// baseDeTreinamento.numInstances(); // A base inteira
		final int K_MAXIMO = 11;

		// Lê a base de dados de treinamento
		String arquivoDaBaseDeTreinamento = "bases/seismic-bumps.arff";
		Instances baseDeTreinamento = DataSource
				.read(arquivoDaBaseDeTreinamento);
		baseDeTreinamento.setClassIndex(baseDeTreinamento.numAttributes() - 1);

		// Obtém os atributos e a classe de cada instância do conjunto de
		// treinamento
		final Instancia[] CONJUNTO_DE_TREINAMENTO = new Instancia[NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TREINAMENTO];
		for (int i = 0; i < NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TREINAMENTO; i++) {
			CONJUNTO_DE_TREINAMENTO[i] = converterInstanciaDoWeka(baseDeTreinamento
					.instance(i + PRIMEIRA_INSTANCIA_CONSIDERADA_NO_TREINAMENTO));
		}

		// Constroi o modelo
		VizinhosMaisProximos vmp = new VizinhosMaisProximos(CONJUNTO_DE_TREINAMENTO, P);

		// Lê a base de dados de teste
		// String arquivoDaBaseDeTeste = "bases/seismic-bumps.arff";
		// Instances baseDeTeste = DataSource.read(arquivoDaBaseDeTeste);
		// baseDeTeste.setClassIndex(baseDeTeste.numAttributes() - 1);
		Instances baseDeTeste = baseDeTreinamento;

		// Obtém os atributos e a classe de cada instância do conjunto de teste
		final Instancia[] CONJUNTO_DE_TESTE = new Instancia[NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TESTE];
		for (int i = 0; i < NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TESTE; i++) {
			CONJUNTO_DE_TESTE[i] = converterInstanciaDoWeka(baseDeTeste
					.instance(i + PRIMEIRA_INSTANCIA_CONSIDERADA_NO_TESTE));
		}

		// Armazena o valor de k para o qual é obtida a maior acurácia, para
		// mostrar no final
		int kDeMaiorAcuracia = 0;
		double acuraciaDoKDeMaiorAcuracia = 0;

		// Dados necessários para a plotagem do gráfico k versus acurácia
		int numeroDeIteracoes = 0;
		double[] eixoX = new double[K_MAXIMO];
		double[] eixoY = new double[K_MAXIMO];

		// Testa cada um dos possíveis k's ímpares
		for (int k = 1; k <= K_MAXIMO; k = k + 2) {
			// Número de instâncias classificadas corretamente
			int acertos = 0;
			// Testa o modelo com cada instância
			for (int i = 0; i < NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TESTE; i++) {
				Instancia teste = CONJUNTO_DE_TESTE[i];
				double classeEsperada = (Double) teste.getValor();
				double classePrevista = (Double) vmp.classificar(teste, k);
				if (classeEsperada == classePrevista) {
					acertos++;
				}
			}
			// Calcula a acurácia
			double acuracia = acertos
					/ (double) NUMERO_DE_INSTANCIAS_CONSIDERADAS_NO_TESTE;
			System.out.println("k = " + k + ", acurácia = " + acuracia);
			// Verifica se esse é o valor de k para o qual a acurácia é a maior
			// já encontrada
			if (acuracia > acuraciaDoKDeMaiorAcuracia) {
				kDeMaiorAcuracia = k;
				acuraciaDoKDeMaiorAcuracia = acuracia;
			}
			// Armazena dados para a plotagem do gráfico k versus acurácia
			eixoX[numeroDeIteracoes] = k;
			eixoY[numeroDeIteracoes] = acuracia;
			numeroDeIteracoes++;
		}

		// Informa a maior acurácia obtida e o valor de k para o qual foi obtida
		// essa acurácia
		System.out.println("A maior acurácia foi obtida para k = "
				+ kDeMaiorAcuracia + " (" + acuraciaDoKDeMaiorAcuracia + ")");

		// Plota o gráfico k versus acurácia
		double[] eixoX2 = new double[numeroDeIteracoes];
		double[] eixoY2 = new double[numeroDeIteracoes];
		System.arraycopy(eixoX, 0, eixoX2, 0, numeroDeIteracoes);
		System.arraycopy(eixoY, 0, eixoY2, 0, numeroDeIteracoes);
		Plot2DPanel kVersusAcuracia = new Plot2DPanel();
		kVersusAcuracia.addLinePlot("k versus acurácia", eixoX2, eixoY2);
		JFrame janela = new JFrame("k versus acurácia");
		janela.setContentPane(kVersusAcuracia);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(500, 500);
		janela.setVisible(true);
	}
}