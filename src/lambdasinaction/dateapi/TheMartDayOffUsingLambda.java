package lambdasinaction.dateapi;

import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.*;

import java.time.DayOfWeek;
//��Ʈ ����
public class TheMartDayOffUsingLambda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate today = LocalDate.now();
		
		LocalDate dayOffDate = today.with(temporal -> {
			//1. temporal�κ��� ������ �Ǵ� ��¥�� ���Ѵ�.
			LocalDate theDay = LocalDate.from(temporal);
			
			//2. ��°, ��° �Ͽ����� ���Ѵ�.
			LocalDate secondSunday = theDay.with(dayOfWeekInMonth(2, DayOfWeek.SUNDAY));
			LocalDate fourthSunday = theDay.with(dayOfWeekInMonth(4, DayOfWeek.SUNDAY));
			
			//3. ���س�¥�� ���� ���� �����ش�.
			// 2��° �Ͽ��Ϻ��� �������� �����̸�, ���³��� 2��° �Ͽ��Ϸ� ����
			// 4��° �Ͽ��Ϻ��� �������� �����̸�, ���³��� 4��° �Ͽ��Ϸ� ����
			// 4��° �Ͽ��Ϻ��� �������� ��������, ���³��� ������ 2��° �Ͽ��Ϸ� ����
			if(theDay.isBefore(secondSunday)) {
				return secondSunday;
			}else if(theDay.isBefore(fourthSunday)) {
				return fourthSunday;
			}else {
				return theDay.plusMonths(1).with(dayOfWeekInMonth(2, DayOfWeek.SUNDAY));
			}
		});
		
		System.out.println("���� ���� ���� ��Ʈ ���� ���� " + dayOffDate + "�� �Դϴ�.");
	}

}




































