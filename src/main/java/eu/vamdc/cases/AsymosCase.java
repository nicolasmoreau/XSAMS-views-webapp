package eu.vamdc.cases;

import java.util.Locale;

import org.vamdc.xsams.cases.common.VibrationalQNType;
import org.vamdc.xsams.cases.asymos.Case;

import eu.vamdc.hitran.HitranData;
import eu.vamdc.util.FormatUtil;


public class AsymosCase implements MolecularCase{

	@Override
	public String getCaseString(CaseParameters parameters, QuantumNumbers qn) throws CaseException {
		StringBuilder result = new StringBuilder();
		boolean needSpecialQ = false;
		Case castedCase = (Case) parameters.getBaseCase();

		/* N */
		/*
		 * In this case J is Double. For matching HITRAN format we take N, the quantum
		 * number associated with the rotational angular momentum
		 */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getN()));
		/* Ka */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getKa()));
		/* Kc */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getKc()));

		/* F */
		if (castedCase.getQNs().getF() == null)
			result.append(String.format(Locale.ROOT, "%5s", " "));
		else
			result.append(FormatUtil.getFFormat5(castedCase.getQNs().getF().getValue()));

		/*
		 * Sym field (not a symmetry) is the J-coding defined as follows: + means J =
		 * N+1/2 and - means J = N-1/2
		 */

		double sym = castedCase.getQNs().getJ() - castedCase.getQNs().getN();
		if (sym == 0.5)
			result.append(String.format(Locale.ROOT, "%1s", "+"));
		else
			result.append(String.format(Locale.ROOT, "%1s", "-"));

		/* Get some global quanta */
		for (VibrationalQNType vis : castedCase.getQNs().getVis()) {
			Integer mode = vis.getMode();
			if (mode > 6) {
				needSpecialQ = true;
				break;
			}
			qn.getV()[mode - 1] = vis.getValue();
		}
		if (needSpecialQ) {
			qn.setGlobalQ(CaseUtil.getSpecialGlobalQString(castedCase.getQNs().getVis()));
		}

		return result.toString();
	}
}
