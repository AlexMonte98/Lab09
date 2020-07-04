package it.polito.tdp.borders.model;

public class Country {
	//SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb
	private int ccode;
	private String stateAbb;
	private String stateName;
	
	public Country(int ccode, String stateAbb, String stateName) {
		super();
		this.ccode = ccode;
		this.stateAbb = stateAbb;
		this.stateName = stateName;
	}
	
	@Override
	public String toString() {
		return "Country ccode= " + ccode + ", stateAbb= " + stateAbb + ", stateName= "
	+ stateName + "]";
	}

	public int getCcode() {
		return ccode;
	}
	public void setCcode(int ccode) {
		this.ccode = ccode;
	}
	public String getStateAbb() {
		return stateAbb;
	}
	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	

}
