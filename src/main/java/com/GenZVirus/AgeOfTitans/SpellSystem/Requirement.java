package com.GenZVirus.AgeOfTitans.SpellSystem;

public class Requirement {

	private String name;
	private String description;

	public Requirement(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public boolean meetsRequirement() {
		return false;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

}
