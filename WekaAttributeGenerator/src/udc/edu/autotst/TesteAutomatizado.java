package udc.edu.autotst;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.LMT;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class TesteAutomatizado {
	
	public static void main(String[] args) throws Exception {
		TesteAutomatizado.testeAutomatizadoLensesPrune(10, 0.05, 0.05);
	}

	
	public static void testeAutomatizadoLensesSplitTaxa(int seed, double initialPercent) throws Exception {
		String pathArff = "C:\\Users\\mathlich\\Desktop\\datasets\\lenses.data.arff";
		
		ArffLoader loader = new ArffLoader();
		loader.setFile(new File(pathArff));
		
		Instances ins = loader.getDataSet();
		ins.setClassIndex(ins.numAttributes() - 1);
		ins.randomize(new Random(seed));
		
		
		
		
		String unique = TesteAutomatizado.generateUniqueString();
		
		
		
		PrintWriter pw = new PrintWriter(new File("C:\\Users\\mathlich\\Desktop\\resultados\\Resultados-" + unique + ".csv"));
		
		pw.print("Classifier;");
		pw.print("Split;");
		pw.print("Correct;");
		pw.print("Pct Correct;");
		pw.print("Stddev;");
		pw.print("Variance;");
		pw.print("Total number of Instances");
		pw.print("\n");
		
		Classifier[] classifiers = new Classifier[4];	
		
		classifiers[0] = new J48();
		classifiers[1] = new Logistic();
		classifiers[2] = new LMT();
		classifiers[3] = new NaiveBayes();
		
		
		Evaluation eval = null;
			
		for(int i=0; i<classifiers.length; i++) {
			double percent = initialPercent;
			while(percent <= 90) {
				
				int trainSize = (int) Math.round(ins.numInstances() * percent / 100);
				int testSize = ins.numInstances() - trainSize;
				
				Instances train = new Instances(ins, 0, trainSize);
				Instances test = new Instances(ins,trainSize, testSize); 
				
				eval = new Evaluation(train);
				
				classifiers[i].buildClassifier(train);
				
				eval.evaluateModel(classifiers[i], test);
				
				pw.print(classifiers[i].getClass().getSimpleName() + ";");
				pw.print(Double.toString(percent).replace(".", ",") + ";");
				pw.print((eval.correct() + ";").replace(".", ","));
				pw.print((eval.pctCorrect() + ";").replace(".", ","));
				pw.print((eval.rootMeanSquaredError() + ";").replace(".", ","));
				pw.print(((eval.rootMeanSquaredError() * eval.rootMeanSquaredError()) + ";").replace(".", ","));
				pw.print((ins.numInstances() + ";").replace(".", ","));
				
				
				
				pw.print("\n");
				
				percent += 10;
				
			}
		}
		
		pw.close();		
		
	}
	
	

	public static void testeAutomatizadoLensesPrune(int seed, double initialPrune, double growth) throws Exception {
		String pathArff = "C:\\Users\\mathlich\\Desktop\\datasets\\lenses.data.arff";
		
		ArffLoader loader = new ArffLoader();
		loader.setFile(new File(pathArff));
		
		Instances ins = loader.getDataSet();
		ins.setClassIndex(ins.numAttributes() - 1);
		ins.randomize(new Random(seed));
		
		int trainSize = (int) Math.round(ins.numInstances() * 80 / 100);
		int testSize = ins.numInstances() - trainSize;
		
		Instances train = new Instances(ins, 0, trainSize);
		Instances test = new Instances(ins,trainSize, testSize); 
		
		
		String unique = TesteAutomatizado.generateUniqueString();
		
		
		
		PrintWriter pw = new PrintWriter(new File("C:\\Users\\mathlich\\Desktop\\resultados\\Resultados-" + unique + ".csv"));
		
		pw.print("Classifier;");
		pw.print("Prune;");
		pw.print("Correct;");
		pw.print("Pct Correct;");
		pw.print("Stddev;");
		pw.print("Variance;");
		pw.print("Total number of Instances");
		pw.print("\n");
		
		J48 classifier = new J48();
		
		
		Evaluation eval = null;
			
		for(int i=0; i<2; i++) {
			double prune = initialPrune;
			while(prune <= 0.5) {
				
				String options[] = {"-C",Double.toString(prune)};
				
				
				
				eval = new Evaluation(train);
				
				
				
			
				
				classifier.setOptions(options);
				classifier.buildClassifier(train);
				
				
				
				eval.evaluateModel(classifier, test);
				
				pw.print(classifier.getClass().getSimpleName() + ";");
				pw.print(Double.toString(prune).replace(".", ",") + ";");
				pw.print((eval.correct() + ";").replace(".", ","));
				pw.print((eval.pctCorrect() + ";").replace(".", ","));
				pw.print((eval.rootMeanSquaredError() + ";").replace(".", ","));
				pw.print(((eval.rootMeanSquaredError() * eval.rootMeanSquaredError()) + ";").replace(".", ","));
				pw.print((ins.numInstances() + ";").replace(".", ","));
				
				
				
				pw.print("\n");
				
				prune += growth;
				
			}
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
