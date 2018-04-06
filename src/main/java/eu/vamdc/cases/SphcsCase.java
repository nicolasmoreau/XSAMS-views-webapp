package eu.vamdc.cases;

import java.util.Locale;

import org.vamdc.xsams.cases.common.VibrationalQNType;
import org.vamdc.xsams.cases.sphcs.Case;

import eu.vamdc.hitran.HitranData;
import eu.vamdc.util.FormatUtil;

public class SphcsCase implements MolecularCase{

	@Override
	public String getCaseString(CaseParameters parameters, QuantumNumbers qn) throws CaseException {
		StringBuilder result = new StringBuilder();
		String sym = " ";
		Case castedCase = (Case) parameters.getBaseCase();

		/* J */
		if (castedCase.getQNs().getJ() == null)
			result.append(String.format(Locale.ROOT, "%3s", " "));
		else
			result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getJ()));
		result.append(' ');
		/* C */
		/*
		 * WARNING Contrary to what we retrieve in HITRAN database we want C2H4 in the
		 * same output that CH4 but in this case Symmetry has a length of 3. So we need
		 * to check it before.
		 */
		
		if (castedCase.getQNs().getRovibSym() != null) {
			sym = castedCase.getQNs().getRovibSym().getValue();
		}
		if (sym.length() == 3) // C2H4
			result.append(String.format(Locale.ROOT, "%-3s", sym));
		else {
			result.append(String.format(Locale.ROOT, "%-2s ", sym));
		}
		/* alpha */
		result.append(String.format(Locale.ROOT, "%3d", CaseUtil.getRankingValue(castedCase.getQNs().getRS(), "alpha")));
		/* F */
		if (castedCase.getQNs().getF() == null)
			result.append(String.format(Locale.ROOT, "%5s", " "));
		else
			result.append(FormatUtil.getFFormat5(castedCase.getQNs().getF().getValue()));
		
		/* Get some global quanta */
		for (VibrationalQNType vis : castedCase.getQNs().getVis()) {
			Integer mode = vis.getMode();
			qn.getV()[mode - 1] = vis.getValue();
		}
		if (castedCase.getQNs().getVibSym() != null)
			qn.setVibSym(castedCase.getQNs().getVibSym().getValue());
		else 
			qn.setVibSym("  ");
		/* HITRAN Online uses n instead of nv */
		qn.setNv(CaseUtil.getRankingValue(castedCase.getQNs().getRS(), "n")); 

		return result.toString();
	}
}
