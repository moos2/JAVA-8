package lambdasinaction.dateapi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class LocalDateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//LocalDate start
		
		LocalDate today = LocalDate.now();
		System.out.println("today : "+today);
		
		LocalDate today2 = LocalDate.now();
		// 0 이 나오면 today와 today2의 값이 같다는 뜻이다.
		System.out.println(today.compareTo(today2));
		
		System.out.println(today.getYear()+"년 "+
						today.getMonth()+"(영문) "+
						today.getMonthValue()+"월 "+
						today.getDayOfMonth()+"일");
		
		System.out.println("영문: "+today.getDayOfWeek()+"\n"+
						   "숫자: "+today.getDayOfWeek().getValue());
		
		//특정 날짜를 지정해서 LocalDate 생성
		//2018-12-31까지 현재 기준으로 며칠 남았는가?
		LocalDate endDay = LocalDate.of(2018, 12, 31);
		System.out.println(today.until(endDay, ChronoUnit.DAYS));
		System.out.println("현재 기준 1개월 후 :" + today.plus(1, ChronoUnit.MONTHS));
		System.out.println("현재 기준 1개월 후 :" + today.plusMonths(1));
		
		//토요일에서 3일 후?
		System.out.println(DayOfWeek.SATURDAY.plus(3));
		//토요일에서 1일 전?
		System.out.println(DayOfWeek.SATURDAY.minus(1));
		
		//LocalDate end
		
		
		//LocalTime start
		LocalTime now = LocalTime.now();
		System.out.println(now);
		System.out.println("시분초 : " + now.getHour() + " : " +
						   now.getMinute() + " : " +
						   now.getSecond() + " : " +
						   now.getNano());
		LocalTime bedTime = LocalTime.of(23, 30);
		LocalTime wakeTime = bedTime.plusHours(7);
		System.out.println("취침시간: "+bedTime);
		System.out.println("기상시간: "+wakeTime);
		//LocalTime end
		
		//LocalDateTime start
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt);
		
		LocalDate d = dt.toLocalDate();
		System.out.println(d);
		
		LocalTime t = dt.toLocalTime();
		System.out.println(t);
		
		LocalDateTime dt2 = 
				LocalDateTime.of(LocalDate.now(), LocalTime.now());
		System.out.println(dt2);
		
		// 년, 월, 일, 시, 분, 초, 나노
		LocalDateTime dt3 =
				LocalDateTime.of(2018, 1, 23, 15, 30, 40, 0);
		System.out.println(dt3);
		//LocalDateTime end
		
		//Formatting start
		//Formatter 생성
		LocalDateTime fd = LocalDateTime.now();
		fd.format(DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println(fd.format(DateTimeFormatter.ISO_LOCAL_DATE));
		
		//포맷을 직접 지정한 Formatter 생성
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy 년 MM 월 dd 일 HH시 mm분 ss", Locale.KOREA);
		fd.format(formatter);
		System.out.println(fd.format(formatter));
		//Formatting end
		
	}

}
