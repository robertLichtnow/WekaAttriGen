package udc.edu.tst;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

public class CSVUtils {

	public static void CSVtoARFFLens() throws FileNotFoundException, UnsupportedEncodingException {
		String pathLens = "C:\\Users\\mathlich\\Desktop\\datasets\\lenses.data.csv";
		Scanner sc = new Scanner(new File(pathLens));	
		
		sc.useDelimiter("\\n");
		
		
		ArrayList<String[]> linhas = new ArrayList<String[]>();
		
		while(sc.hasNext()) {
			String[] linha = sc.next().split("  | ");
			linhas.add(linha);
		}
		
		sc.close();
		
		/*
		 * 	1. age of the patient: (1) young, (2) pre-presbyopic, (3) presbyopic
		 *  2. spectacle prescription:  (1) myope, (2) hypermetrope
    	 *	3. astigmatic:     (1) no, (2) yes
    	 *  4. tear production rate:  (1) reduced, (2) normal
		 * */
		
		List<String> values = new ArrayList<String>();
		ArrayList<Attribute> atributos = new ArrayList<Attribute>();
		
		values.add("young");
		values.add("pre-presbyopic");
		values.add("presbyopic");
		Attribute age = new Attribute("age",values);
		atributos.add(age);

		
		values.clear();
		values.add("myope");
		values.add("hypermetrope");
		Attribute spectacle = new Attribute("spectacle",values);
		atributos.add(spectacle);

		
		values.clear();
		values.add("no");
		values.add("yes");
		Attribute astigmatic = new Attribute("astigmatic",values);
		atributos.add(astigmatic);

		
		values.clear();
		values.add("reduced");
		values.add("normal");
		Attribute tearRate = new Attribute("tear-rate",values);
		atributos.add(tearRate);

		
		values.clear();
		values.add("hard");
		values.add("soft");
		values.add("none");
		Attribute lenses = new Attribute("lenses",values);
		atributos.add(lenses);


		Instances instances = new Instances("lenses",atributos,linhas.size());
		instances.setClassIndex(instances.numAttributes()-1);
		
		for(String[] instancia : linhas) {
			double line[] = new double[instancia.length-1];
			for(int i = 1; i<instancia.length; i++) {
				line[i-1] = CSVUtils.parseNominalValueFloat(instancia[i]);
			}
			DenseInstance ins = new DenseInstance(1.0, line);
			instances.add(ins);		
		}

		String pathLensesArff = "C:\\Users\\mathlich\\Desktop\\datasets\\lenses.data.arff";
		PrintWriter writer = new PrintWriter(pathLensesArff,"UTF-8");
		writer.print(instances.toString());
		writer.close();
		
		
	}
	
	public static void CSVtoARFFCar() throws IOException {
		String pathCar = "C:\\Users\\mathlich\\Desktop\\datasets\\car\\car.data.csv";
		CSVLoader loader = new CSVLoader();
		loader.setSource(new File(pathCar));
		Instances data = loader.getDataSet();
		
		String pathArffCar = "C:\\Users\\mathlich\\Desktop\\datasets\\car\\car.data.arff";
		ArffSaver saver = new ArffSaver();
		saver.setInstances(data);
		saver.setFile(new File(pathArffCar));
		saver.writeBatch();
		
	}
	
	public static double parseNominalValueFloat(String value) {
		return Double.parseDouble(value) - 1;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		CSVUtils.CSVtoARFFCar();
	}
	
}
