package eu.vamdc.cases;

import java.util.Locale;

import org.vamdc.xsams.cases.ltcs.Case;
import eu.vamdc.util.FormatUtil;

public class LtcsCase implements MolecularCase{

	@Override
	public String getCaseString(CaseParameters parameters, QuantumNumbers qn) throws CaseException {
		StringBuilder result = new StringBuilder();
		Case castedCase = (Case) parameters.getBaseCase();
		String Br = " ";
		String J = "   ";

		if (parameters.getLevel().equals(CaseUtil.LOWER_LEVEL)) {
			qn.setJlow(Double.valueOf(castedCase.getQNs().getJ()));
			if (qn.getJlow() != null) {
				J = String.format(Locale.ROOT, "%3d", qn.getJlow().intValue());
				/* If Jup has already been assigned then get the branch name */
				if (qn.getJup() > -1.0) {
					try {
						Br = CaseUtil.getBranchName(qn.getJup(), qn.getJlow());
					} catch (IllegalArgumentException e) {
						System.out.println("Branch not allowed for one transition: Jup=" + qn.getJup() + ", Jlow=" + qn.getJlow());
					}
					qn.setJlow(-1.0);
					qn.setJup(-1.0);
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
			qn.setL2(castedCase.getQNs().getL2());
			qn.getV()[0] = castedCase.getQNs().getV1();
			qn.getV()[1] = castedCase.getQNs().getV2();
			qn.getV()[2] = castedCase.getQNs().getV3();
			if (castedCase.getQNs().getR() != null)
				qn.setRank(castedCase.getQNs().getR().getValue());
			qn.setParity(castedCase.getQNs().getParity());

		} else {
			result.append(String.format(Locale.ROOT, "%10s", " "));
			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%5s", " "));
			else
				result.append(FormatUtil.getFFormat5(castedCase.getQNs().getF().getValue()));
			qn.setJup(Double.valueOf(castedCase.getQNs().getJ()));

			/* Get some global quanta */
			qn.setL2(castedCase.getQNs().getL2());
			qn.getV()[0] = castedCase.getQNs().getV1();
			qn.getV()[1] = castedCase.getQNs().getV2();
			qn.getV()[2] = castedCase.getQNs().getV3();
			if (castedCase.getQNs().getR() != null)
				qn.setRank(castedCase.getQNs().getR().getValue());
			qn.setParity(castedCase.getQNs().getParity());

		}
		return result.toString();
	}
}
