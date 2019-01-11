package udc.edu.tst;

import java.io.File;

import udc.edu.weka.attribute.DenseAttribute;
import udc.edu.weka.attribute.DenseInstance;
import udc.edu.weka.classifiers.Classifier;
import udc.edu.weka.classifiers.J48;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class Teste {

	public static void main(String[] args) throws Exception {
		
		String path = "C:\\Users\\mathlich\\Desktop\\datasets\\iris.data.csv";
		
		CSVLoader csvl = new CSVLoader();
		csvl.setFile(new File(path));
		
		Instances ins = csvl.getDataSet();
		
		ins.setClassIndex(ins.numAttributes()-1);
		
		Classifier classifier = new J48();
		
		classifier.buildClassifier(ins);
		
		System.out.println(classifier.getAttributes().get(4).toString());
		
		DenseAttribute attributes[] = new DenseAttribute[4];
		
		
		DenseAttribute attr = new DenseAttribute();
		attr.setType(classifier.getAttributes().get(0).getAttributeType());
		attr.setNumericalValue(5.9379);
		attributes[0] = attr;

		
		attr = new DenseAttribute();
		attr.setType(classifier.getAttributes().get(1).getAttributeType());
		attr.setNumericalValue(2.7687);
		attributes[1] = attr;

		
		attr = new DenseAttribute();
		attr.setType(classifier.getAttributes().get(2).getAttributeType());
		attr.setNumericalValue(4.2452);
		attributes[2] = attr;

		
		attr = new DenseAttribute();
		attr.setType(classifier.getAttributes().get(3).getAttributeType());
		attr.setNumericalValue(1.3097);
		attributes[3] = attr;

		
		
		DenseAttribute resultado = classifier.classifyInstance(new DenseInstance(1,attributes,classifier));
		
		System.out.println(resultado.getNominalValue() + "|" + resultado.getNumericalValue());
		
		System.out.println("-");
		
		double dist[] = classifier.distributionForInstance(new DenseInstance(1,attributes,classifier));
		for(double d : dist) {
			System.out.println(d);
		}
	}
	
}
