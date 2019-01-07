package udc.edu.weka.attribute;

import java.util.List;

import udc.edu.weka.classifiers.Classifier;



public class DenseInstance implements java.io.Serializable {

	
	private weka.core.DenseInstance denseInstance;
	/**
	 * 
	 */
	private static final long serialVersionUID = 5227708805224168766L;

	public DenseInstance(double weight, DenseAttribute[] attributes, Classifier cls) throws Exception {
		double attributeParsed[] = new double[attributes.length];	
		List<Attribute> attributeList = cls.getAttributes();	
		int i = 0;
		for(DenseAttribute attr : attributes) {
			if(attr.getType().equals(AttributeType.NUMERIC)) {
				attributeParsed[i] = attr.getNumericalValue();
			}
			else {
				try {
					attributeParsed[i] = attributeList.get(i).parseValue(attr.getNominalValue());
				} catch (Exception e) {
					throw e;
				}
			}		
			i++;
		}
		
		this.denseInstance = new weka.core.DenseInstance(weight, attributeParsed);
	}

	public weka.core.DenseInstance getDenseInstance() {
		return denseInstance;
	}
		
}
