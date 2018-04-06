package eu.vamdc.cases;

import java.util.Locale;

import org.vamdc.xsams.cases.hundb.Case;

import eu.vamdc.hitran.HitranData;
import eu.vamdc.util.FormatUtil;

public class HundbCase implements MolecularCase{

	@Override
	public String getCaseString(CaseParameters parameters, QuantumNumbers qn) throws CaseException {
		StringBuilder result = new StringBuilder();
		Case castedCase = (Case) parameters.getBaseCase();
		String BrJ = " ";
		String J = "   ";
		String BrN = " ";
		String N = "   ";

		if (parameters.getLevel().equals(CaseUtil.LOWER_LEVEL)) {
			qn.setJlow(castedCase.getQNs().getJ());
			if (qn.getJlow() != null) {
				J = String.format(Locale.ROOT, "%3d", qn.getJlow().intValue());
				/* If Jup has already been assigned then get the branch name */
				if (qn.getJup() > -1.0) {
					try {
						BrJ = CaseUtil.getBranchName(qn.getJup(), qn.getJlow());
					} catch (IllegalArgumentException e) {
						System.out.println("Branch not allowed for one transition: Jup=" + qn.getJup() + ", Jlow=" + qn.getJlow());
					}
					qn.setJlow(-1.0);
					qn.setJup(-1.0);
				}
			}
			qn.setNlow(castedCase.getQNs().getN());
			N = String.format(Locale.ROOT, "%3d", qn.getNlow());
			if (qn.getNup() > -1) {

				try {
					BrN = CaseUtil.getBranchName(Double.valueOf(qn.getNup()), Double.valueOf(qn.getNlow()));
				} catch (IllegalArgumentException e) {
					System.out.println("Branch not allowed for one transition: Nup=" + qn.getNup() + ", Nlow=" + qn.getNlow());
				}
				qn.setNlow(-1);
				qn.setNup(-1);
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
			qn.setElecStateLabel(castedCase.getQNs().getElecStateLabel());
			qn.getV()[0] = castedCase.getQNs().getV();

		} else {
			result.append(String.format(Locale.ROOT, "%10s", " "));
			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%5s", " "));
			else
				result.append(FormatUtil.getFFormat5(castedCase.getQNs().getF().getValue()));
			qn.setJup(castedCase.getQNs().getJ());
			qn.setNup(castedCase.getQNs().getN());

			// Get some global quanta if available.
			qn.setElecStateLabel(castedCase.getQNs().getElecStateLabel());
			qn.getV()[0] = castedCase.getQNs().getV();

		}
		return result.toString();
	}
}
