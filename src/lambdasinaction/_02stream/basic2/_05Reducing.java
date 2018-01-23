package lambdasinaction._02stream.basic2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import lambdasinaction._02stream.basic1.Dish;


public class _05Reducing {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
    	
        //reduce - - reduce�� ����Ͽ� sum �� ���ϴ� ���
        int sum1 = numbers.stream()
        				  .reduce(0, (a,b) -> a + b);
        System.out.println("1�� sum :" + sum1);
        sum1 = numbers.stream()
        			  .reduce(0, Integer::sum);
        			//.reduce(0, (a,b) -> Integer.sum(a,b))
        System.out.println("2�� sum :" + sum1);
        
        //reduce�� ����Ͽ� �ּҰ� ���ϴ� ���
        //reduce�� �ʱⰪ 0�� ��� ������ Ÿ���� int�� �ȴ�.
        int min1 = numbers.stream()
        			  	  .reduce(0, Integer::min);
        
        //reduce�� �ʱⰪ�� ���� ������
        //Ÿ���� Optional<T>�� �ȴ�.
        Optional<Integer> minVal= numbers.stream()
        						   .reduce(Integer::min);
        
        System.out.println("�ּҰ� ���ϱ�: "+minVal.get());
        minVal.ifPresent(System.out::println);
        
        //reduce�� ����Ͽ� �ִ밪 ���ϴ� ���

        //Į�θ� �հ踦 ���ϴ� �������� ���
        //1. reduce �ռ� ���� ����
        int totalCalory= Dish.menu.stream()
				        		  .map(Dish::getCalories)
				        		  .reduce((a, b) -> a + b)
				        		  .get();
        System.out.println(totalCalory);
        
        //2. reduce �Լ����� Integer.sum�� ȣ��
        totalCalory =
        		Dish.menu.stream()
        				 .map(d -> d.getCalories())
        				 .reduce(0, Integer::sum);
        System.out.println(totalCalory);
        
        //3. reduce() ��� mapToInt() �Լ� ����
        totalCalory = 
        		Dish.menu.stream()
        				 .mapToInt(dish -> dish.getCalories())
        				 .sum();
        System.out.println(totalCalory);
        
        //�����Լ����� �Ѳ����� �����ϴ� summaryStatistics()�� ���
        //range()�� ���������� ���� x
        //rangeClosed()�� ������ ���� ���� o
        System.out.println("IntStream: "+IntStream.rangeClosed(1, 100)
        		 .summaryStatistics());
    }
}
