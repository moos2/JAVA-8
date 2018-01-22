package lambdasinaction._02stream.basic2;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import lambdasinaction._02stream.basic1.Dish;

public class _03Mapping {

    public static void main(String...args){

        //1. map -Dish의 name 목록만
    	List<String> dishNames = Dish.menu
    								 .stream()
    								 .map(Dish::getName)
    								 .collect(Collectors.toList())
    								 ;
    	dishNames.forEach(System.out::println);
    	
    	//칼로리 합계 계산
    	int sumCalory = Dish.menu
    						.stream()
    						.map(Dish::getCalories)
    						.reduce((prev, curr)-> prev + curr)
    						.get()
    						;
    	System.out.println(sumCalory);
    	
    	//자바스크립트는 reduce에서 초기값을 function 뒤에 준다.
    	//ex) reduce( (x,y) => x+y, 0)
    	sumCalory = Dish.menu
    					.stream()
    					.map(Dish::getCalories)
    					.reduce(0, (prev, curr) -> prev + curr)
    					;
    	System.out.println(sumCalory);
    	
        // map 
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                                         .map(String::length)
                                         .collect(toList());
        System.out.println(wordLengths);

        //2. map - 중복된 문자 제거한 word 리스트


        //3.flatMap  - 중복된 문자 제거가 word 리스트




        // flatMap
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs =
                        numbers1.stream()
                                .flatMap((Integer i) -> numbers2.stream()
                                                       .map((Integer j) -> new int[]{i, j})
                                 )
                                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                                .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}
