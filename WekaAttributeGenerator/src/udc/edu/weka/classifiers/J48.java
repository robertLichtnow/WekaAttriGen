package udc.edu.weka.classifiers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import udc.edu.weka.attribute.Attribute;
import udc.edu.weka.attribute.AttributeType;
import weka.core.Instances;

public class J48 extends weka.classifiers.trees.J48 {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2842003251181268377L;

	private List<Attribute> attributes;

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
		
		for(int i = 0; i < numAttributes; i++) {
			weka.core.Attribute attribute = instances.attribute(i);
			Attribute attr = new Attribute();
			attr.setName(attribute.name());
			attr.setAttributeType(checkAttributeType(attribute));
			List<String> possValues = new ArrayList<String>();
			attr.setPossibleValues(possValues);
			if(attr.getAttributeType() == AttributeType.ORDINAL || attr.getAttributeType() == AttributeType.NOMINAL) {
				Enumeration<Object> e = attribute.enumerateValues();
				if (e != null) {
					List<Object> values = Collections.list(e);
					for(Object o : values) {
						possValues.add(o.toString());
					}
				}
			}
		}
	}
	
	private AttributeType checkAttributeType(weka.core.Attribute attribute) {
		if(attribute.ordering() == weka.core.Attribute.ORDERING_ORDERED && attribute.isNominal()) 
			return AttributeType.ORDINAL;
		else if(attribute.isNominal()) 
			return AttributeType.NOMINAL;
		else {
			return AttributeType.NUMERIC;
		}
		
		
	}

}
