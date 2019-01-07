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
	
	public String toString() {
		StringBuilder strb = new StringBuilder();
		
		strb.append("{\"type\":\"");
		switch(attributeType) {
		case NOMINAL:
			strb.append("NOMINAL");
			break;
		case NUMERIC:
			strb.append("NUMERIC");
			break;
		default:
			strb.append("UNKOWN");	
			break;
		}
		strb.append("\",");
		strb.append("\"values\":[");
		
		for(int i = 0; i < possibleValues.size(); i++) {
			strb.append("\"");
			strb.append(possibleValues.get(i));
			strb.append("\"");
			if(i < possibleValues.size() - 1) {
				strb.append(",");
			}			
		}	
		strb.append("]");
		strb.append("}");
		
		return strb.toString();
	}
	
	public double parseValue(String value) throws Exception {
		double i = 0;
		for(String possValue : possibleValues) {
			if(possValue.equals(value)) return i;
			i++;
		}
		throw new Exception("Unparsable value: " + value);
	}
	
}
