package weka;
// http://www.ibm.com/developerworks/library/os-weka1/

import weka.classifiers.Evaluation;
import weka.classifiers.meta.ClassificationViaRegression;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ClassificacaoLinear {

	public static void main(String args[]) throws Exception {

		// String arquivoDaBaseDeTreinamento = "bases/diabetes.arff";
		String arquivoDaBaseDeTreinamento = "bases/iris.arff";

		Instances baseDeTreinamento = DataSource.read(arquivoDaBaseDeTreinamento);
		baseDeTreinamento.setClassIndex(baseDeTreinamento.numAttributes() - 1);

		ClassificationViaRegression classificacaoLinear = new ClassificationViaRegression();
		classificacaoLinear.buildClassifier(baseDeTreinamento);

		System.out.print(classificacaoLinear.toString());

		Evaluation avaliacao = new Evaluation(baseDeTreinamento);
		avaliacao.evaluateModel(classificacaoLinear, baseDeTreinamento);

		System.out.println("Avaliação do modelo:\n");
		System.out.println("Correctly classified instances: " + avaliacao.correct());
		System.out.println("Incorrectly classified instances: " + avaliacao.incorrect());
		System.out.println("Kappa statistic: " + avaliacao.kappa());
		System.out.println("Mean absolute error: " + avaliacao.meanAbsoluteError());
		System.out.println("Root mean squared error: " + avaliacao.rootMeanSquaredError());
		System.out.println("Relative absolute error: " + avaliacao.relativeAbsoluteError());
		System.out.println("Root relative squared error: " + avaliacao.rootRelativeSquaredError());
		System.out.println("Total number of instances: " + avaliacao.numInstances());

		// String arquivoDaBaseDeTeste = "bases/new_diabetes.arff";
		String arquivoDaBaseDeTeste = "bases/iris_novas.arff";
		Instances baseDeTeste = DataSource.read(arquivoDaBaseDeTeste);
		baseDeTeste.setClassIndex(baseDeTeste.numAttributes() - 1);
		int classe = (int) classificacaoLinear.classifyInstance(baseDeTeste.instance(2));
		
		System.out.print("\nTeste: ");
		// System.out.println("possui diabetes? " + (classe == 1 ? "sim" : "não"));
		switch (classe) {
		case 0:
			System.out.println("Iris-setosa");
			break;
		case 1:
			System.out.println("Iris-versicolor");
			break;
		default:
			System.out.println("Iris-virginica");
			break;
		}
	}
}