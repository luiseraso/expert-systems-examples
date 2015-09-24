package edu.fup.ims.examples.karts;

public class Kart {
	
	// Attributes
	// Type: Standard, Pipe Frame, Mach 8, Steel Driver, Cat Cruiser, etc.
	private String type;
	
	// Constructors
	public Kart(){
		this.setType("Standard");
	}
	
	public Kart(String type){
		this.setType(type);
	}

	// Getters and setters...
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
