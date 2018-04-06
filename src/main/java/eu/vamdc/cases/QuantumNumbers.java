package eu.vamdc.cases;

public class QuantumNumbers {
	private Double Jup = -1.0;


	private Double Jlow = -1.0;
	private Integer Nup = -1;
	private Integer Nlow = -1;

	// Some global quanta
	private String elecStateLabel;
	private Double omega;
	private Integer l2;
	private Integer l_class7;
	private String parity;
	private Integer[] v = new Integer[7];
	
	/* for NH3 */
	private Integer[] l = new Integer[7];
	private String VibSym; /* also used for Hitran-Online CH4 */
	
	private Integer Nv; /* for Hitran-Online CH4 */
	
	private Integer l_stcs;
	
	private Integer rank;
	private String vibInv;
	// Global Q when class 10
	private String globalQ = "";
	public Double getJup() {
		return Jup;
	}
	public void setJup(Double jup) {
		Jup = jup;
	}
	public Double getJlow() {
		return Jlow;
	}
	public void setJlow(Double jlow) {
		Jlow = jlow;
	}
	public Integer getNup() {
		return Nup;
	}
	public void setNup(Integer nup) {
		Nup = nup;
	}
	public Integer getNlow() {
		return Nlow;
	}
	public void setNlow(Integer nlow) {
		Nlow = nlow;
	}
	public String getElecStateLabel() {
		return elecStateLabel;
	}
	public void setElecStateLabel(String elecStateLabel) {
		this.elecStateLabel = elecStateLabel;
	}
	public Double getOmega() {
		return omega;
	}
	public void setOmega(Double omega) {
		this.omega = omega;
	}
	public Integer getL2() {
		return l2;
	}
	public void setL2(Integer l2) {
		this.l2 = l2;
	}
	public Integer getL_class7() {
		return l_class7;
	}
	public void setL_class7(Integer l_class7) {
		this.l_class7 = l_class7;
	}
	public String getParity() {
		return parity;
	}
	public void setParity(String parity) {
		this.parity = parity;
	}
	public Integer[] getV() {
		return v;
	}
	public void setV(Integer[] v) {
		this.v = v;
	}
	public Integer[] getL() {
		return l;
	}
	public void setL(Integer[] l) {
		this.l = l;
	}
	public String getVibSym() {
		return VibSym;
	}
	public void setVibSym(String vibSym) {
		VibSym = vibSym;
	}
	public Integer getNv() {
		return Nv;
	}
	public void setNv(Integer nv) {
		Nv = nv;
	}
	public Integer getL_stcs() {
		return l_stcs;
	}
	public void setL_stcs(Integer l_stcs) {
		this.l_stcs = l_stcs;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getVibInv() {
		return vibInv;
	}
	public void setVibInv(String vibInv) {
		this.vibInv = vibInv;
	}
	public String getGlobalQ() {
		return globalQ;
	}
	public void setGlobalQ(String globalQ) {
		this.globalQ = globalQ;
	}
	
	
	
	
	
	
}
