package weka;
// http://www.ibm.com/developerworks/library/os-weka1/

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class RegressaoLinear {

	public static void main(String args[]) throws Exception {

		String arquivoDaBaseDeTreinamento = "bases/houses.arff";

		Instances baseDeTreinamento = DataSource.read(arquivoDaBaseDeTreinamento);
		baseDeTreinamento.setClassIndex(baseDeTreinamento.numAttributes() - 1);
		// Descomente para remover atributo houseSize
		//baseDeTreinamento.deleteAttributeAt(0);

		LinearRegression modeloLinear = new LinearRegression();
		modeloLinear.buildClassifier(baseDeTreinamento);

		System.out.println(modeloLinear.toString());

		Evaluation avaliacao = new Evaluation(baseDeTreinamento);
		avaliacao.evaluateModel(modeloLinear, baseDeTreinamento);

		System.out.println("\nAvaliação do modelo:\n");
		System.out.println("Correlation coefficient: " + avaliacao.correlationCoefficient());
		System.out.println("Mean absolute error: " + avaliacao.meanAbsoluteError());
		System.out.println("Root mean squared error: " + avaliacao.rootMeanSquaredError());
		System.out.println("Relative absolute error: " + avaliacao.relativeAbsoluteError());
		System.out.println("Root relative squared error: " + avaliacao.rootRelativeSquaredError());
		System.out.println("Total number of instances: " + avaliacao.numInstances());

		String arquivoDaBaseDeTeste = "bases/new_houses.arff";
		Instances baseDeTeste = DataSource.read(arquivoDaBaseDeTeste);
		// Descomente para remover atributo houseSize
		//baseDeTeste.deleteAttributeAt(0);
		double precoEstimadoParaMinhaCasa = modeloLinear.classifyInstance(baseDeTeste.firstInstance());
		
		System.out.println("\nPreço estimado para minha casa: " + precoEstimadoParaMinhaCasa);
	}
}