package eu.vamdc.hitran;

import java.util.List;
import java.util.Locale;

import org.vamdc.xsams.cases.common.BaseCase;
import org.vamdc.xsams.cases.common.RankingType;
import org.vamdc.xsams.cases.common.SymmetrySpeciesType;
import org.vamdc.xsams.cases.common.VibrationalQNType;
import org.vamdc.xsams.schema.BasisStateType;
import org.vamdc.xsams.schema.MolecularStateType;
import org.vamdc.xsams.schema.StateExpansionType;

public class HitranData {

	public final double c = 299792458; // speed of light (m/s)
	public final double L = 2.6867774e19; // Loschmidt number (molecule.cm-3)

	private int M; // Molecule Number
	private int I; // Isotopologue number
	private double vacWn; // Vacuum wavenumber
	private double vacWnErr; // Vacuum wavenumber accuracy
	private int vacWnRef; // Vacuum wavenumber Reference
	private double S; // Intensity
	private double SErr; // Intensity accuracy
	private int SRef; // Intensity reference
	private double A; // Einstein A-coefficient
	private double gammaAir; // Air-broadened half-width
	private double gammaAirErr; // Air-broadened half-width accuracy
	private int gammaAirRef; // Air-broadened half-width accuracy
	private double gammaSelf; // Self-broadened half-width
	private double gammaSelfErr; // Self-broadened half-width accuracy
	private int gammaSelfRef; // Self-broadened half-width Reference
	private double eLow; // Lower-state energy
	private double nAir; // Temperature-dependence exponent for gammaAir
	private double nAirErr; // Temperature-dependence exponent for gammaAir
							// accuracy
	private int nAirRef; // Temperature-dependence exponent for gammaAir
							// reference

	private double deltaAir; // Air Pressure-induced line shift
	private double deltaAirErr; // Air Pressure-induced line shift accuracy
	private int deltaAirRef; // Air Pressure-induced line shift reference
	private String vUp; // Upper-state "global" quanta
	private String vLow; // Lower-state "global" quanta
	private String qUp; // Upper-state "local" quanta
	private String qLow; // Lower-state "local" quanta
	private String iErr; // Uncertainty indices
	private String iRef; // Reference indices
	private char flag; // flag
	private double gUp; // Statistical weight of the upper state
	private double gLow; // Statistical weight of the lower state

	private static Double Jup = -1.0;
	private static Double Jlow = -1.0;
	private static Integer Nup = -1;
	private static Integer Nlow = -1;

	// Some global quanta
	private static String elecStateLabel;
	private static Double omega;
	private static Integer l2;
	private static Integer l_class7;
	private static String parity;
	private static Integer[] v = new Integer[6];
	private static Integer rank;
	private static String vibInv;
	// Global Q when class 10
	private static String globalQ = "";

	/** Gets the type of the branch. Other values are not allowed
	 * 
	 * @param X1
	 * @param X2
	 * @return the type of the branch: O, P, Q, R or S
	 */
	
	private String getBranchName(Double X1, Double X2) {
		Double DX = X1 - X2;
		switch (DX.intValue()) {
		case -2:
			return "O";
		case -1:
			return "P";
		case 0:
			return "Q";
		case 1:
			return "R";
		case 2:
			return "S";
		default:
			throw new IllegalArgumentException("Branch not allowed: DX=" + X1 + "-" + X2);
		}
	}

	private Integer getRankingValue(List<RankingType> RS, String rankingName) {
		for (RankingType ranking : RS) {
			if (ranking.getName().equals(rankingName))
				return ranking.getValue();
		}
		return 0;
	}

	private String getFFormat5(double F) {
		String str;

		/* We check if F is integer or half integer */
		if (F != Math.round(F))
			str = String.format(Locale.ROOT, "%5.1f", F);
		else
			str = String.format(Locale.ROOT, "%5.0f", F);

		return str;
	}

	private String getFFormat4(double F) {
		String str;

		/* We check if F is integer or half integer */
		if (F != Math.round(F))
			str = String.format(Locale.ROOT, "%4.1f", F);
		else
			str = String.format(Locale.ROOT, "%4.0f", F);

		return str;
	}

	/**
	 * Group 1
	 * 
	 * @param qnCase
	 * @return
	 */
	private String nltcsCase(BaseCase qnCase) {
		StringBuffer result = new StringBuffer();
		org.vamdc.xsams.cases.nltcs.Case castedCase = (org.vamdc.xsams.cases.nltcs.Case) qnCase;

		/* J */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getJ()));
		/* Ka */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getKa()));
		/* Kc */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getKc()));
		/* F */
		if (castedCase.getQNs().getF() == null) {
			result.append(String.format(Locale.ROOT, "%5s", " "));
		} else {
			result.append(getFFormat5(castedCase.getQNs().getF().getValue()));
		}
		/* Sym */
		if (castedCase.getQNs().getParity() == null) {
			result.append(' ');
		} else {
			result.append(String.format(Locale.ROOT, "%1s", castedCase.getQNs().getParity()));
		}

		/* Get some global quanta */
		v[0] = castedCase.getQNs().getV1();
		v[1] = castedCase.getQNs().getV2();
		v[2] = castedCase.getQNs().getV3();

		return result.toString();
	}

	/**
	 * Group 1
	 * 
	 * @param qnCase
	 * @return
	 */
	private String asymcsCase(BaseCase qnCase) {
		StringBuffer result = new StringBuffer();
		boolean needSpecialQ = false;
		org.vamdc.xsams.cases.asymcs.Case castedCase = (org.vamdc.xsams.cases.asymcs.Case) qnCase;

		/* J */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getJ()));
		/* Ka */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getKa()));
		/* Kc */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getKc()));

		/* F */
		if (castedCase.getQNs().getF() == null)
			result.append(String.format(Locale.ROOT, "%5s", " "));
		else
			result.append(getFFormat5(castedCase.getQNs().getF().getValue()));

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
			v[mode - 1] = vis.getValue();
		}
		if (needSpecialQ) {
			globalQ = getSpecialGlobalQString(castedCase.getQNs().getVis());
		}

		return result.toString();
	}

	/**
	 * Group 1, tested for HO2
	 * 
	 * @param qnCase
	 * @return
	 */
	private String asymosCase(BaseCase qnCase) {
		StringBuffer result = new StringBuffer();
		boolean needSpecialQ = false;
		org.vamdc.xsams.cases.asymos.Case castedCase = (org.vamdc.xsams.cases.asymos.Case) qnCase;

		/* N */
		/*
		 * In this case J is Double. For matching HITRAN format we take N, the
		 * quantum number associated with the rotational angular momentum
		 */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getN()));
		/* Ka */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getKa()));
		/* Kc */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getKc()));

		/* F */
		if (castedCase.getQNs().getF() == null)
			result.append(String.format(Locale.ROOT, "%5s", " "));
		else
			result.append(getFFormat5(castedCase.getQNs().getF().getValue()));

		/*
		 * Sym field (not a symmetry) is the J-coding defined as follows: 
		 * + means J = N+1/2 and - means J = N-1/2
		 */

		double Sym = castedCase.getQNs().getJ() - castedCase.getQNs().getN();
		if (Sym == 0.5)
			result.append(String.format(Locale.ROOT, "%1s", "+"));
		else
			result.append(String.format(Locale.ROOT, "%1s", "-"));

		/* Get some global quanta */
		for (VibrationalQNType vis : castedCase.getQNs().getVis()) {
			Integer mode = vis.getMode();
			if (mode > 6) {
				needSpecialQ = true;
				break;
			}
			v[mode - 1] = vis.getValue();
		}
		if (needSpecialQ) {
			globalQ = getSpecialGlobalQString(castedCase.getQNs().getVis());
		}

		return result.toString();
	}

	/**
	 * Group 1, tested for NO2
	 * 
	 * @param qnCase
	 * @return
	 */

	private String nltosCase(BaseCase qnCase) {
		StringBuffer result = new StringBuffer();
		org.vamdc.xsams.cases.nltos.Case castedCase = (org.vamdc.xsams.cases.nltos.Case) qnCase;

		/* N */
		/*
		 * In this case J is Double. For matching HITRAN format we take N, the
		 * quantum number associated with the rotational angular momentum
		 */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getN()));
		/* Ka */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getKa()));
		/* Kc */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getKc()));

		/* F */
		if (castedCase.getQNs().getF() == null)
			result.append(String.format(Locale.ROOT, "%5s", " "));
		else
			result.append(getFFormat5(castedCase.getQNs().getF().getValue()));

		/*
		 * Sym field (not a symmetry) is the J-coding defined as follows: +
		 * means J = N+1/2 and - means J = N-1/2
		 */

		double Sym = castedCase.getQNs().getJ() - castedCase.getQNs().getN();
		if (Sym == 0.5)
			result.append(String.format(Locale.ROOT, "%1s", "+"));
		else
			result.append(String.format(Locale.ROOT, "%1s", "-"));

		/* Get some global quanta */
		v[0] = castedCase.getQNs().getV1();
		v[1] = castedCase.getQNs().getV2();
		v[2] = castedCase.getQNs().getV3();

		return result.toString();
	}

	/**
	 * Group 2
	 * 
	 * @param qnCase
	 * @param level
	 * @return
	 */
	private String ltcsCase(BaseCase qnCase, String level) {
		StringBuffer result = new StringBuffer();
		org.vamdc.xsams.cases.ltcs.Case castedCase = (org.vamdc.xsams.cases.ltcs.Case) qnCase;
		String Br = " ";
		String J = "   ";

		if (level.equals("lower")) {
			Jlow = Double.valueOf(castedCase.getQNs().getJ());
			if (Jlow != null) {
				J = String.format(Locale.ROOT, "%3d", Jlow.intValue());
				/* If Jup has already been assigned then get the branch name */
				if (Jup > -1.0) {
					try {
						Br = getBranchName(Jup, Jlow);
					} catch (IllegalArgumentException e) {
						System.out.println("Branch not allowed for one transition: Jup=" + Jup + ", Jlow=" + Jlow);
					}
					Jup = Jlow = -1.0;
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
				result.append(getFFormat5(castedCase.getQNs().getF().getValue()));

			/* Get some global quanta */
			l2 = castedCase.getQNs().getL2();
			v[0] = castedCase.getQNs().getV1();
			v[1] = castedCase.getQNs().getV2();
			v[2] = castedCase.getQNs().getV3();
			if (castedCase.getQNs().getR() != null)
				rank = castedCase.getQNs().getR().getValue();
			parity = castedCase.getQNs().getParity();

		} else {
			result.append(String.format(Locale.ROOT, "%10s", " "));
			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%5s", " "));
			else
				result.append(getFFormat5(castedCase.getQNs().getF().getValue()));
			Jup = Double.valueOf(castedCase.getQNs().getJ());

			/* Get some global quanta */
			l2 = castedCase.getQNs().getL2();
			v[0] = castedCase.getQNs().getV1();
			v[1] = castedCase.getQNs().getV2();
			v[2] = castedCase.getQNs().getV3();
			if (castedCase.getQNs().getR() != null)
				rank = castedCase.getQNs().getR().getValue();
			parity = castedCase.getQNs().getParity();

		}
		return result.toString();
	}

	/**
	 * Group 2, CO
	 * 
	 * @param qnCase
	 * @param level
	 * @return
	 */
	private String dcsCase(BaseCase qnCase, String level) {
		StringBuffer result = new StringBuffer();
		org.vamdc.xsams.cases.dcs.Case castedCase = (org.vamdc.xsams.cases.dcs.Case) qnCase;
		String Br = " ";
		String J = "   ";

		if (level.equals("lower")) {
			Jlow = Double.valueOf(castedCase.getQNs().getJ());
			if (Jlow != null) {
				J = String.format(Locale.ROOT, "%3d", Jlow.intValue());
				/* If Jup has already been assigned then get the branch name */
				if (Jup > -1.0) {
					try {
						Br = getBranchName(Jup, Jlow);
					} catch (IllegalArgumentException e) {
						System.out.println("Branch not allowed for one transition: Jup=" + Jup + ", Jlow=" + Jlow);
					}
					Jup = Jlow = -1.0;
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
				result.append(getFFormat5(castedCase.getQNs().getF().getValue()));

			/* Get some global quanta */
			v[0] = castedCase.getQNs().getV();

		} else {
			result.append(String.format(Locale.ROOT, "%10s", " "));
			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%5s", " "));
			else
				result.append(getFFormat5(castedCase.getQNs().getF().getValue()));
			Jup = Double.valueOf(castedCase.getQNs().getJ());

			/* Get some global quanta */
			v[0] = castedCase.getQNs().getV();
		}
		return result.toString();
	}

	private String lpcsCase(BaseCase qnCase, String level) {
		StringBuffer result = new StringBuffer();
		boolean needSpecialQ = false;
		org.vamdc.xsams.cases.lpcs.Case castedCase = (org.vamdc.xsams.cases.lpcs.Case) qnCase;
		String Br = " ";
		String J = "   ";

		if (level.equals("lower")) {
			Jlow = Double.valueOf(castedCase.getQNs().getJ());
			if (Jlow != null) {
				J = String.format(Locale.ROOT, "%3d", Jlow.intValue());
				/* If Jup has already been assigned then get the branch name */
				if (Jup > -1.0) {
					try {
						Br = getBranchName(Jup, Jlow);
					} catch (IllegalArgumentException e) {
						System.out.println("Branch not allowed for one transition: Jup=" + Jup + ", Jlow=" + Jlow);
					}
					Jup = Jlow = -1.0;
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
				result.append(getFFormat5(castedCase.getQNs().getF().getValue()));

			/* Get some global quanta */
			for (VibrationalQNType vis : castedCase.getQNs().getVis()) {
				Integer mode = vis.getMode();
				v[mode - 1] = vis.getValue();
			}
			l_class7 = castedCase.getQNs().getL();
			parity = castedCase.getQNs().getParity();
			vibInv = castedCase.getQNs().getVibInv();

		} else {
			result.append(String.format(Locale.ROOT, "%10s", " "));
			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%5s", " "));
			else
				result.append(getFFormat5(castedCase.getQNs().getF().getValue()));
			Jup = Double.valueOf(castedCase.getQNs().getJ());

			/* Get some global quanta */
			for (VibrationalQNType vis : castedCase.getQNs().getVis()) {
				Integer mode = vis.getMode();
				if (mode > 6) {
					needSpecialQ = true;
					break;
				}
				v[mode - 1] = vis.getValue();
			}
			if (needSpecialQ) {
				globalQ = getSpecialGlobalQString(castedCase.getQNs().getVis());
			}

			l_class7 = castedCase.getQNs().getL();
			parity = castedCase.getQNs().getParity();
			vibInv = castedCase.getQNs().getVibInv();
		}

		return result.toString();
	}

	/**
	 * Group 3
	 * 
	 * @param qnCase
	 * @return
	 */

	private String sphcsCase(BaseCase qnCase) {
		StringBuffer result = new StringBuffer();
		org.vamdc.xsams.cases.sphcs.Case castedCase = (org.vamdc.xsams.cases.sphcs.Case) qnCase;

		/* J */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getJ()));
		result.append(' ');
		/* C */
		/*
		 * WARNING Contrary to what we retrieve in HITRAN database we want C2H4
		 * in the same output that CH4 but in this case Symmetry has a length of
		 * 3. So we need to check it before.
		 */
		String Sym = castedCase.getQNs().getRovibSym().getValue();
		if (Sym.length() == 3) // C2H4
			result.append(String.format(Locale.ROOT, "%-3s", Sym));
		else {
			result.append(String.format(Locale.ROOT, "%-2s ", Sym));
		}
		/* alpha */
		result.append(String.format(Locale.ROOT, "%3d", getRankingValue(castedCase.getQNs().getRS(), "alpha")));
		/* F */
		if (castedCase.getQNs().getF() == null)
			result.append(String.format(Locale.ROOT, "%5s", " "));
		else
			result.append(getFFormat5(castedCase.getQNs().getF().getValue()));

		return result.toString();
	}

	/**
	 * Group 4
	 * 
	 * @param qnCase
	 * @return
	 */

	private String stcsCase(BaseCase qnCase) {
		StringBuffer result = new StringBuffer();
		boolean needSpecialQ = false;
		org.vamdc.xsams.cases.stcs.Case castedCase = (org.vamdc.xsams.cases.stcs.Case) qnCase;

		/* J */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getJ()));
		/* K */
		result.append(String.format(Locale.ROOT, "%3d", castedCase.getQNs().getK()));
		/* l */
		result.append(String.format(Locale.ROOT, "%2s", " ")); // TODO
		/* C */
		SymmetrySpeciesType C = castedCase.getQNs().getRovibSym();
		result.append(String.format(Locale.ROOT, "%2s", C == null ? " " : C.getValue()));
		/* Sym */
		String Sym = null; // castedCase.getQNs().get
		result.append(String.format(Locale.ROOT, "%1s", Sym == null ? " " : Sym)); // TODO

		/* F */
		if (castedCase.getQNs().getF() == null)
			result.append(String.format(Locale.ROOT, "%4s", " "));
		else
			result.append(getFFormat4(castedCase.getQNs().getF().getValue()));

		/* Get some global quanta */
		for (VibrationalQNType vis : castedCase.getQNs().getVis()) {
			Integer mode = vis.getMode();
			/* For larger molecule like CH3OH from JPL */
			if (mode > 6) {
				needSpecialQ = true;
				break;
			}
			v[mode - 1] = vis.getValue();
		}
		if (needSpecialQ) {
			globalQ = getSpecialGlobalQString(castedCase.getQNs().getVis());
		}

		parity = castedCase.getQNs().getParity();

		return result.toString();
	}

	/**
	 * Group 5
	 * 
	 * @param qnCase
	 * @param level
	 * @return
	 */

	private String hundbCase(BaseCase qnCase, String level) {
		StringBuffer result = new StringBuffer();
		org.vamdc.xsams.cases.hundb.Case castedCase = (org.vamdc.xsams.cases.hundb.Case) qnCase;
		String BrJ = " ";
		String J = "   ";
		String BrN = " ";
		String N = "   ";

		if (level.equals("lower")) {
			Jlow = castedCase.getQNs().getJ();
			if (Jlow != null) {
				J = String.format(Locale.ROOT, "%3d", Jlow.intValue());
				/* If Jup has already been assigned then get the branch name */
				if (Jup > -1.0) {
					try {
						BrJ = getBranchName(Jup, Jlow);
					} catch (IllegalArgumentException e) {
						System.out.println("Branch not allowed for one transition: Jup=" + Jup + ", Jlow=" + Jlow);
					}
					Jup = Jlow = -1.0;
				}
			}
			Nlow = castedCase.getQNs().getN();
			N = String.format(Locale.ROOT, "%3d", Nlow);
			if (Nup > -1) {

				try {
					BrN = getBranchName(Double.valueOf(Nup), Double.valueOf(Nlow));
				} catch (IllegalArgumentException e) {
					System.out.println("Branch not allowed for one transition: Nup=" + Nup + ", Nlow=" + Nlow);
				}
				Nup = Nlow = -1;
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
				result.append(getFFormat5(castedCase.getQNs().getF().getValue()));
			/* Sym */
			if (castedCase.getQNs().getKronigParity() == null)
				result.append(' ');
			else
				result.append(String.format(Locale.ROOT, "%1s", castedCase.getQNs().getKronigParity()));

			// Get some global quanta if available.
			elecStateLabel = castedCase.getQNs().getElecStateLabel();
			v[0] = castedCase.getQNs().getV();

		} else {
			result.append(String.format(Locale.ROOT, "%10s", " "));
			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%5s", " "));
			else
				result.append(getFFormat5(castedCase.getQNs().getF().getValue()));
			Jup = castedCase.getQNs().getJ();
			Nup = castedCase.getQNs().getN();

			// Get some global quanta if available.
			elecStateLabel = castedCase.getQNs().getElecStateLabel();
			v[0] = castedCase.getQNs().getV();

		}
		return result.toString();
	}

	/**
	 * Group 6
	 * 
	 * @param qnCase
	 * @param level
	 * @return
	 */

	private String hundaCase(BaseCase qnCase, String level) {
		StringBuffer result = new StringBuffer();
		org.vamdc.xsams.cases.hunda.Case castedCase = (org.vamdc.xsams.cases.hunda.Case) qnCase;
		String Br = " ";
		String J = "     ";

		if (level.equals("lower")) {
			Jlow = castedCase.getQNs().getJ();
			if (Jlow != null) {
				J = String.format(Locale.ROOT, "%5.1f", Jlow);
				/* If Jup has already been assigned then get the branch name */
				if (Jup > -1.0) {
					try {
						Br = getBranchName(Jup, Jlow);
					} catch (IllegalArgumentException e) {
						System.out.println("Branch not allowed for one transition: Jup=" + Jup + ", Jlow=" + Jlow);
					}
					Jup = Jlow = -1.0;
				}
			}
			result.append("   ");
			/* Br */
			result.append(String.format(Locale.ROOT, "%1s", Br));
			/* J */
			result.append(J);
			/* Sym */
			String Sym = castedCase.getQNs().getKronigParity();
			result.append(String.format(Locale.ROOT, "%1s", Sym == null ? " " : Sym));
			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%5s", " "));
			else
				result.append(String.format(Locale.ROOT, "%5.1f", castedCase.getQNs().getF().getValue()));

			// Get some global quanta if available.
			elecStateLabel = castedCase.getQNs().getElecStateLabel();
			v[0] = castedCase.getQNs().getV();
			omega = castedCase.getQNs().getOmega();

		} else {
			result.append(String.format(Locale.ROOT, "%10s", " "));
			/* F */
			if (castedCase.getQNs().getF() == null)
				result.append(String.format(Locale.ROOT, "%5s", " "));
			else
				result.append(String.format(Locale.ROOT, "%5.1f", castedCase.getQNs().getF().getValue()));
			Jup = castedCase.getQNs().getJ();

			// Get some global quanta if available.
			elecStateLabel = castedCase.getQNs().getElecStateLabel();
			v[0] = castedCase.getQNs().getV();
			omega = castedCase.getQNs().getOmega();

		}
		return result.toString();
	}

	private String getSpecialGlobalQString(List<VibrationalQNType> VibQN) {
		StringBuffer result = new StringBuffer();
		Integer nb = 0;
		Integer val = 0;

		for (VibrationalQNType vi : VibQN) {
			if (vi.getValue() == 0)
				continue;
			val = vi.getValue();
			if (nb > 0)
				result.append("+");
			result.append(String.format(Locale.ROOT, "%s", val == 1 ? "" : val));
			result.append("V" + vi.getMode());
			nb++;
		}
		/* if all modes are 0 */
		if (result.toString().equals("")) {
			result.append(String.format(Locale.ROOT, "%7s", " "));
			result.append("GROUND");
		}
		return result.toString();
	}

	/**
	 * Gets the value of the Upper/Lower-state "global" quanta
	 * 
	 * @param ERef
	 * @param vCode
	 * @param M
	 * @param I
	 * @return a string value
	 */

	public String getGlobalQuanta(MolecularStateType ERef, int vCode, int M, int I) {
		StringBuffer result = new StringBuffer();

		/* We first check if BasisStates are set */
		if (ERef.getStateExpansions().isEmpty()) {
			switch (vCode) {
			case 1:
				result.append(String.format(Locale.ROOT, "%13s", " "));
				result.append(String.format(Locale.ROOT, "%2d", v[0] == null ? 0 : v[0]));
				break;
			case 2: // should be O2
				result.append(String.format(Locale.ROOT, "%7s", " "));
				result.append(String.format(Locale.ROOT, "%1s", elecStateLabel == null ? " " : elecStateLabel));
				result.append(String.format(Locale.ROOT, "%5s", " "));
				result.append(String.format(Locale.ROOT, "%2d", v[0] == null ? 0 : v[0]));
				break;
			case 3:
				result.append(String.format(Locale.ROOT, "%7s", " "));
				result.append(String.format(Locale.ROOT, "%1s", elecStateLabel == null ? " " : elecStateLabel));
				if (omega != null)
					result.append(String.format(Locale.ROOT, "%3s", omega == 1.5 ? "3/2" : "1/2")); // i
																						// parameter
				result.append(String.format(Locale.ROOT, "%2s", " "));
				result.append(String.format(Locale.ROOT, "%2d", v[0] == null ? 0 : v[0]));
				break;
			case 4:
				result.append(String.format(Locale.ROOT, "%7s", " "));
				result.append(String.format(Locale.ROOT, "%2d", v[0] == null ? 0 : v[0]));
				result.append(String.format(Locale.ROOT, "%2d", v[1] == null ? 0 : v[1]));
				result.append(String.format(Locale.ROOT, "%2s", String.valueOf(l2 == null ? " " : l2)));
				result.append(String.format(Locale.ROOT, "%2d", v[2] == null ? 0 : v[2]));
				break;
			case 5:
				result.append(String.format(Locale.ROOT, "%6s", " "));
				result.append(String.format(Locale.ROOT, "%2d", v[0] == null ? 0 : v[0]));
				result.append(String.format(Locale.ROOT, "%2d", v[1] == null ? 0 : v[1]));
				result.append(String.format(Locale.ROOT, "%2d", l2 == null ? 0 : l2));
				result.append(String.format(Locale.ROOT, "%2d", v[2] == null ? 0 : v[2]));
				result.append(String.format(Locale.ROOT, "%1s", String.valueOf(rank == null ? " " : rank)));
				break;
			case 6:
				result.append(String.format(Locale.ROOT, "%9s", " "));
				result.append(String.format(Locale.ROOT, "%2d", v[0] == null ? 0 : v[0]));
				result.append(String.format(Locale.ROOT, "%2d", v[1] == null ? 0 : v[1]));
				result.append(String.format(Locale.ROOT, "%2d", v[2] == null ? 0 : v[2]));
				break;
			case 7:
				result.append(String.format(Locale.ROOT, "%2d", v[0] == null ? 0 : v[0]));
				result.append(String.format(Locale.ROOT, "%2d", v[1] == null ? 0 : v[1]));
				result.append(String.format(Locale.ROOT, "%2d", v[2] == null ? 0 : v[2]));
				result.append(String.format(Locale.ROOT, "%2d", v[3] == null ? 0 : v[3]));
				result.append(String.format(Locale.ROOT, "%2d", v[4] == null ? 0 : v[4]));
				result.append(String.format(Locale.ROOT, "%2d", l_class7 == null ? 0 : l_class7));
				result.append(String.format(Locale.ROOT, "%1s", (parity == null || parity.equals("None")) ? " " : parity));
				result.append(String.format(Locale.ROOT, "%1s", rank == null ? " " : String.valueOf(rank)));
				result.append(String.format(Locale.ROOT, "%1s", vibInv == null ? " " : vibInv));
				break;
			case 8:
				result.append(String.format(Locale.ROOT, "%5s", " "));
				result.append(String.format(Locale.ROOT, "%-2d", v[0] == null ? 0 : v[0]));
				result.append(String.format(Locale.ROOT, "%-2d", v[1] == null ? 0 : v[1]));
				result.append(String.format(Locale.ROOT, "%-2d", v[2] == null ? 0 : v[2]));
				result.append(String.format(Locale.ROOT, "%-2d", v[3] == null ? 0 : v[3]));
				/* Get S value */
				if (M == 28) { // PH3 Case, S is blank
					result.append(" ");
				} else {
					result.append(String.format(Locale.ROOT, "%1s", (parity == null || parity.equals("None")) ? " " : parity));
				}
				break;
			case 9:
				/*
				 * FIXME: For H2O2 v4 has been replaced by the torsional quanta
				 * n and tau. But these were not found in xsams
				 */
				result.append(String.format(Locale.ROOT, "%3s", " "));
				result.append(String.format(Locale.ROOT, "%2d", v[0] == null ? 0 : v[0]));
				result.append(String.format(Locale.ROOT, "%2d", v[1] == null ? 0 : v[1]));
				result.append(String.format(Locale.ROOT, "%2d", v[2] == null ? 0 : v[2]));
				result.append(String.format(Locale.ROOT, "%2d", v[3] == null ? 0 : v[3]));
				result.append(String.format(Locale.ROOT, "%2d", v[4] == null ? 0 : v[4]));
				result.append(String.format(Locale.ROOT, "%2d", v[5] == null ? 0 : v[5]));
				break;
			case 10:
				if (globalQ.equals("")) {
					/*
					 * globalQ can be equal to "" if no mode are filled in data.
					 * It seems that by default, if no mode are filled it is
					 * because it is ground state
					 */
					result.append(String.format(Locale.ROOT, "%7s", " "));
					result.append("GROUND");
				} else
					result.append(globalQ);
				break;
			default:
				throw new IllegalArgumentException("Case not handled: " + vCode);
			}
		} else {
			/* Case for ICB data. Only files well done */
			for (StateExpansionType StateExpansion : ERef.getStateExpansions()) {
				/*
				 * Here it's a bit tricky. We need to take the first coeffecient
				 * which is the biggest. Indeed this coefficient is the only
				 * relevant.
				 */
				BasisStateType BasisStateRef = (BasisStateType) StateExpansion.getCoefves().get(0).getBasisStateRef();
				for (BaseCase globalQuanta : BasisStateRef.getCases()) {
					switch (vCode) {
					case 10:
						org.vamdc.xsams.cases.sphcs.Case castedCase = (org.vamdc.xsams.cases.sphcs.Case) globalQuanta;
						if (M == 6 || M == 42) { // CH4, CF4
							Integer nv = getRankingValue(castedCase.getQNs().getRS(), "nv");
							String Sym = castedCase.getQNs().getSyms().get(0).getValue();
							if (I != 3) {
								result.append(String.format(Locale.ROOT, "%3s", " "));
								for (VibrationalQNType vi : castedCase.getQNs().getVis()) {
									result.append(String.format(Locale.ROOT, "%2d", vi.getValue()));
								}
								result.append(String.format(Locale.ROOT, "%2d", nv));
								result.append(String.format(Locale.ROOT, "%-2s", Sym));
							} else { // CH3D
								result.append(" ");
								for (VibrationalQNType vi : castedCase.getQNs().getVis()) {
									result.append(String.format(Locale.ROOT, "%d", vi.getValue()));
								}
								result.append(String.format(Locale.ROOT, "%2d", nv));
								result.append(String.format(Locale.ROOT, "%-2s", Sym));
							}
						} else if (M == 38 || M == 30) { // C2H4, SF6
							/*
							 * HITRAN format is too small for all mode values In
							 * consequence we write GROUND if all v are equal to
							 * 0 or yVX if the Xth mode is different of 0 and is
							 * equal to y.
							 */
							String specialCase = getSpecialGlobalQString(castedCase.getQNs().getVis());
							result.append(specialCase);
						}
						break;
					default:
						throw new IllegalArgumentException("Case not handled " + vCode);
					}
				}
			}
		}
		return result.toString();
	}

	/**
	 * Gets the value of the Upper/Lower-state "local" quanta.
	 * 
	 * @param ERef
	 * @param level
	 * @return the local quanta string value
	 */

	public String getLocalQuanta(MolecularStateType ERef, String level) {
		String result = "";

		if (ERef.getCases().isEmpty())
			return "";
		/* Not handling more than one case */
		if (ERef.getCases().size() > 1)
			return "";
		for (BaseCase quanta : ERef.getCases()) {
			switch (quanta.getCaseID()) {
			/* Group 1: Assymetric rotors */
			case "nltcs":
				result = nltcsCase(quanta);
				break;
			case "asymcs":
				result = asymcsCase(quanta);
				break;
			case "asymos":
				result = asymosCase(quanta);
				break;
			case "nltos":
				result = nltosCase(quanta);
				break;
			/* Group 2: Diatomic and linear molecules */
			case "ltcs":
				result = ltcsCase(quanta, level);
				break;
			case "lpcs":
				result = lpcsCase(quanta, level);
				break;
			case "dcs":
				result = dcsCase(quanta, level);
				break;
			/* Group 3: Spherical rotors */
			case "sphcs":
				result = sphcsCase(quanta);
				break;
			/* Group 4: Symmetric rotor */
			case "stcs":
				result = stcsCase(quanta);
				break;
			/* Group 5: Triplet-Sig ground electronic states */
			case "hundb":
				result = hundbCase(quanta, level);
				break;
			/* Group 6: Douplet-Pi ground electronic states */
			case "hunda":
				result = hundaCase(quanta, level);
				break;
			default:
				throw new IllegalArgumentException("Case not handled: " + quanta.getCaseID());
			}
		}
		return result;
	}

	/**
	 * Converts value into HITRAN units: 1/cm.
	 * 
	 * @param value
	 * @param units
	 * @return value in 1/cm
	 */

	public double getWavenumberHitran(double value, String units) {
		if (units.equals("1/cm"))
			return value;
		else if (units.equals("Hz")) {
			return (value * (1 / c) * 1.0e-2);
		} else if (units.equals("kHz")) {
			return (value * (1 / c) * 1.0e1);
		} else if (units.equals("MHz")) {
			return (value * (1 / c) * 1.0e4);
		} else if (units.equals("GHz")) {
			return (value * (1 / c) * 1.0e7);
		}
		return -1.0;
	}

	/**
	 * Converts intensity into HITRAN units.
	 * 
	 * @param S
	 * @param units
	 * @return intensity value into HITRAN units
	 */

	public double getIntensityHitran(double S, String units) {
		// System.out.println(units + " " + S);
		if (units.equals("1/cm2/atm")) {
			return (S * ((1 * 296) / (L * 273.15)));
		} else if (units.equals("cm2/molecule/cm")) { // HITRAN
			return S;
		}
		else if (units.equals("unitless")) {
			// System.out.print("Warning: Because intensity is unitless, it has
			// not been converted");
			return 0.0;
		}
		return 0.0;
	}

	/**
	 * Gets the molecular species identification (ID) number.
	 * 
	 * @return integer parameter M
	 */

	public int getM() {
		return M;
	}

	/**
	 * Sets the molecular species identification (ID) number.
	 * 
	 * @param M
	 */

	public void setM(int M) {
		this.M = M;
	}

	/**
	 * Gets the isotopologue ID number.
	 * 
	 * @return integer parameter I
	 */

	public int getI() {
		return I;
	}

	/**
	 * Sets the isotopologue ID number.
	 * 
	 * @param I
	 */

	public void setI(int I) {
		this.I = I;
	}

	/**
	 * Gets the wavenumber of the spectral line transition (1/cm) in vacuum.
	 * 
	 * @return wavenumber value in 1/cm
	 */

	public double getVacWn() {
		return vacWn;
	}

	/**
	 * Sets the wavenumber of the spectral line transition (1/cm) in vacuum.
	 * 
	 * @param vacWn
	 *            in 1/cm
	 */

	public void setVacWn(double vacWn) {
		this.vacWn = vacWn;
	}

	/**
	 * Sets uncertainty indices of the wavenumber of the spectral line
	 * transition in vacuum. See Table 5 in HITRAN 2004 edition.
	 * 
	 * param vacWnErr
	 */

	public void setVacWnErr(double vacWnErr) {
		this.vacWnErr = vacWnErr;
	}

	/**
	 * Sets reference indices of the wavenumber of the spectral line transition.
	 * in vacuum.
	 * 
	 * @param vacWnRef
	 */

	public void setVacWnRef(int vacWnRef) {
		this.vacWnRef = vacWnRef;
	}

	/**
	 * Gets the spectral line intensity in cm^{-1}/(molecule.cm^{-2}) at T_{ref}
	 * = 296K.
	 * 
	 * @return the line intensity
	 */

	public double getS() {
		return S;
	}

	/**
	 * Sets the spectral line intensity in cm^{-1}/(molecule.cm^{-2}) at T_{ref}
	 * = 296K.
	 * 
	 * @param S
	 */

	public void setS(double S) {
		this.S = S;
	}

	/**
	 * Sets uncertainty indices of the spectral line intensity. See Table 5 in
	 * HITRAN 2004 edition.
	 * 
	 * @param sErr
	 */

	public void setSErr(double sErr) {
		this.SErr = sErr;
	}

	/**
	 * Sets reference indices of the spectral line intensity.
	 * 
	 * @param sRef
	 */

	public void setSRef(int sRef) {
		this.SRef = sRef;
	}

	/**
	 * Gets the Einstein-A coefficient (s^{-1}) of a transition.
	 * 
	 * @return A coefficient
	 */

	public double getA() {
		return A;
	}

	/**
	 * Sets the Einstein-A coefficient (s^{-1}) of a transition.
	 * 
	 * @param A
	 */

	public void setA(double A) {
		this.A = A;
	}

	/**
	 * Gets the air-broadened half width at half maximum (HWHM) (cm^{-1}/atm) at
	 * T_{ref} = 296K and reference pressure p_{ref} = 1 atm.
	 * 
	 * @return gammaAir value
	 */

	public double getGammaAir() {
		return gammaAir;
	}

	/**
	 * Sets the air-broadened half width at half maximum (HWHM) (cm^{-1}/atm) at
	 * T_{ref} = 296K and reference pressure p_{ref} = 1 atm.
	 * 
	 * @param gammaAir
	 */

	public void setGammaAir(double gammaAir) {
		this.gammaAir = gammaAir;
	}
	
	/**
	 * Gets the air-broadened half width at half maximum (HWHM) (cm^{-1}/atm) uncertainty.
	 * 
	 * @return gammaAir value
	 */

	public double getGammaAirErr() {
		return gammaAirErr;
	}

	/**
	 * Sets the air-broadened half width at half maximum (HWHM) (cm^{-1}/atm) uncertainty.
	 * 
	 * @param gammaAirErr
	 */

	public void setGammaAirErr(double gammaAirErr) {
		this.gammaAirErr = gammaAirErr;
	}

	/**
	 * Sets reference indices of the air-broadened half width at half maximum.
	 * 
	 * @param gammaAirRef
	 */

	public void setGammaAirRef(int gammaAirRef) {
		this.gammaAirRef = gammaAirRef;
	}

	/**
	 * Gets the self-broadened half width at half maximum (HWHM) (cm^{-1}/atm)
	 * at T_{ref} = 296K and reference pressure p_{ref} = 1 atm.
	 * 
	 * @return gammaSelf value
	 */

	public double getGammaSelf() {
		return gammaSelf;
	}

	/**
	 * Sets the self-broadened half width at half maximum (HWHM) (cm^{-1}/atm)
	 * at T_{ref} = 296K and reference pressure p_{ref} = 1 atm.
	 * 
	 * @param gammaSelf
	 */

	public void setGammaSelf(double gammaSelf) {
		this.gammaSelf = gammaSelf;
	}

	/**
	 * Sets uncertainty indices of the self-broadened half width at half
	 * maximum. See Table 5 in HITRAN 2004 edition.
	 * 
	 * @param gammaSelfErr
	 */

	public void setGammaSelfErr(double gammaSelfErr) {
		this.gammaSelfErr = gammaSelfErr;
	}

	/**
	 * Sets reference indices of the self-broadened half width at half maximum.
	 * 
	 * @param gammaSelfRef
	 */

	public void setGammaSelfRef(int gammaSelfRef) {
		this.gammaSelfRef = gammaSelfRef;
	}

	/**
	 * Gets the coefficient of the temperature dependence of the air-broadened.
	 * half width
	 * 
	 * @return the coefficient value nAir
	 */

	public double getnAir() {
		return nAir;
	}

	/**
	 * Sets the coefficient of the temperature dependence of the air-broadened.
	 * half width
	 * 
	 * @param nAir
	 */

	public void setnAir(double nAir) {
		this.nAir = nAir;
	}

	/**
	 * Sets uncertainty indices of the coefficient of the temperature dependence
	 * of the air-broadened. See Table 5 in HITRAN 2004 edition.
	 * 
	 * @param nAirErr
	 */

	public void setnAirErr(double nAirErr) {
		this.nAirErr = nAirErr;
	}

	/**
	 * Sets reference indices of the coefficient of the temperature dependence
	 * of the air-broadened.
	 * 
	 * @param nAirRef
	 */

	public void setnAirRef(int nAirRef) {
		this.nAirRef = nAirRef;
	}

	/**
	 * Gets the pressure shift (cm^{-1}) at T_{ref} = 296K and reference
	 * pressure p_{ref} = 1 atm of the line position with respect to the vacuum
	 * transition wavenumber.
	 * 
	 * @return coefficient deltaAir value
	 */

	public double getDeltaAir() {
		return deltaAir;
	}

	/**
	 * Sets the pressure shift (cm^{-1}) at T_{ref} = 296K and reference
	 * pressure p_{ref} = 1 atm of the line position with respect to the vacuum
	 * transition wavenumber.
	 * 
	 * @param deltaAir
	 */

	public void setDeltaAir(double deltaAir) {
		this.deltaAir = deltaAir;
	}

	/**
	 * Sets uncertainty indices of the pressure shift. See Table 5 in HITRAN
	 * 2004 edition.
	 * 
	 * @param deltaAirErr
	 */

	public void setDeltaAirErr(double deltaAirErr) {
		this.deltaAirErr = deltaAirErr;
	}

	/**
	 * Sets reference indices of the pressure shift.
	 * 
	 * @param deltaAirRef
	 */

	public void setDeltaAirRef(int deltaAirRef) {
		this.deltaAirRef = deltaAirRef;
	}

	/**
	 * Gets the lower-state energy of transition (cm^{-1}).
	 * 
	 * @return the energy eLow
	 */

	public double getELow() {
		return eLow;
	}

	/**
	 * Sets the lower-state energy of transition (cm^{-1}).
	 * 
	 * @param eLow
	 */

	public void setELow(double eLow) {
		this.eLow = eLow;
	}

	/**
	 * Gets upper-state "global" quanta.
	 * 
	 * @return vUp
	 */

	public String getVUp() {
		return vUp;
	}

	/**
	 * Sets upper-state "global" quanta.
	 * 
	 * @param vUp
	 */

	public void setVUp(String vUp) {
		this.vUp = vUp;
	}

	/**
	 * Gets lower-state "global" quanta.
	 * 
	 * @return vLow
	 */

	public String getVLow() {
		return vLow;
	}

	/**
	 * Sets lower-state "global" quanta.
	 * 
	 * @param vLow
	 */

	public void setVLow(String vLow) {
		this.vLow = vLow;
	}

	/**
	 * Gets upper-state "local" quanta.
	 * 
	 * @return qUp
	 */

	public String getQUp() {
		return qUp;
	}

	/**
	 * Sets upper-state "local" quanta.
	 * 
	 * @param qUp
	 */

	public void setQUp(String qUp) {
		this.qUp = qUp;
	}

	/**
	 * Gets lower-state "local" quanta.
	 * 
	 * @return qLow
	 */

	public String getQLow() {
		return qLow;
	}

	/**
	 * Sets lower-state "local" quanta.
	 * 
	 * @param qLow
	 */

	public void setQLow(String qLow) {
		this.qLow = qLow;
	}

	/**
	 * Gets uncertainty indices following the uncertainty codes adopted for
	 * HITRAN. See Table 5 in HITRAN 2004 edition.
	 * 
	 * @return a 6-char string.
	 */

	public String getIerr() {
		int I1, I2, I3, I4, I5, I6;

		if (vacWnErr == 0.0)
			I1 = 0;
		else if (vacWnErr >= 0.1 && vacWnErr < 1.0)
			I1 = 1;
		else if (vacWnErr >= 0.01 && vacWnErr < 0.1)
			I1 = 2;
		else if (vacWnErr >= 0.001 && vacWnErr < 0.01)
			I1 = 3;
		else if (vacWnErr >= 0.0001 && vacWnErr < 0.001)
			I1 = 4;
		else if (vacWnErr >= 0.00001 && vacWnErr < 0.0001)
			I1 = 5;
		else if (vacWnErr < 0.00001)
			I1 = 6;
		else
			I1 = 0;

		/* Code 1 and 2 should not exist from xsams data */
		if (SErr >= 20 && SErr < 100)
			I2 = 3;
		else if (SErr >= 10 && SErr < 20)
			I2 = 4;
		else if (SErr >= 5 && SErr < 10)
			I2 = 5;
		else if (SErr >= 2 && SErr < 5)
			I2 = 6;
		else if (SErr >= 1 && SErr < 2)
			I2 = 7;
		else if (SErr > 0 && SErr < 1)
			I2 = 8;
		else
			I2 = 0;

		/* Code 1 and 2 should not exist from xsams data */
		if (gammaAirErr >= 20 && gammaAirErr < 100)
			I3 = 3;
		else if (gammaAirErr >= 10 && gammaAirErr < 20)
			I3 = 4;
		else if (gammaAirErr >= 5 && gammaAirErr < 10)
			I3 = 5;
		else if (gammaAirErr >= 2 && gammaAirErr < 5)
			I3 = 6;
		else if (gammaAirErr >= 1 && gammaAirErr < 2)
			I3 = 7;
		else if (gammaAirErr > 0 && gammaAirErr < 1)
			I3 = 8;
		else
			I3 = 0;

		/* Code 1 and 2 should not exist from xsams data */
		if (gammaSelfErr >= 20 && gammaSelfErr < 100)
			I4 = 3;
		else if (gammaSelfErr >= 10 && gammaSelfErr < 20)
			I4 = 4;
		else if (gammaSelfErr >= 5 && gammaSelfErr < 10)
			I4 = 5;
		else if (gammaSelfErr >= 2 && gammaSelfErr < 5)
			I4 = 6;
		else if (gammaSelfErr >= 1 && gammaSelfErr < 2)
			I4 = 7;
		else if (gammaSelfErr > 0 && gammaSelfErr < 1)
			I4 = 8;
		else
			I4 = 0;

		/* Code 1 and 2 should not exist from xsams data */
		if (nAirErr >= 20 && nAirErr < 100)
			I5 = 3;
		else if (nAirErr >= 10 && nAirErr < 20)
			I5 = 4;
		else if (nAirErr >= 5 && nAirErr < 10)
			I5 = 5;
		else if (nAirErr >= 2 && nAirErr < 5)
			I5 = 6;
		else if (nAirErr >= 1 && nAirErr < 2)
			I5 = 7;
		else if (nAirErr > 0 && nAirErr < 1)
			I5 = 8;
		else
			I5 = 0;

		if (deltaAirErr == 0.0)
			I6 = 0;
		else if (deltaAirErr >= 0.1 && deltaAirErr < 1.0)
			I6 = 1;
		else if (deltaAirErr >= 0.01 && deltaAirErr < 0.1)
			I6 = 2;
		else if (deltaAirErr >= 0.001 && deltaAirErr < 0.01)
			I6 = 3;
		else if (deltaAirErr >= 0.0001 && deltaAirErr < 0.001)
			I6 = 4;
		else if (deltaAirErr >= 0.00001 && deltaAirErr < 0.0001)
			I6 = 5;
		else if (deltaAirErr < 0.00001)
			I6 = 6;
		else
			I6 = 0;

		iErr = String.format(Locale.ROOT, "%06d", I1 * 100000 + I2 * 10000 + I3 * 1000 + I4 * 100 + I5 * 10 + I6);
		return iErr;
	}

	/**
	 * Gets reference indices for 6 critical parameters (vacWn, S, gammaAir,
	 * gammaSelf, nAir, deltaAir).
	 * 
	 * @return a 12-char string Iref
	 */

	public String getIref() {
		StringBuffer result = new StringBuffer();

		result.append(String.format(Locale.ROOT, "%2d", vacWnRef));
		result.append(String.format(Locale.ROOT, "%2d", SRef));
		result.append(String.format(Locale.ROOT, "%2d", gammaAirRef));
		result.append(String.format(Locale.ROOT, "%2d", gammaSelfRef));
		result.append(String.format(Locale.ROOT, "%2d", nAirRef));
		result.append(String.format(Locale.ROOT, "%2d", deltaAirRef));
		iRef = result.toString();
		return iRef;
	}

	/**
	 * Gets the flat * that represent the availability of program and data for
	 * the case of line mixing.
	 * 
	 * @return the 1-char flag
	 */

	public char getFlag() {
		return flag;
	}

	/**
	 * Gets the upper state statistical weights.
	 * 
	 * @return gUp
	 */

	public double getgUp() {
		return gUp;
	}

	/**
	 * Sets the upper state statistical weights.
	 * 
	 * @param gUp
	 */

	public void setgUp(double gUp) {
		this.gUp = gUp;
	}

	/**
	 * Gets the lower state statistical weights.
	 * 
	 * @return gLow
	 */

	public double getgLow() {
		return gLow;
	}

	/**
	 * Sets the lower state statistical weights.
	 * 
	 * @param gLow
	 */

	public void setgLow(double gLow) {
		this.gLow = gLow;
	}

	/**
	 * Creates a new HitranData instance by initializing all output fields.
	 */

	public HitranData() {
		M = 0;
		I = 1;
		vacWn = vacWnErr = 0.0;
		S = SErr = 0.0;
		A = 0.0;
		gammaAir = gammaAirErr = 0.0;
		gammaSelf = gammaSelfErr = 0.0;
		eLow = 0.0;
		nAir = nAirErr = 0.0;
		deltaAir = deltaAirErr = 0.0;
		vUp = vLow = qUp = qLow = String.format(Locale.ROOT, "%06d", 0);
		iErr = "";
		iRef = String.format(Locale.ROOT, "0 0 0 0 0 0");
		flag = ' ';
		gUp = 0.0;
		gLow = 0.0;

	}
}
