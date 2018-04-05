package eu.vamdc.cases;

import java.util.Locale;

import org.vamdc.xsams.cases.nltos.Case;

import eu.vamdc.hitran.HitranData;
import eu.vamdc.util.FormatUtil;

public class NltosCase implements MolecularCase{

	@Override
	public String getCaseString(CaseParameters parameters) throws CaseException {
		StringBuilder result = new StringBuilder();
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

		double Sym = castedCase.getQNs().getJ() - castedCase.getQNs().getN();
		if (Sym == 0.5)
			result.append(String.format(Locale.ROOT, "%1s", "+"));
		else
			result.append(String.format(Locale.ROOT, "%1s", "-"));

		/* Get some global quanta */
		HitranData.getv()[0] = castedCase.getQNs().getV1();
		HitranData.getv()[1] = castedCase.getQNs().getV2();
		HitranData.getv()[2] = castedCase.getQNs().getV3();

		return result.toString();
	}
}
