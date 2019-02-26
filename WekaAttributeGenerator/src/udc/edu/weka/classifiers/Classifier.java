package udc.edu.weka.classifiers;

import java.util.List;

import udc.edu.weka.attribute.Attribute;
import udc.edu.weka.attribute.DenseAttribute;
import udc.edu.weka.attribute.DenseInstance;
import weka.core.Instances;

public interface Classifier {
	public List<Attribute> getAttributes();
	public DenseAttribute classifyInstance(DenseInstance denseInstance) throws Exception;
	public void buildClassifier(Instances ins) throws Exception;
	public double[] distributionForInstance(DenseInstance denseInstance) throws Exception;
	public int getClassIndex();
}
