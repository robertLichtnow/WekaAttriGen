import java.io.File;

import udc.edu.weka.classifiers.J48;
import udc.edu.weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class Main {
	public static void main(String[] args) throws Exception {
		ArffLoader loader = new ArffLoader();
		loader.setSource(new File("datasets/lenses.data.arff"));
		
		Instances ins = loader.getDataSet();
		ins.setClassIndex(ins.numAttributes()-1);
		
		Classifier cls = new J48();
		cls.buildClassifier(ins);
		
		weka.core.SerializationHelper.write("models/lenses-j48.model",cls);
	}
}
