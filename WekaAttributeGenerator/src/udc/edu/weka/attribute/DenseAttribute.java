package udc.edu.weka.attribute;

import java.io.Serializable;

public class DenseAttribute implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8868299786798684749L;
	
	
	private String nominalValue;
	private double numericalValue;
	private AttributeType type;
	
	public String getNominalValue() {
		return nominalValue;
	}
	public void setNominalValue(String nominalValue) {
		this.nominalValue = nominalValue;
	}
	public double getNumericalValue() {
		return numericalValue;
	}
	public void setNumericalValue(double numericalValue) {
		this.numericalValue = numericalValue;
	}
	public AttributeType getType() {
		return type;
	}
	public void setType(AttributeType type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public DenseAttribute(String nominalValue, double numericalValue, AttributeType type) {
		super();
		this.nominalValue = nominalValue;
		this.numericalValue = numericalValue;
		this.type = type;
	}
	
	public DenseAttribute() {
		super();
	}
	
	
}
