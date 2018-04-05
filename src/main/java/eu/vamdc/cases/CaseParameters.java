package eu.vamdc.cases;

import org.vamdc.xsams.cases.common.BaseCase;

public class CaseParameters {
	private BaseCase baseCase;
	private String level;
	private Integer m;
	
	public CaseParameters(BaseCase baseCase){
		this.baseCase = baseCase;
	}
	
	public CaseParameters(BaseCase baseCase, String level){
		this.baseCase = baseCase;
		this.level = level;
	}
	
	public CaseParameters(BaseCase baseCase, String level, Integer m){
		this.baseCase = baseCase;
		this.level = level;
		this.m = m;
	}
	
	public CaseParameters(BaseCase baseCase, Integer m){
		this.baseCase = baseCase;
		this.m = m;
	}

	public BaseCase getBaseCase() {
		return baseCase;
	}

	public void setBaseCase(BaseCase baseCase) {
		this.baseCase = baseCase;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getM() {
		return m;
	}

	public void setM(Integer m) {
		this.m = m;
	}
	
	
	
}
