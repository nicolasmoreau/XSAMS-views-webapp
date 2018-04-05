package eu.vamdc.cases;

import java.util.List;
import java.util.Locale;

import org.vamdc.xsams.cases.common.RankingType;
import org.vamdc.xsams.cases.common.VibrationalQNType;

public class CaseUtil {
	
	public final static String LOWER_LEVEL = "lower";
	public final static String UPPER_LEVEL = "upper";	
	
	public static String getSpecialGlobalQString(List<VibrationalQNType> VibQN) {
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
	 * Gets the type of the branch. Other values are not allowed
	 * 
	 * @param X1
	 * @param X2
	 * @return the type of the branch: O, P, Q, R or S
	 */

	public static String getBranchName(Double X1, Double X2) {
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
	
	public static Integer getRankingValue(List<RankingType> RS, String rankingName) {
		for (RankingType ranking : RS) {
			if (ranking.getName().equals(rankingName))
				return ranking.getValue();
		}
		return 0;
	}
}
