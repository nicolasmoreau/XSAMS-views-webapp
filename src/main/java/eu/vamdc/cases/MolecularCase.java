package eu.vamdc.cases;


public interface MolecularCase {
	public String getCaseString(CaseParameters parameters, QuantumNumbers qn) throws CaseException;
}
