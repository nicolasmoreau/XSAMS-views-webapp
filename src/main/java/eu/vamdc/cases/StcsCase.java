package eu.vamdc.cases;

import java.util.Locale;

import org.vamdc.xsams.cases.stcs.Case;
import org.vamdc.xsams.cases.common.SymmetrySpeciesType;
import org.vamdc.xsams.cases.common.VibrationalAMQNType;
import org.vamdc.xsams.cases.common.VibrationalQNType;
import eu.vamdc.util.FormatUtil;

public class StcsCase implements MolecularCase{

	@Override
	public String getCaseString(CaseParameters parameters, QuantumNumbers qn) throws CaseException {
		StringBuilder result = new StringBuilder();
		Case castedCase = (Case) parameters.getBaseCase();

		if (parameters.getM() == 11) { // NH3
			/* J */
			if (castedCase.getQNs().getJ() == null)
				result.append(String.format(Locale.ROOT, "%2s", " "));
			else
				result.append(String.format(Locale.ROOT, "%2d", castedCase.getQNs().getJ()));
			/* K */
			if (castedCase.getQNs().getK() == null)
				result.append(String.format(Locale.ROOT, "%3s", " "));
			else
				result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getK()));
			/* vibInv */
			if (castedCase.getQNs().getVibInv() == null)
				result.append(String.format(Locale.ROOT, "%2s", " "));
			else
				result.append(String.format(Locale.ROOT, "%2s", castedCase.getQNs().getVibInv()));
			result.append(String.format(Locale.ROOT, "%1s", " "));
			/* RotSym */
			SymmetrySpeciesType C = castedCase.getQNs().getRotSym();
			result.append(String.format(Locale.ROOT, "%-3s", C == null ? " " : C.getValue()));
			/* RoVibSym */
			SymmetrySpeciesType sym = castedCase.getQNs().getRovibSym();
			result.append(String.format(Locale.ROOT, "%-3s", sym == null ? " " : sym.getValue()));
			result.append(String.format(Locale.ROOT, "%1s", " "));
		}

		else {
			/* J */
			if (castedCase.getQNs().getJ() == null)
				result.append(String.format(Locale.ROOT, "%3s", " "));
			else
				result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getJ()));
			/* K */
			if (castedCase.getQNs().getK() == null)
				result.append(String.format(Locale.ROOT, "%3s", " "));
			else
				result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getK()));
			/* l */
			if (castedCase.getQNs().getLS().isEmpty()) 
				result.append(String.format(Locale.ROOT, "%2s", " "));
			else 
				result.append(String.format(Locale.ROOT, "%2s", castedCase.getQNs().getLS().get(0)));
			/* C */
			SymmetrySpeciesType C = castedCase.getQNs().getRovibSym();
			result.append(String.format(Locale.ROOT, "%-2s", C == null ? " " : C.getValue()));
			/* Sym */
			String sym = null; // castedCase.getQNs().get
			result.append(String.format(Locale.ROOT, "%1s", sym == null ? " " : sym)); // FIXME

			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%4s", " "));
			else
				result.append(FormatUtil.getFFormat4(castedCase.getQNs().getF().getValue()));
		}

		/* Get some global quanta */
		int count = 0;
		for (VibrationalQNType vis : castedCase.getQNs().getVis()) {
			Integer mode = vis.getMode();
			qn.getV()[mode - 1] = vis.getValue();
			count++;
		}

		if (count > 0)
			qn.setGlobalQ(CaseUtil.getSpecialGlobalQString(castedCase.getQNs().getVis()));
		else
			qn.setGlobalQ(String.format(Locale.ROOT, "%13s", " "));
		
		qn.setParity(castedCase.getQNs().getParity());

		/* NH3 */
		for (VibrationalAMQNType lis : castedCase.getQNs().getLis()) {
			Integer mode = lis.getMode();
			qn.getL()[mode - 1] = lis.getValue();
		}
		
		if (castedCase.getQNs().getVibSym() != null)
			qn.setVibSym(castedCase.getQNs().getVibSym().getValue());
		else 
			qn.setVibSym("   ");
		
		/* stcs */
		
		if (!castedCase.getQNs().getLS().isEmpty()) 
			qn.setL_stcs(castedCase.getQNs().getLS().get(0));
		
		return result.toString();
	}
}
