package udc.edu.tst;

import java.io.File;
import java.util.ArrayList;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class TesteSemModulo {

	public static void main(String[] args) throws Exception {
		String path = "C:\\Users\\mathlich\\Desktop\\datasets\\iris.data.csv";
		
		CSVLoader csvl = new CSVLoader();
		csvl.setFile(new File(path));
		
		Instances ins = csvl.getDataSet();
		
		ins.setClassIndex(ins.numAttributes()-1);
		
		Classifier classifier = new J48();
		
		classifier.buildClassifier(ins);
		
		double attr[] = new double[5];
		
		
		attr[0] = 5.9379;
		attr[1] = 2.7687;
		attr[2] = 4.2452;
		attr[3] = 1.3097;

		
		
		DenseInstance di = new DenseInstance(1.0,attr);
		
		ArrayList<weka.core.Attribute> attrWeka = new ArrayList<weka.core.Attribute>();
		
		for(int i = 0; i<5; i++) {
			attrWeka.add(ins.attribute(i));
		}
			
		
		Instances aux = new Instances("teste",attrWeka,1);
		aux.setClassIndex(4);
		aux.add(di);
		
		System.out.println(classifier.classifyInstance(aux.firstInstance()));
		
		System.out.println("-");
		
		double results[] = classifier.distributionForInstance(aux.firstInstance());
		
		for(double d : results) {
			System.out.println(d);
		}
		
		
		
		
		
		
		
		
		
	}
}
