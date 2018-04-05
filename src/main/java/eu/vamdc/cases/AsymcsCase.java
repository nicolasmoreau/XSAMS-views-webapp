package eu.vamdc.cases;

import java.util.Locale;

import org.vamdc.xsams.cases.asymcs.Case;
import org.vamdc.xsams.cases.common.VibrationalQNType;

import eu.vamdc.hitran.HitranData;
import eu.vamdc.util.FormatUtil;

public class AsymcsCase implements MolecularCase{

	@Override
	public String getCaseString(CaseParameters parameters) throws CaseException {
		StringBuilder result = new StringBuilder();
		boolean needSpecialQ = false;
		Case castedCase = (Case) parameters.getBaseCase();

		/* J */
		/* in some files (like SO3) no J values are filled */
		if (castedCase.getQNs().getJ() == null)
			result.append(String.format(Locale.ROOT, "%3s", " "));
		else
			result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getJ()));
		/* Ka */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getKa()));
		/* Kc */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getKc()));

		/* F */
		if (castedCase.getQNs().getF() == null)
			result.append(String.format(Locale.ROOT, "%5s", " "));
		else
			result.append(FormatUtil.getFFormat5(castedCase.getQNs().getF().getValue()));

		/* Sym */
		if (castedCase.getQNs().getParity() == null)
			result.append(' ');
		else
			result.append(String.format(Locale.ROOT, "%1s", castedCase.getQNs().getParity()));

		/* Get some global quanta */
		for (VibrationalQNType vis : castedCase.getQNs().getVis()) {
			Integer mode = vis.getMode();
			/* For larger molecule like CH3OH from CDMS */
			if (mode > 6) {
				needSpecialQ = true;
				break;
			}
			HitranData.getv()[mode - 1] = vis.getValue();
		}
		if (needSpecialQ) {
			HitranData.setGlobalQ(CaseUtil.getSpecialGlobalQString(castedCase.getQNs().getVis()));
		}

		return result.toString();
	}
}
