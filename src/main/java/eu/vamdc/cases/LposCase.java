package eu.vamdc.cases;

import java.util.Locale;

import org.vamdc.xsams.cases.lpos.Case;
import org.vamdc.xsams.cases.common.VibrationalQNType;

import eu.vamdc.hitran.HitranData;
import eu.vamdc.util.FormatUtil;

public class LposCase implements MolecularCase{

	@Override
	public String getCaseString(CaseParameters parameters) throws CaseException {
		StringBuilder result = new StringBuilder();
		boolean needSpecialQ = false;
		Case castedCase = (Case) parameters.getBaseCase();
		String Br = " ";
		String J = "   ";

		if (parameters.getLevel().equals(CaseUtil.LOWER_LEVEL)) {
			HitranData.setJlow(Double.valueOf(castedCase.getQNs().getJ()));
			if (HitranData.getJlow() != null) {
				J = String.format(Locale.ROOT, "%3d", HitranData.getJlow().intValue());
				/* If Jup has already been assigned then get the branch name */
				if (HitranData.getJup() > -1.0) {
					try {
						Br = CaseUtil.getBranchName(HitranData.getJup(), HitranData.getJlow());
					} catch (IllegalArgumentException e) {
						System.out.println("Branch not allowed for one transition: Jup=" + HitranData.getJup() + ", Jlow=" + HitranData.getJlow());
					}
					HitranData.setJlow(-1.0);
					HitranData.setJup(-1.0);
				}
			}
			result.append(String.format(Locale.ROOT, "%5s", " "));
			/* Br */
			result.append(String.format(Locale.ROOT, "%1s", Br));
			/* J */
			result.append(J);
			/* Sym */
			String Sym = castedCase.getQNs().getKronigParity();
			result.append(String.format(Locale.ROOT, "%1s", (Sym == null) ? " " : Sym));
			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%5s", " "));
			else
				result.append(FormatUtil.getFFormat5(castedCase.getQNs().getF().getValue()));

			/* Get some global quanta */
			for (VibrationalQNType vis : castedCase.getQNs().getVis()) {
				Integer mode = vis.getMode();
				HitranData.getv()[mode - 1] = vis.getValue();
			}
			HitranData.setL_class7(castedCase.getQNs().getL());
			HitranData.setParity(castedCase.getQNs().getParity());
			HitranData.setVibInv(castedCase.getQNs().getVibInv());

		} else {
			result.append(String.format(Locale.ROOT, "%10s", " "));
			/* No F displayed in HITRAN example */
			result.append(String.format(Locale.ROOT, "%5s", " "));

			HitranData.setJup(Double.valueOf(castedCase.getQNs().getJ()));

			/* Get some global quanta */
			for (VibrationalQNType vis : castedCase.getQNs().getVis()) {
				Integer mode = vis.getMode();
				if (mode > 6) {
					needSpecialQ = true;
					break;
				}
				HitranData.getv()[mode - 1] = vis.getValue();
			}
			if (needSpecialQ) {
				HitranData.setGlobalQ(CaseUtil.getSpecialGlobalQString(castedCase.getQNs().getVis()));
			}

			HitranData.setL_class7(castedCase.getQNs().getL());
			HitranData.setParity(castedCase.getQNs().getParity());
			HitranData.setVibInv(castedCase.getQNs().getVibInv());
		}
		return result.toString();
	}
}
