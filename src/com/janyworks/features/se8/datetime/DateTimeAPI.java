/**
 * 
 */
package com.janyworks.features.se8.datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * The new java.time.* APIs are thread safe
 * Instant
 * Duration
 * LocalTime
 * LocalDate
 * LocalDateTime
 * ZonedLocalDateTIme
 * DateTimeFormatter
 * 
 * @author Jany
 *
 */
public class DateTimeAPI {

	public static void main(String[] args) {
		learnInstantDuration();
		learnLocalDateTime();
		learnDateTimeFormatter();
		learnZonedDateTime();
	}
	
	public static void learnInstantDuration() {
		System.out.println("learnInstantDuration");

		Instant start = Instant.now();
		
		System.out.printf("Start Instant: %s%s",start,"\n");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		
		Instant end = Instant.now();
		
		System.out.printf("End Instant: %s%s",end,"\n");
		
		Duration duration = Duration.between(start, end);
		
		System.out.printf("Duration: %s%s",duration,"\n");
		
		System.out.printf("Duration in seconds: %s%s",duration.getSeconds(),"\n");
	}
	
	public static void learnLocalDateTime() {
		System.out.println("learnLocalDateTime");

		LocalDate today = LocalDate.now();
		
		System.out.println("Today: "+today);
		
		LocalDate specificDate = LocalDate.of(2016, 2, 5);
		
		System.out.println("SpecificDate: "+specificDate);
		
		LocalTime timeNow = LocalTime.now();
		
		System.out.println("TimeNow: "+timeNow);
		
		LocalTime specificTime = LocalTime.of(3, 0, 0,0);
		
		System.out.println("SpecificTime: "+specificTime);
		
		LocalDateTime now = LocalDateTime.now();
		
		System.out.println("LocalDateTime now: "+now);
		
		LocalDateTime specificDateTime = LocalDateTime.of(2016,2,5,15, 30, 10,11);
		
		System.out.println("SpecificDateTime: "+specificDateTime);
		
		Period period = Period.between(specificDate, today);
		
		System.out.println("Period: "+period);
		System.out.printf("Period: %d years - %d months - %d days%s",period.getYears(),period.getMonths(),period.getDays(),"\n");
	}
	public static void learnDateTimeFormatter() {
		System.out.println("learnDateTimeFormatter");
		
		LocalDate currentDate = LocalDate.now();
		
		System.out.println("Formatted Date: "+DateTimeFormatter.ISO_DATE.format(currentDate));
		
		LocalTime currentTime = LocalTime.now();
		
		System.out.println("Formatted Time: "+DateTimeFormatter.ISO_TIME.format(currentTime));
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		System.out.println("Formatted DateTime: "+DateTimeFormatter.ISO_DATE_TIME.format(currentDateTime));
		
		System.out.println("Short Formatted DateTime: "+DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(currentDateTime));
		
		DateTimeFormatter mediumFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		
		System.out.println("Medium French Formatted DateTime: "+mediumFormatter.withLocale(Locale.FRENCH).format(currentDateTime));
		
		DateTimeFormatterBuilder b = new DateTimeFormatterBuilder().appendValue(ChronoField.MONTH_OF_YEAR).appendLiteral("$")
				.appendValue(ChronoField.DAY_OF_MONTH).appendLiteral("$").appendValue(ChronoField.YEAR);
		System.out.println("Custom Fomat: "+b.toFormatter().format(currentDateTime));
	}
	
	public static void learnZonedDateTime() {
		System.out.println("learnZonedDateTime");
		
		ZonedDateTime currentZDateTime = ZonedDateTime.now();
		
		System.out.println("ZonedDateTime : "+currentZDateTime);
		
		ZonedDateTime gmtDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("GMT+0"));
		
		System.out.println("GMT DateTime : "+gmtDateTime);
		
		ZonedDateTime nYDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("America/New_York"));
		
		System.out.println("NY DateTime : "+nYDateTime);
		
		ZoneId.getAvailableZoneIds().forEach(z->System.out.println(z+" "+ZonedDateTime.of(LocalDateTime.now(),ZoneId.of(z))));
		
		
	}
}