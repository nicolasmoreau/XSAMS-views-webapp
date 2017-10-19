package eu.vamdc.hitran;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class WriteOutputFile {
	private PrintWriter file;
	
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
	 * display content of XSAMSData object in console output. Should be use only
	 * for tests
	 * 
	 * @param data
	 */
	protected void display(HitranData data) {
		System.out.print(String.format(Locale.ROOT,"%2d", data.getM()));
		System.out.print(String.format(Locale.ROOT,"%1d", data.getI()));
		System.out.print(String.format(Locale.ROOT,"%12.6f", data.getVacWn()));
		System.out.print(String.format(Locale.ROOT,"%10.3e", data.getS()));
		System.out.print(String.format(Locale.ROOT,"%10.3e", data.getA()));
		System.out.print(noZeroFormat(4, data.getGammaAir()));
		System.out.print(noZeroFormat(4, data.getGammaSelf()));
		System.out.print(String.format(Locale.ROOT,"%10.4f", data.getELow()));
		System.out.print(String.format(Locale.ROOT,"%4.2f", data.getnAir()));
		System.out.print(String.format(Locale.ROOT,"%8.6f", data.getDeltaAir()));
		System.out.print(String.format(Locale.ROOT,"%15s", data.getVUp()));
		System.out.print(String.format(Locale.ROOT,"%15s", data.getVLow()));
		System.out.print(String.format(Locale.ROOT,"%15s", data.getQUp()));
		System.out.print(String.format(Locale.ROOT,"%15s", data.getQLow()));
		System.out.print(String.format(Locale.ROOT,"%6s", data.getIerr()));
		System.out.print(String.format(Locale.ROOT,"%12s", data.getIref()));
		System.out.print(String.format(Locale.ROOT,"%c", data.getFlag()));
		System.out.print(String.format(Locale.ROOT,"%7.1f", data.getgUp()));
		System.out.println(String.format(Locale.ROOT,"%7.1f", data.getgLow()));
	}

	/**
	 * Open the output file
	 * 
	 * @param fOutput
	 */

	public void openFile(String fOutput) {
		try {
			File output = new File(fOutput);
			file = new PrintWriter(new FileWriter(output));
			System.out.println("Creating File '" + fOutput + "'...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Close the output file
	 * 
	 * @throws IOException
	 */

	public void closeFile() throws IOException {
		if (file != null)
			file.close();
	}

	/**
	 * Print HitranData data into HITRAN format output
	 * 
	 * @param data
	 */

	public void writeFile(HitranData data) {
		file.print(String.format(Locale.ROOT,"%2d", data.getM()));
		file.print(String.format(Locale.ROOT,"%1d", data.getI()));
		file.print(String.format(Locale.ROOT,"%12.6f", data.getVacWn()));
		file.print(String.format(Locale.ROOT,"%10.3e", data.getS()));
		file.print(String.format(Locale.ROOT,"%10.3e", data.getA()));
		file.print(noZeroFormat(4, data.getGammaAir())); 
		file.print(noZeroFormat(4, data.getGammaSelf()));
		file.print(String.format(Locale.ROOT,"%10.4f", data.getELow()));
		file.print(String.format(Locale.ROOT,"%4.2f", data.getnAir()));
		file.print(String.format(Locale.ROOT,"%8.6f", data.getDeltaAir()));
		file.print(String.format(Locale.ROOT,"%15s", data.getVUp()));
		file.print(String.format(Locale.ROOT,"%15s", data.getVLow()));
		file.print(String.format(Locale.ROOT,"%15s", data.getQUp()));
		file.print(String.format(Locale.ROOT,"%15s", data.getQLow()));
		file.print(String.format(Locale.ROOT,"%6s", data.getIerr()));
		file.print(String.format(Locale.ROOT,"%12s", data.getIref()));
		file.print(String.format(Locale.ROOT,"%c", data.getFlag()));
		file.print(String.format(Locale.ROOT,"%7.1f", data.getgUp()));
		file.println(String.format(Locale.ROOT,"%7.1f", data.getgLow()));
	}
	
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
