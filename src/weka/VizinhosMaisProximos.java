package weka;
// http://www.ibm.com/developerworks/library/os-weka3/

import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class VizinhosMaisProximos {

	public static void main(String args[]) throws Exception {

		String arquivoDaBaseDeTreinamento = "bases/bmw-training.arff";

		Instances baseDeTreinamento = DataSource.read(arquivoDaBaseDeTreinamento);
		baseDeTreinamento.setClassIndex(baseDeTreinamento.numAttributes() - 1);

		IBk vizinhosMaisProximos = new IBk();
		vizinhosMaisProximos.buildClassifier(baseDeTreinamento);

		System.out.println(vizinhosMaisProximos.toString());

		Evaluation avaliacao = new Evaluation(baseDeTreinamento);
		avaliacao.evaluateModel(vizinhosMaisProximos, baseDeTreinamento);

		System.out.println("Avaliação do modelo:\n");
		System.out.println("Correctly classified instances: " + avaliacao.correct());
		System.out.println("Incorrectly classified instances: " + avaliacao.incorrect());
		System.out.println("Kappa statistic: " + avaliacao.kappa());
		System.out.println("Mean absolute error: " + avaliacao.meanAbsoluteError());
		System.out.println("Root mean squared error: " + avaliacao.rootMeanSquaredError());
		System.out.println("Relative absolute error: " + avaliacao.relativeAbsoluteError());
		System.out.println("Root relative squared error: " + avaliacao.rootRelativeSquaredError());
		System.out.println("Total number of instances: " + avaliacao.numInstances());

		String arquivoDaBaseDeTeste = "bases/bmw-testing.arff";
		Instances baseDeTeste = DataSource.read(arquivoDaBaseDeTeste);
		baseDeTeste.setClassIndex(baseDeTeste.numAttributes() - 1);
		
		int compraria = (int) vizinhosMaisProximos.classifyInstance(baseDeTeste.firstInstance());
		System.out.println("\nCompraria a garantia estendida? " + (compraria == 1 ? "sim" : "não"));
	}
}