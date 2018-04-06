package eu.vamdc.cases;

import java.util.Locale;

import org.vamdc.xsams.cases.hunda.Case;

import eu.vamdc.hitran.HitranData;

public class HundaCase implements MolecularCase{

	@Override
	public String getCaseString(CaseParameters parameters, QuantumNumbers qn) throws CaseException {
		StringBuilder result = new StringBuilder();
		Case castedCase = (Case) parameters.getBaseCase();
		String Br = " ";
		String J = "     ";

		if (parameters.getLevel().equals(CaseUtil.LOWER_LEVEL)) {
			qn.setJlow(castedCase.getQNs().getJ());
			if (qn.getJlow() != null) {
				J = String.format(Locale.ROOT, "%5.1f", qn.getJlow());
				/* If Jup has already been assigned then get the branch name */
				if (qn.getJup() > -1.0) {
					try {
						Br = CaseUtil.getBranchName(qn.getJup(), qn.getJlow());
					} catch (IllegalArgumentException e) {
						System.out.println("Branch not allowed for one transition: Jup=" + qn.getJup() + ", Jlow=" + qn.getJlow());
					}
					qn.setJup(-1.0);
					qn.setJlow(-1.0);
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
			qn.setElecStateLabel(castedCase.getQNs().getElecStateLabel());
			qn.getV()[0] = castedCase.getQNs().getV();
			qn.setOmega(castedCase.getQNs().getOmega());

		} else {
			result.append(String.format(Locale.ROOT, "%10s", " "));
			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%5s", " "));
			else
				result.append(String.format(Locale.ROOT, "%5.1f", castedCase.getQNs().getF().getValue()));
			qn.setJup(castedCase.getQNs().getJ());

			// Get some global quanta if available.
			qn.setElecStateLabel(castedCase.getQNs().getElecStateLabel());
			qn.getV()[0] = castedCase.getQNs().getV();
			qn.setOmega(castedCase.getQNs().getOmega());

		}
		return result.toString();
	}
}
