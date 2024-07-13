package utililties;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DateManager {

	private static SimpleDateFormat sdf = null;

	public static Calendar getDateAfterDaysOut(Integer daysOut) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, daysOut);
		return calendar;
	}

	public static String getDaysOut(Integer daysOut, String desiredFormat) {
		Calendar currentDate = getDateAfterDaysOut(daysOut);
		sdf = new SimpleDateFormat(desiredFormat);
		return sdf.format(currentDate.getTime());
	}

	public static String getMonthByMonthsOut(Integer monthsOut, String desiredFormat) {
		Calendar currentDate = getDateAfterDaysOut(0);
		sdf = new SimpleDateFormat(desiredFormat);
		currentDate.add(Calendar.MONTH, monthsOut);
		return sdf.format(currentDate.getTime());

	}

	public static String getStartEndWeekDate(Integer weeksOut, String format) {
		Calendar currentDate = getDateAfterDaysOut(0);
		sdf = new SimpleDateFormat(format);
		currentDate.add(Calendar.WEEK_OF_MONTH, weeksOut);
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String startDate = sdf.format(currentDate.getTime());
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		String endDate = sdf.format(currentDate.getTime());
		String startEndWeekDate = startDate + " – " + endDate;
		return startEndWeekDate;
	}

	public static String getDayNameForDate(String dayDate, String format) {
		Date date;
		String dayName = null;
		try {
			sdf = new SimpleDateFormat(format);
			date = sdf.parse(dayDate);
			sdf = new SimpleDateFormat("EEEE");
			dayName = sdf.format(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dayName;

	}

	public static String getFirstAndLastDateForCurrentMonth(String dateFormat) {

		Date today = new Date();

		Calendar cal = Calendar.getInstance();

		cal.setTime(today);

		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));

		Date firstDayOfTheMonth = cal.getTime();

		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		Date lastDayOfMonth = cal.getTime();

		sdf = new SimpleDateFormat(dateFormat);

		return sdf.format(firstDayOfTheMonth) + "-" + sdf.format(lastDayOfMonth);

	}

	public static String getDateBasedOnDayNameForCurrentWeek(String dayName, String format) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);

		String dayDate = null;

		switch (dayName) {

		case "Sunday": {

			dayDate = dtf.format(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY)));

			break;

		}

		case "Monday": {

			dayDate = dtf.format(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)));

			break;

		}

		case "Tuesday": {

			dayDate = dtf.format(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY)));

			break;

		}

		case "Wednesday": {

			dayDate = dtf.format(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY)));

			break;

		}

		case "Thursday": {

			dayDate = dtf.format(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY)));

			break;

		}

		case "Friday": {

			dayDate = dtf.format(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));

			break;

		}

		case "Saturday": {

			dayDate = dtf.format(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY)));

			break;

		}

		}

		return dayDate;

	}

	public static ArrayList<String> getAllDateBetweenDateRange(int startDaysOut, int endDaysOut, String dateFormat) {

		ArrayList<String> dates = new ArrayList<String>();
		for (int i = startDaysOut; i <= endDaysOut; i++) {
			dates.add(getDaysOut(i, dateFormat));
		}
		return dates;
	}

	public static String getTimestamp(String format) {
		// format: example like-yyyy-MM-dd HH:mm:ss
		sdf = new SimpleDateFormat(format);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String value = sdf.format(timestamp);
		return value;
	}

	public static HashMap calculateDaysOut(Integer daysOut) {
		final HashMap<String, String> finalDate = new HashMap<>();
		final Calendar calendar = getDateAfterDaysOut(daysOut);
		finalDate.put("YEAR", Integer.toString(calendar.get(Calendar.YEAR)));
		String monthName = (new SimpleDateFormat("MMM").format(calendar.getTime()));
		monthName = monthName.substring(0, 3).toUpperCase();
		finalDate.put("MONTH", monthName);
		finalDate.put("DATE", Integer.toString(calendar.get(Calendar.DATE)));
		return finalDate;
	}
}
