import java.io.File;

import udc.edu.weka.classifiers.Classifier;
import udc.edu.weka.classifiers.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class MainAbalone {
	public static void main(String[] args) throws Exception{
		ArffLoader loader = new ArffLoader();
		loader.setSource(new File("datasets/abalone.data.arff"));
		
		Instances ins = loader.getDataSet();
		ins.setClassIndex(ins.numAttributes()-1);
		
		Classifier cls = new J48();
		cls.buildClassifier(ins);
		
		weka.core.SerializationHelper.write("models/abalone-j48.model",cls);
	}
}
