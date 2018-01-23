package lambdasinaction.dateapi;

import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.*;

import java.time.DayOfWeek;
public class TemporalAdjustersTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LocalDate d = LocalDate.now();
		LocalDate secondFriday = d.with(dayOfWeekInMonth(2, DayOfWeek.FRIDAY));
		
		System.out.println("�ι�° �ݿ���: " + secondFriday);
		System.out.println("�������� ù��° ��¥: " + d.with(firstDayOfNextMonth()));
		System.out.println("ù��° ������� ��¥: " + d.with(firstInMonth(DayOfWeek.THURSDAY)));
		System.out.println("�̹����� ��������: " + d.with(lastDayOfMonth()));
		System.out.println("�̹��� ������ ������� ��¥: " + d.with(lastInMonth(DayOfWeek.THURSDAY)));
		System.out.println("���� ���� ������ �������� ��¥: " + d.with(next(DayOfWeek.WEDNESDAY)));
		System.out.println("���� ���� ���� ȭ������ ��¥: " + d.with(previous(DayOfWeek.TUESDAY)));
	}

}
