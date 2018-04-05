package eu.vamdc.util;

import java.util.Locale;

public class FormatUtil {
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
}
