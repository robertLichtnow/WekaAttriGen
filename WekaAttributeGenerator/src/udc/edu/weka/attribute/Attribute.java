package udc.edu.weka.attribute;

import java.util.List;

public class Attribute {
	
	private AttributeType attributeType;
	private String name;
	private List<String> possibleValues;
	
	public AttributeType getAttributeType() {
		return attributeType;
	}
	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getPossibleValues() {
		return possibleValues;
	}
	public void setPossibleValues(List<String> possibleValues) {
		this.possibleValues = possibleValues;
	}
	
}
