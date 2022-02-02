package com.example.model;

public class Funds {
	
	private double funds;
	private String typename;
	
	public Funds() {
		// TODO Auto-generated constructor stub
	}

	public Funds(double funds, String typename) {
		super();
		this.funds = funds;
		this.typename = typename;
	}

	public Funds(double funds) {
		super();
		this.funds = funds;
	}

	public double getFunds() {
		return funds;
	}

	public void setFunds(double funds) {
		this.funds = funds;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Override
	public String toString() {
		return "Funds [funds=" + funds + ", typename=" + typename + "] \n";
	}
	

}
