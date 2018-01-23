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
    	
        //reduce - - reduce를 사용하여 sum 을 구하는 방법
        int sum1 = numbers.stream()
        				  .reduce(0, (a,b) -> a + b);
        System.out.println("1번 sum :" + sum1);
        sum1 = numbers.stream()
        			  .reduce(0, Integer::sum);
        			//.reduce(0, (a,b) -> Integer.sum(a,b))
        System.out.println("2번 sum :" + sum1);
        
        //reduce를 사용하여 최소값 구하는 방법
        //reduce에 초기값 0을 줬기 때문에 타입이 int가 된다.
        int min1 = numbers.stream()
        			  	  .reduce(0, Integer::min);
        
        //reduce에 초기값을 주지 않으면
        //타입이 Optional<T>이 된다.
        Optional<Integer> minVal= numbers.stream()
        						   .reduce(Integer::min);
        
        System.out.println("최소값 구하기: "+minVal.get());
        minVal.ifPresent(System.out::println);
        
        //reduce를 사용하여 최대값 구하는 방법

        //칼로리 합계를 구하는 여러가지 방법
        //1. reduce 합수 직접 구현
        int totalCalory= Dish.menu.stream()
				        		  .map(Dish::getCalories)
				        		  .reduce((a, b) -> a + b)
				        		  .get();
        System.out.println(totalCalory);
        
        //2. reduce 함수에서 Integer.sum을 호출
        totalCalory =
        		Dish.menu.stream()
        				 .map(d -> d.getCalories())
        				 .reduce(0, Integer::sum);
        System.out.println(totalCalory);
        
        //3. reduce() 대신 mapToInt() 함수 쓰기
        totalCalory = 
        		Dish.menu.stream()
        				 .mapToInt(dish -> dish.getCalories())
        				 .sum();
        System.out.println(totalCalory);
        
        //집계함수들을 한꺼번에 제공하는 summaryStatistics()를 사용
        //range()는 마지막값을 포함 x
        //rangeClosed()는 마지막 값을 포함 o
        System.out.println("IntStream: "+IntStream.rangeClosed(1, 100)
        		 .summaryStatistics());
    }
}
