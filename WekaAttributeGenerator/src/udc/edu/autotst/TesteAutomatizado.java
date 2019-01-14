package udc.edu.autotst;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class TesteAutomatizado {
	
	public static void main(String[] args) throws Exception {
		TesteAutomatizado.testeAutomatizadoLenses(2, 10);
	}
	
	public static void testeAutomatizadoLenses(int folds, int seed) throws Exception {
		String pathArff = "C:\\Users\\mathlich\\Desktop\\datasets\\lenses.data.arff";
		
		ArffLoader loader = new ArffLoader();
		loader.setFile(new File(pathArff));
		
		Instances ins = loader.getDataSet();
		ins.setClassIndex(ins.numAttributes() - 1);
		
		String unique = TesteAutomatizado.generateUniqueString();
		
		PrintWriter pw = new PrintWriter(new File("C:\\Users\\mathlich\\Desktop\\resultados\\Resultados-" + unique + ".csv"));
		
		pw.print("Classifier;");
		pw.print("Binary Splits;");
		pw.print("Pruning Confidence;");
		pw.print("Laplace Smoothing;");
		pw.print("Folds;");
		pw.print("Correct;");
		pw.print("Incorrect;");
		pw.print("Pct Correct;");
		pw.print("Pct Incorrect;");
		pw.print("Kappa;");
		pw.print("Mean absolute error;");
		pw.print("Root mean squared error;");
		pw.print("Relative absolute error;");
		pw.print("Root relative squared error;");
		pw.print("Total number of Instances;");
		pw.print("Area under ROC;");
		pw.print("Area under PRC;");
		pw.print("Area under weighted ROC;");
		pw.print("Area under weighted PRC");
		pw.print("\n");
		
		J48 classifier = new J48();
		
		Double prune = new Double(0.1);
		
		Evaluation eval = null;
			
		while(prune <= 0.5) {
			
			String options[] = {"-B","-C",prune.toString()};
			
			classifier.setOptions(options);
			
			eval = new Evaluation(ins);
			
			eval.crossValidateModel(classifier, ins, folds, new Random(10));
			
			pw.print(classifier.getClass().getSimpleName() + ";");
			pw.print(TesteAutomatizado.hasOption(classifier.getOptions(), "-B") ? "Sim;" : "Não;");
			pw.print(prune.toString().replace(".", ",") + ";");
			pw.print(TesteAutomatizado.hasOption(classifier.getOptions(), "-A") ? "Sim;" : "Não;");
			pw.print(folds + ";");
			pw.print((eval.correct() + ";").replace(".", ","));
			pw.print((eval.incorrect() + ";").replace(".", ","));
			pw.print((eval.pctCorrect() + ";").replace(".", ","));
			pw.print((eval.pctIncorrect() + ";").replace(".", ","));
			pw.print((eval.kappa() + ";").replace(".", ","));
			pw.print((eval.meanAbsoluteError() + ";").replace(".", ","));
			pw.print((eval.rootMeanSquaredError() + ";").replace(".", ","));
			pw.print((eval.relativeAbsoluteError() + ";").replace(".", ","));
			pw.print((eval.rootRelativeSquaredError() + ";").replace(".", ","));
			pw.print((ins.numInstances() + ";").replace(".", ","));
			
			String ROC = "";
			String PRC = "";
			String WROC = "";
			String WPRC = "";
			
			try {ROC = new Double(eval.areaUnderROC(ins.numAttributes()-1)).toString();}
			catch(Exception e) {}
			try {PRC = new Double(eval.areaUnderPRC(ins.numAttributes()-1)).toString();}
			catch(Exception e) {}
			try {WROC = new Double(eval.weightedAreaUnderROC()).toString();}
			catch(Exception e) {}
			try {WPRC = new Double(eval.weightedAreaUnderPRC()).toString();}
			catch(Exception e) {}
			
			
			pw.print(ROC.replace(".", ",") + ";");
			pw.print(PRC.replace(".", ",") + ";");
			pw.print(WROC.replace(".", ",") + ";");
			pw.print(WPRC.replace(".", ",") + "");
			
			pw.print("\n");
			
			prune += 0.1;
			
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
		unique += cal.get(Calendar.SECOND) + ".";
		unique += cal.get(Calendar.MILLISECOND);

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
