package com.akulovs.CurrencyConverter.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import static com.akulovs.CurrencyConverter.util.CurrencyConverterConstants.SUPPORTED_DATE_FORMAT;

public class CurrencyConverterUtil {

	public static String getFormattedDate(Date dateToFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(SUPPORTED_DATE_FORMAT);
		return Optional.ofNullable(dateToFormat).map(sdf::format).orElse(null);
	}

	public static String getFormattedAmount(BigDecimal amountToFormat) {
		return Optional.ofNullable(amountToFormat).map((p) -> {
			p = p.setScale(2, RoundingMode.HALF_UP);
			return String.format("%1$17s", new DecimalFormat("0.00").format(p));
		}).orElse(null);
	}

	public static String getFormattedDateTime(Date dateToFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return Optional.ofNullable(dateToFormat).map(sdf::format).orElse(null);
	}

}
