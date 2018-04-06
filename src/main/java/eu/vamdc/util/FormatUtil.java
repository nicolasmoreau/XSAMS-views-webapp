package eu.vamdc.util;

import java.util.Locale;

public class FormatUtil {
	public final static double c = 299792458; // speed of light (m/s)
	public final double L = 2.6867774e19; // Loschmidt number (molecule.cm-3)
	
	public static String getFFormat5(double F) {
		String str;

		/* We check if F is integer or half integer */
		if (F != Math.round(F))
			str = String.format(Locale.ROOT, "%5.1f", F);
		else
			str = String.format(Locale.ROOT, "%5.0f", F);

		return str;
	}

	public static String getFFormat4(double F) {
		String str;

		/* We check if F is integer or half integer */
		if (F != Math.round(F))
			str = String.format(Locale.ROOT, "%4.1f", F);
		else
			str = String.format(Locale.ROOT, "%4.0f", F);

		return str;
	}
	
	/**
	 * Converts value into HITRAN units: 1/cm.
	 * 
	 * @param value
	 * @param units
	 * @return value in 1/cm
	 */

	public static double getWavenumberHitran(double value, String units) {
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

	public static double getIntensityHitran(double S, String units) {
		// System.out.println(units + " " + S);
		if (units.equals("1/cm2/atm")) {
			return (S * ((1 * 296) / (L * 273.15)));
		} else if (units.equals("cm2/molecule/cm")) { // HITRAN
			return S;
		} else if (units.equals("unitless")) {
			// System.out.print("Warning: Because intensity is unitless, it has
			// not been converted");
			return 0.0;
		}
		return 0.0;
	}
}
