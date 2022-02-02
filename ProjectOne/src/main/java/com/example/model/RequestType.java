package com.example.model;

public class RequestType {
	
	private int typeid;
	private String typename;
	
	public RequestType() {
		// TODO Auto-generated constructor stub
	}

	public RequestType(int typeid, String typename) {
		super();
		this.typeid = typeid;
		this.typename = typename;
	}

	public RequestType(String typename) {
		super();
		this.typename = typename;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public int getTypeid() {
		return typeid;
	}

	@Override
	public String toString() {
		return "RequestType [typeid=" + typeid + ", typename=" + typename + "] \n";
	}
	
}
