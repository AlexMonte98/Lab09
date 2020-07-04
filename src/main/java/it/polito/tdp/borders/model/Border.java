package it.polito.tdp.borders.model;

public class Border {
	private int id1;
	private String state1;
	private int id2;
	private String state2;
	private int year;
	private Country c1;
	private Country c2;
	
	public Border(int int1, String string, int int2, String string2, int int3,Country c1
			,Country c2) {
		// TODO Auto-generated constructor stub
		this.id1=int1;
		this.state1=string;
		this.id2=int2;
		this.state2=string2;
		this.year=int3;
		this.c1=c1;
		this.c2=c2;
	}
	
	public Country getC1() {
		return c1;
	}

	public void setC1(Country c1) {
		this.c1 = c1;
	}

	public Country getC2() {
		return c2;
	}

	public void setC2(Country c2) {
		this.c2 = c2;
	}

	

	
	

	@Override
	public String toString() {
		return "state1= " + state1 +" state2= " + state2 + ", year=" + year
				+ ", c1=" + c1.getStateName() + ", c2=" + c2.getStateName() + "";
	}

	public int getId1() {
		return id1;
	}

	public void setId1(int id1) {
		this.id1 = id1;
	}

	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	

}
