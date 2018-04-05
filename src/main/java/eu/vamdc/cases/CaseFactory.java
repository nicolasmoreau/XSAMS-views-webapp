package eu.vamdc.cases;

public class CaseFactory {

	public static MolecularCase buildCase(String caseName){
		MolecularCase result;

		switch (caseName) {
		/* Group 1: Assymetric rotors */
		case "nltcs":
			result = new NltcsCase();
			break;
		case "asymcs":
			result = new AsymcsCase();
			break;
		case "asymos":
			result = new AsymosCase();
			break;
		case "nltos":
			result = new NltosCase();
			break;
		/* Group 2: Diatomic and linear molecules */
		case "ltcs":
			result = new LtcsCase();
			break;
		case "lpcs":
			result = new LpcsCase();
			break;
		case "lpos":
			result = new LposCase();
			break;
		case "dcs":
			result = new DcsCase();
			break;
		/* Group 3: Spherical rotors */
		case "sphcs":
			result = new SphcsCase();
			break;
		/* Group 4: Symmetric rotor */
		case "stcs":
			result = new StcsCase();
			break;
		/* Group 5: Triplet-Sig ground electronic states */
		case "hundb":
			result = new HundbCase();
			break;
		/* Group 6: Douplet-Pi ground electronic states */
		case "hunda":
			result = new HundaCase();
			break;
		default:
			throw new IllegalArgumentException("Case not handled: " + caseName);
		}		
		return result;
	}
}
