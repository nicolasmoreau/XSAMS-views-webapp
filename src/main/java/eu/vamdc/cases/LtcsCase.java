package eu.vamdc.cases;

import java.util.Locale;

import org.vamdc.xsams.cases.ltcs.Case;

import eu.vamdc.hitran.HitranData;
import eu.vamdc.util.FormatUtil;

public class LtcsCase implements MolecularCase{

	@Override
	public String getCaseString(CaseParameters parameters) throws CaseException {
		StringBuilder result = new StringBuilder();
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
			HitranData.setL2(castedCase.getQNs().getL2());
			HitranData.getv()[0] = castedCase.getQNs().getV1();
			HitranData.getv()[1] = castedCase.getQNs().getV2();
			HitranData.getv()[2] = castedCase.getQNs().getV3();
			if (castedCase.getQNs().getR() != null)
				HitranData.setRank(castedCase.getQNs().getR().getValue());
			HitranData.setParity(castedCase.getQNs().getParity());

		} else {
			result.append(String.format(Locale.ROOT, "%10s", " "));
			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%5s", " "));
			else
				result.append(FormatUtil.getFFormat5(castedCase.getQNs().getF().getValue()));
			HitranData.setJup(Double.valueOf(castedCase.getQNs().getJ()));

			/* Get some global quanta */
			HitranData.setL2(castedCase.getQNs().getL2());
			HitranData.getv()[0] = castedCase.getQNs().getV1();
			HitranData.getv()[1] = castedCase.getQNs().getV2();
			HitranData.getv()[2] = castedCase.getQNs().getV3();
			if (castedCase.getQNs().getR() != null)
				HitranData.setRank(castedCase.getQNs().getR().getValue());
			HitranData.setParity(castedCase.getQNs().getParity());

		}
		return result.toString();
	}
}
