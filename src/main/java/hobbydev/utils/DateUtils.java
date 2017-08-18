package hobbydev.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public final class DateUtils {
	
	public static Date toDate(LocalDate localDate) {
		return toDate(localDate, ZoneOffset.UTC);
	}
	
	public static Date toDate(LocalDate localDate, ZoneOffset zoneOffset) {
		return toDate(localDate, ZoneId.ofOffset("", zoneOffset));
	}
	
	public static Date toDate(LocalDate localDate, ZoneId zoneId) {
		return Date.from(localDate.atStartOfDay(zoneId).toInstant());
	}
	
	public static LocalDate toLocalDate(Date date) {
		return toLocalDate(date, ZoneOffset.UTC);
	}
	
	public static LocalDate toLocalDate(Date date, ZoneOffset zoneOffset) {
		return toLocalDate(date, ZoneId.ofOffset("", zoneOffset));
	}
	
	public static LocalDate toLocalDate(Date date, ZoneId zoneId) {
		return 	date.toInstant().atZone(zoneId).toLocalDate();
	}
}
