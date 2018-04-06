package eu.vamdc.cases;

import java.util.Locale;

import org.vamdc.xsams.cases.common.VibrationalAMQNType;
import org.vamdc.xsams.cases.common.VibrationalQNType;
import org.vamdc.xsams.cases.lpcs.Case;

import eu.vamdc.hitran.HitranData;
import eu.vamdc.util.FormatUtil;

public class LpcsCase implements MolecularCase{

	@Override
	public String getCaseString(CaseParameters parameters, QuantumNumbers qn) throws CaseException {
		StringBuilder result = new StringBuilder();
		boolean needSpecialQ = false;
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
			for (VibrationalQNType vis : castedCase.getQNs().getVis()) {
				Integer mode = vis.getMode();
				qn.getV()[mode - 1] = vis.getValue();
			}
			qn.setL_class7(castedCase.getQNs().getL());
			qn.setParity(castedCase.getQNs().getParity());
			qn.setVibInv(castedCase.getQNs().getVibInv());

		} else {
			result.append(String.format(Locale.ROOT, "%10s", " "));
			/* no F in this case */
			/*
			 * if (castedCase.getQNs().getF() == null)
			 * result.append(String.format(Locale.ROOT, "%5s", " ")); else
			 * result.append(getFFormat5(castedCase.getQNs().getF().getValue()));
			 */
			qn.setJup(Double.valueOf(castedCase.getQNs().getJ()));

			/* Get some global quanta */
			int count = 0;
			for (VibrationalQNType vis : castedCase.getQNs().getVis()) {
				Integer mode = vis.getMode();
				/* mode = 7 with 43-HC3N-Hitran Online */
				if (mode > 6) {
					needSpecialQ = true;
				}
				qn.getV()[mode - 1] = vis.getValue();
				count++;
			}
			qn.setL_class7(castedCase.getQNs().getL());
			qn.setParity(castedCase.getQNs().getParity());
			qn.setVibInv(castedCase.getQNs().getVibInv());
			if (needSpecialQ) {
				StringBuffer tmp = new StringBuffer();

				for (int i = 0; i < count; i++) {
					tmp.append(String.format(Locale.ROOT, "%d", qn.getV()[i]));
				}
				Integer[] l = new Integer[10];
				int i = 0;
				for (VibrationalAMQNType lis : castedCase.getQNs().getLis()) {
					l[i] = lis.getValue();
					i++;
				}
				int l5 = (l[5 - 1] == null ? 0 : l[5 - 1]);
				int l6 = (l[6 - 1] == null ? 0 : l[6 - 1]);
				int l7 = (l[7 - 1] == null ? 0 : l[7 - 1]);
				tmp.append(String.format(Locale.ROOT, "%2d%2d%2d", l5, l6, l7));
				qn.setGlobalQ(tmp.toString());
			}

		}

		return result.toString();
	}
}
