package eu.vamdc.cases;

import java.util.Locale;

import org.vamdc.xsams.cases.hunda.Case;

import eu.vamdc.hitran.HitranData;

public class HundaCase implements MolecularCase{

	@Override
	public String getCaseString(CaseParameters parameters) throws CaseException {
		StringBuilder result = new StringBuilder();
		Case castedCase = (Case) parameters.getBaseCase();
		String Br = " ";
		String J = "     ";

		if (parameters.getLevel().equals(CaseUtil.LOWER_LEVEL)) {
			HitranData.setJlow(castedCase.getQNs().getJ());
			if (HitranData.getJlow() != null) {
				J = String.format(Locale.ROOT, "%5.1f", HitranData.getJlow());
				/* If Jup has already been assigned then get the branch name */
				if (HitranData.getJup() > -1.0) {
					try {
						Br = CaseUtil.getBranchName(HitranData.getJup(), HitranData.getJlow());
					} catch (IllegalArgumentException e) {
						System.out.println("Branch not allowed for one transition: Jup=" + HitranData.getJup() + ", Jlow=" + HitranData.getJlow());
					}
					HitranData.setJup(-1.0);
					HitranData.setJlow(-1.0);
				}
			}
			result.append("   ");
			/* Br */
			result.append(String.format(Locale.ROOT, "%1s", Br));
			/* J */
			result.append(J);
			/* Sym */
			String sym = castedCase.getQNs().getKronigParity();
			result.append(String.format(Locale.ROOT, "%1s", sym == null ? " " : sym));
			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%5s", " "));
			else
				result.append(String.format(Locale.ROOT, "%5.1f", castedCase.getQNs().getF().getValue()));

			// Get some global quanta if available.
			HitranData.setElecStateLabel(castedCase.getQNs().getElecStateLabel());
			HitranData.getv()[0] = castedCase.getQNs().getV();
			HitranData.setOmega(castedCase.getQNs().getOmega());

		} else {
			result.append(String.format(Locale.ROOT, "%10s", " "));
			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%5s", " "));
			else
				result.append(String.format(Locale.ROOT, "%5.1f", castedCase.getQNs().getF().getValue()));
			HitranData.setJup(castedCase.getQNs().getJ());

			// Get some global quanta if available.
			HitranData.setElecStateLabel(castedCase.getQNs().getElecStateLabel());
			HitranData.getv()[0] = castedCase.getQNs().getV();
			HitranData.setOmega(castedCase.getQNs().getOmega());

		}
		return result.toString();
	}
}
