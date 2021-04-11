package com.antonov.date;

import java.util.Calendar;

public class DateConverter {

	public static final int LOCALE_RUS = 1;

	public static String getDayOfWeekName(int dayOfWeek, int locale) {
		switch (dayOfWeek + 1) {
		case Calendar.MONDAY:
			return "Понедельник";
		case Calendar.TUESDAY:
			return "Вторник";
		case Calendar.WEDNESDAY:
			return "Среда";
		case Calendar.THURSDAY:
			return "Четверг";
		case Calendar.FRIDAY:
			return "Пятница";
		case Calendar.SATURDAY:
			return "Субота";
		case Calendar.SUNDAY:
			return "Воскресенье";

		default:
			return null;
		}
	}

	public static String getMonthName(int month, int locale) {
		switch (month) {
		case Calendar.JANUARY:
			return "Январь";
		case Calendar.FEBRUARY:
			return "Февраль";
		case Calendar.MARCH:
			return "Март";
		case Calendar.APRIL:
			return "Апрель";
		case Calendar.MAY:
			return "Май";
		case Calendar.JUNE:
			return "Июнь";
		case Calendar.JULY:
			return "Июль";
		case Calendar.AUGUST:
			return "Август";
		case Calendar.SEPTEMBER:
			return "Сентябрь";
		case Calendar.OCTOBER:
			return "Октябрь";
		case Calendar.NOVEMBER:
			return "Ноябрь";
		case Calendar.DECEMBER:
			return "Декабрь";

		default:
			return null;
		}
	}
}
