package udc.edu.autotst;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class TesteAutomatizado {
	
	public static void main(String[] args) throws Exception {
		TesteAutomatizado.testeAutomatizadoLenses(10, 20);
	}
	
	//Testes foram feitos utilizando um split de 70/30
	//Os dados são armazenados em um arquivo csv
	
	public static void testeAutomatizadoLenses(int seed, double initialPercent) throws Exception {
		String pathArff = "C:\\Users\\Robert\\Desktop\\datasets\\lenses.data.arff";
		
		ArffLoader loader = new ArffLoader();
		loader.setFile(new File(pathArff));
		
		Instances ins = loader.getDataSet();
		ins.setClassIndex(ins.numAttributes() - 1);
		ins.randomize(new Random(seed));
		
		
		
		
		String unique = TesteAutomatizado.generateUniqueString();
		
		Logistic classifier = new Logistic();
		
		PrintWriter pw = new PrintWriter(new File("C:\\Users\\Robert\\Desktop\\resultados\\Resultados-" + classifier.getClass().getSimpleName() + "-" + unique + ".csv"));
		
		pw.print("Classifier;");
		pw.print("Split;");
		pw.print("Correct;");
		pw.print("Pct Correct;");
		pw.print("Stddev;");
		pw.print("Variance;");
		pw.print("Total number of Instances");
		pw.print("\n");
		
		
		
		
		Evaluation eval = null;
			
		while(initialPercent <= 90) {
			
			int trainSize = (int) Math.round(ins.numInstances() * initialPercent / 100);
			int testSize = ins.numInstances() - trainSize;
			
			Instances train = new Instances(ins, 0, trainSize);
			Instances test = new Instances(ins,trainSize, testSize); 
			
			eval = new Evaluation(train);
			
			classifier.buildClassifier(train);
			
			eval.evaluateModel(classifier, test);
			
			pw.print(classifier.getClass().getSimpleName() + ";");
			pw.print(Double.toString(initialPercent).replace(".", ",") + ";");
			pw.print((eval.correct() + ";").replace(".", ","));
			pw.print((eval.pctCorrect() + ";").replace(".", ","));
			pw.print((eval.rootMeanSquaredError() + ";").replace(".", ","));
			pw.print(((eval.rootMeanSquaredError() * eval.rootMeanSquaredError()) + ";").replace(".", ","));
			pw.print((ins.numInstances() + ";").replace(".", ","));
			
			
			
			pw.print("\n");
			
			initialPercent += 10;
			
		}
		
		
		
		
		pw.close();		
		
	}
	
	public static String generateUniqueString() {
		String unique = "";
		
		Calendar cal = Calendar.getInstance();
		
		unique += cal.get(Calendar.YEAR) + "-";
		unique += (cal.get(Calendar.MONTH) + 1) + "-";
		unique += cal.get(Calendar.DATE) + "T";
		unique += cal.get(Calendar.HOUR_OF_DAY) + "-";
		unique += cal.get(Calendar.MINUTE) + "-";
		unique += cal.get(Calendar.SECOND);

		return unique;
	}
	
	public static boolean hasOption(String options[], String option) {
		for(String o : options) {
			if(o.equals(option)) {
				return true;
			}
		}
		return false;
	}
	
}
