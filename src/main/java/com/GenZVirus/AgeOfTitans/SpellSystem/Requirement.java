package com.GenZVirus.AgeOfTitans.SpellSystem;

public class Requirement {

	private String name;

	public Requirement(String name) {
		this.name = name;
	}

	public boolean meetsRequirement() {
		return false;
	}

	public String getDescription() {
		return null;
	}

	public String getName() {
		return name;
	}

}
