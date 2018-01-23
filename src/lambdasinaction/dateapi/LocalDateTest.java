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
		// 0 �� ������ today�� today2�� ���� ���ٴ� ���̴�.
		System.out.println(today.compareTo(today2));
		
		System.out.println(today.getYear()+"�� "+
						today.getMonth()+"(����) "+
						today.getMonthValue()+"�� "+
						today.getDayOfMonth()+"��");
		
		System.out.println("����: "+today.getDayOfWeek()+"\n"+
						   "����: "+today.getDayOfWeek().getValue());
		
		//Ư�� ��¥�� �����ؼ� LocalDate ����
		//2018-12-31���� ���� �������� ��ĥ ���Ҵ°�?
		LocalDate endDay = LocalDate.of(2018, 12, 31);
		System.out.println(today.until(endDay, ChronoUnit.DAYS));
		System.out.println("���� ���� 1���� �� :" + today.plus(1, ChronoUnit.MONTHS));
		System.out.println("���� ���� 1���� �� :" + today.plusMonths(1));
		
		//����Ͽ��� 3�� ��?
		System.out.println(DayOfWeek.SATURDAY.plus(3));
		//����Ͽ��� 1�� ��?
		System.out.println(DayOfWeek.SATURDAY.minus(1));
		
		//LocalDate end
		
		
		//LocalTime start
		LocalTime now = LocalTime.now();
		System.out.println(now);
		System.out.println("�ú��� : " + now.getHour() + " : " +
						   now.getMinute() + " : " +
						   now.getSecond() + " : " +
						   now.getNano());
		LocalTime bedTime = LocalTime.of(23, 30);
		LocalTime wakeTime = bedTime.plusHours(7);
		System.out.println("��ħ�ð�: "+bedTime);
		System.out.println("���ð�: "+wakeTime);
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
		
		// ��, ��, ��, ��, ��, ��, ����
		LocalDateTime dt3 =
				LocalDateTime.of(2018, 1, 23, 15, 30, 40, 0);
		System.out.println(dt3);
		//LocalDateTime end
		
		//Formatting start
		//Formatter ����
		LocalDateTime fd = LocalDateTime.now();
		fd.format(DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println(fd.format(DateTimeFormatter.ISO_LOCAL_DATE));
		
		//������ ���� ������ Formatter ����
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy �� MM �� dd �� HH�� mm�� ss", Locale.KOREA);
		fd.format(formatter);
		System.out.println(fd.format(formatter));
		//Formatting end
		
	}

}
