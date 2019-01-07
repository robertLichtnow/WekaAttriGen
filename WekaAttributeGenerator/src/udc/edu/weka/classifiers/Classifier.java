package udc.edu.weka.classifiers;

import java.util.List;

import udc.edu.weka.attribute.Attribute;
import udc.edu.weka.attribute.DenseAttribute;
import udc.edu.weka.attribute.DenseInstance;

public interface Classifier {
	public List<Attribute> getAttributes();
	public DenseAttribute classifyInstance(DenseInstance denseInstance);
}
