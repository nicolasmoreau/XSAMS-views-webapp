package eu.vamdc.hitran;

import java.util.Locale;

public class WriteOutput {	
	/**
	 * Build value by removing zero before decimal separator.
	 * 
	 * @param value
	 * @param decimalPlaces
	 * @return
	 */
	private static String noZeroFormat(int decimalPlaces, double value) {
		StringBuffer result = new StringBuffer();

		if (value >= 1) {
			throw new IllegalArgumentException("Value must be lower than 1");
		}
		if (value < 0) {
			result.append("-");
		} else if (value == 0) {
			result.append(" ");
		}
		String tmp = String.format(Locale.ROOT, "%." + decimalPlaces + "f", value);
		result.append(tmp.substring(tmp.indexOf('.')));
		return result.toString();
	}


	/**
	 * Print HitranData data into HITRAN format output
	 * 
	 * @param data
	 */

	public static String getHitranAsString(HitranData data){
		StringBuilder result = new StringBuilder();
		result.append(String.format(Locale.ROOT,"%2d", data.getM()));
		result.append(String.format(Locale.ROOT,"%1d", data.getI()));
		result.append(String.format(Locale.ROOT,"%12.6f", data.getVacWn()));
		result.append(String.format(Locale.ROOT,"%10.3e", data.getS()));
		result.append(String.format(Locale.ROOT,"%10.3e", data.getA()));
		result.append(noZeroFormat(4, data.getGammaAir())); 
		result.append(noZeroFormat(4, data.getGammaSelf()));
		result.append(String.format(Locale.ROOT,"%10.4f", data.getELow()));
		result.append(String.format(Locale.ROOT,"%4.2f", data.getnAir()));
		result.append(String.format(Locale.ROOT,"%8.6f", data.getDeltaAir()));
		result.append(String.format(Locale.ROOT,"%15s", data.getVUp()));
		result.append(String.format(Locale.ROOT,"%15s", data.getVLow()));
		result.append(String.format(Locale.ROOT,"%15s", data.getQUp()));
		result.append(String.format(Locale.ROOT,"%15s", data.getQLow()));
		result.append(String.format(Locale.ROOT,"%6s", data.getIerr()));
		result.append(String.format(Locale.ROOT,"%12s", data.getIref()));
		result.append(String.format(Locale.ROOT,"%c", data.getFlag()));
		result.append(String.format(Locale.ROOT,"%7.1f", data.getgUp()));
		result.append(String.format(Locale.ROOT,"%7.1f", data.getgLow()));		
		return result.toString();
	}
}
