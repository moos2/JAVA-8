package lambdasinaction.dateapi;

import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.*;

import java.time.DayOfWeek;
//마트 휴일
public class TheMartDayOffUsingLambda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate today = LocalDate.now();
		
		LocalDate dayOffDate = today.with(temporal -> {
			//1. temporal로부터 기준이 되는 날짜를 구한다.
			LocalDate theDay = LocalDate.from(temporal);
			
			//2. 둘째, 넷째 일요일을 구한다.
			LocalDate secondSunday = theDay.with(dayOfWeekInMonth(2, DayOfWeek.SUNDAY));
			LocalDate fourthSunday = theDay.with(dayOfWeekInMonth(4, DayOfWeek.SUNDAY));
			
			//3. 기준날짜와 쉬는 날을 비교해준다.
			// 2번째 일요일보다 기준일이 이전이면, 쉬는날을 2번째 일요일로 리턴
			// 4번째 일요일보다 기준일이 이전이면, 쉬는날을 4번째 일요일로 리턴
			// 4번째 일요일보다 기준일이 지났으면, 쉬는날을 다음달 2번째 일요일로 리턴
			if(theDay.isBefore(secondSunday)) {
				return secondSunday;
			}else if(theDay.isBefore(fourthSunday)) {
				return fourthSunday;
			}else {
				return theDay.plusMonths(1).with(dayOfWeekInMonth(2, DayOfWeek.SUNDAY));
			}
		});
		
		System.out.println("오늘 기준 다음 마트 쉬는 날은 " + dayOffDate + "일 입니다.");
	}

}




































