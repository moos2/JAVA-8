package lambdasinaction.dateapi;

import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.*;

import java.time.DayOfWeek;
public class TemporalAdjustersTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LocalDate d = LocalDate.now();
		LocalDate secondFriday = d.with(dayOfWeekInMonth(2, DayOfWeek.FRIDAY));
		
		System.out.println("두번째 금요일: " + secondFriday);
		System.out.println("다음달의 첫번째 날짜: " + d.with(firstDayOfNextMonth()));
		System.out.println("첫번째 목요일의 날짜: " + d.with(firstInMonth(DayOfWeek.THURSDAY)));
		System.out.println("이번달의 마지막날: " + d.with(lastDayOfMonth()));
		System.out.println("이번달 마지막 목요일의 날짜: " + d.with(lastInMonth(DayOfWeek.THURSDAY)));
		System.out.println("오늘 기준 다음번 수요일의 날짜: " + d.with(next(DayOfWeek.WEDNESDAY)));
		System.out.println("오늘 기준 저번 화요일의 날짜: " + d.with(previous(DayOfWeek.TUESDAY)));
	}

}
