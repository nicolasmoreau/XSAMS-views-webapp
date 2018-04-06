package eu.vamdc.cases;

import java.util.Locale;

import eu.vamdc.hitran.HitranData;
import eu.vamdc.util.FormatUtil;

import org.vamdc.xsams.cases.nltcs.Case;

public class NltcsCase implements MolecularCase{
	
	private Case castedCase = new Case();

	@Override
	public String getCaseString(CaseParameters parameters, QuantumNumbers qn) throws CaseException {
		StringBuilder result = new StringBuilder();
		castedCase = (Case) parameters.getBaseCase();

		/* J */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getJ()));
		/* Ka */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getKa()));
		/* Kc */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getKc()));
		/* F */
		if (castedCase.getQNs().getF() == null) {
			result.append(String.format(Locale.ROOT, "%5s", " "));
		} else {
			result.append(FormatUtil.getFFormat5(castedCase.getQNs().getF().getValue()));
		}
		/* Sym */
		if (castedCase.getQNs().getParity() == null) {
			result.append(' ');
		} else {
			result.append(String.format(Locale.ROOT, "%1s", castedCase.getQNs().getParity()));
		}

		/* Get some global quanta */
		qn.getV()[0] = castedCase.getQNs().getV1();
		qn.getV()[1] = castedCase.getQNs().getV2();
		qn.getV()[2] = castedCase.getQNs().getV3();

		return result.toString();
	}
	
	public Case getCastedCase(){
		return this.castedCase;
	}

}
