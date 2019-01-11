package udc.edu.weka.classifiers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import udc.edu.weka.attribute.Attribute;
import udc.edu.weka.attribute.AttributeType;
import udc.edu.weka.attribute.DenseAttribute;
import udc.edu.weka.attribute.DenseInstance;
import weka.core.Instances;

public class J48 extends weka.classifiers.trees.J48 implements Classifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2842003251181268377L;

	private List<Attribute> attributes;
	private int classIndex;

	public int getClassIndex() {
		return classIndex;
	}

	public void setClassIndex(int classIndex) {
		this.classIndex = classIndex;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public void buildClassifier(Instances instances) throws Exception {
		super.buildClassifier(instances);
		
		int numAttributes = instances.numAttributes();
		
		this.classIndex = instances.classIndex();
		
		this.attributes = new ArrayList<Attribute>();
		
		for(int i = 0; i < numAttributes; i++) {
			weka.core.Attribute attribute = instances.attribute(i);
			Attribute attr = new Attribute();
			attr.setAttribute(attribute);
			attr.setName(attribute.name());
			attr.setAttributeType(checkAttributeType(attribute));
			List<String> possValues = new ArrayList<String>();
			attr.setPossibleValues(possValues);
			if(attr.getAttributeType() == AttributeType.NOMINAL) {
				Enumeration<Object> e = attribute.enumerateValues();
				if (e != null) {
					List<Object> values = Collections.list(e);
					for(Object o : values) {
						possValues.add(o.toString());
					}
				}
			}
			this.attributes.add(attr);
		}
	}
	
	private AttributeType checkAttributeType(weka.core.Attribute attribute) {
		if(attribute.isNominal()) 
			return AttributeType.NOMINAL;
		else {
			return AttributeType.NUMERIC;
		}
	}

	@Override
	public DenseAttribute classifyInstance(DenseInstance denseInstance) throws Exception {
		//Cria um atributo denso
		DenseAttribute denseAttribute = new DenseAttribute();
		
		//Pega o atributo classe
		Attribute attr = this.attributes.get(classIndex);
		
		//Cria uma lista de atributos para o framework
		ArrayList<weka.core.Attribute> attrWeka = new ArrayList<weka.core.Attribute>();
		
		//Adiciona os atributos na lista
		this.attributes.forEach((a)-> attrWeka.add(a.getAttribute()));
		
		//Adiciona a lista de atributos em uma lista de instâncias
		Instances aux = new Instances("teste",attrWeka,1);

		//Adiciona a nova instância na lista de instâncias
		aux.add(denseInstance.getDenseInstance());
		
		//Seta o index do classificador
		aux.setClassIndex(this.classIndex);
		
		//Classifica a instância e obtém o resultado
		double resultado = super.classifyInstance(aux.firstInstance());
	
		//Seta os valores dentro do resultado
		denseAttribute.setType(attr.getAttributeType());
		denseAttribute.setNumericalValue(resultado);		
		if(attr.getAttributeType().equals(AttributeType.NOMINAL)) {
			denseAttribute.setNominalValue(attr.getPossibleValues().get(new Double(resultado).intValue()));
		}
		
		return denseAttribute;
	}
	
	@Override
	public double[] distributionForInstance(DenseInstance denseInstance) throws Exception {
		//Cria um atributo denso
		DenseAttribute denseAttribute = new DenseAttribute();
		
		//Pega o atributo classe
		Attribute attr = this.attributes.get(classIndex);
		
		//Cria uma lista de atributos para o framework
		ArrayList<weka.core.Attribute> attrWeka = new ArrayList<weka.core.Attribute>();
		
		//Adiciona os atributos na lista
		this.attributes.forEach((a)-> attrWeka.add(new weka.core.Attribute(a.getName(),a.getAttributeType() == AttributeType.NOMINAL)));
		
		//Adiciona a lista de atributos em uma lista de instâncias
		Instances aux = new Instances("teste",attrWeka,1);

		//Adiciona a nova instância na lista de instâncias
		aux.add(denseInstance.getDenseInstance());
		
		//Seta o index do classificador
		aux.setClassIndex(this.classIndex);
		
		//Classifica a instância e obtém o resultado
		double resultado[] = this.distributionForInstance(aux.firstInstance());
	
		//Seta os valores dentro do resultado
		denseAttribute.setType(attr.getAttributeType());		
		
		return resultado;
	}

}
