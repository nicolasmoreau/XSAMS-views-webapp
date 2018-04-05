package eu.vamdc.cases;

import java.util.Locale;

import org.vamdc.xsams.cases.hundb.Case;

import eu.vamdc.hitran.HitranData;
import eu.vamdc.util.FormatUtil;

public class HundbCase implements MolecularCase{

	@Override
	public String getCaseString(CaseParameters parameters) throws CaseException {
		StringBuilder result = new StringBuilder();
		Case castedCase = (Case) parameters.getBaseCase();
		String BrJ = " ";
		String J = "   ";
		String BrN = " ";
		String N = "   ";

		if (parameters.getLevel().equals(CaseUtil.LOWER_LEVEL)) {
			HitranData.setJlow(castedCase.getQNs().getJ());
			if (HitranData.getJlow() != null) {
				J = String.format(Locale.ROOT, "%3d", HitranData.getJlow().intValue());
				/* If Jup has already been assigned then get the branch name */
				if (HitranData.getJup() > -1.0) {
					try {
						BrJ = CaseUtil.getBranchName(HitranData.getJup(), HitranData.getJlow());
					} catch (IllegalArgumentException e) {
						System.out.println("Branch not allowed for one transition: Jup=" + HitranData.getJup() + ", Jlow=" + HitranData.getJlow());
					}
					HitranData.setJlow(-1.0);
					HitranData.setJup(-1.0);
				}
			}
			HitranData.setNlow(castedCase.getQNs().getN());
			N = String.format(Locale.ROOT, "%3d", HitranData.getNlow());
			if (HitranData.getNup() > -1) {

				try {
					BrN = CaseUtil.getBranchName(Double.valueOf(HitranData.getNup()), Double.valueOf(HitranData.getNlow()));
				} catch (IllegalArgumentException e) {
					System.out.println("Branch not allowed for one transition: Nup=" + HitranData.getNup() + ", Nlow=" + HitranData.getNlow());
				}
				HitranData.setNlow(-1);
				HitranData.setNup(-1);
			}
			result.append(' ');
			/* Br */
			result.append(String.format(Locale.ROOT, "%1s", BrN));
			/* N */
			result.append(N);
			/* Br */
			result.append(String.format(Locale.ROOT, "%1s", BrJ));
			/* J */
			result.append(J);
			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%5s", " "));
			else
				result.append(FormatUtil.getFFormat5(castedCase.getQNs().getF().getValue()));
			/* Sym */
			if (castedCase.getQNs().getKronigParity() == null)
				result.append(' ');
			else
				result.append(String.format(Locale.ROOT, "%1s", castedCase.getQNs().getKronigParity()));

			// Get some global quanta if available.
			HitranData.setElecStateLabel(castedCase.getQNs().getElecStateLabel());
			HitranData.getv()[0] = castedCase.getQNs().getV();

		} else {
			result.append(String.format(Locale.ROOT, "%10s", " "));
			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%5s", " "));
			else
				result.append(FormatUtil.getFFormat5(castedCase.getQNs().getF().getValue()));
			HitranData.setJup(castedCase.getQNs().getJ());
			HitranData.setNup(castedCase.getQNs().getN());

			// Get some global quanta if available.
			HitranData.setElecStateLabel(castedCase.getQNs().getElecStateLabel());
			HitranData.getv()[0] = castedCase.getQNs().getV();

		}
		return result.toString();
	}
}
