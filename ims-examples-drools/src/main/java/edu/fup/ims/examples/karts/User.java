package edu.fup.ims.examples.karts;

public class User {
	
	// Attributes
	String name;
	int age;
	
	// Constructors
	public User(String name, int age){
		this.name = name;
		this.age = age;
	}

	// Getters and setters...
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
