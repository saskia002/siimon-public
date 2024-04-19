package siimon.core.api.shared.util;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtil {

	public static String InstantToString(Instant instant) {
		return DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
				.withZone(ZoneId.of("UTC"))
				.format(instant);
	}

	public static String localDateToString(LocalDate localDate) {
		return DateTimeFormatter.ofPattern("dd.MM.yyyy")
				.format(localDate);
	}

	public static Instant stringToInstant(String date) {
		return LocalDate.parse(date)
				.atTime(LocalTime.of(0, 0))
				.atZone(ZoneId.of("UTC"))
				.toInstant();
	}

	public static LocalTime stringToLocalTime(String time) {
		return LocalTime.parse(time);
	}

	public static String localTimeToString(LocalTime localTime) {
		return DateTimeFormatter.ofPattern("HH:mm:ss")
				.format(localTime);
	}

}
