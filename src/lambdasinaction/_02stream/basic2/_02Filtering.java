package lambdasinaction._02stream.basic2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lambdasinaction._02stream.basic1.Dish;

public class _02Filtering {

    public static void main(String...args){

        // 1. Filtering with predicate ( isVegeterian() )
    	List<Dish> vegeList = Dish.menu
    							  .stream()
    							  .filter(Dish::isVegetarian)
    							  .collect(Collectors.toList())
    							  ;
    	vegeList.forEach(System.out::println);

        // 2. Filtering unique elements
    	List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
    	numbers.stream()
    		   .filter(x -> x % 2 == 0)
    		   .distinct()
    	       .forEach(System.out::println)
    		   ;
    	
    	

        //3. Truncating 3 stream ( d.getCalories() > 300 )
    	List<Dish> dishesLimit3 = Dish.menu
    								  .stream()
    								  .filter(x -> x.getCalories() > 300)
    								  .limit(3)
    								  .collect(Collectors.toList())
    								  ;
    	
    	System.out.println(dishesLimit3);
    	

        //4. Skipping 2 elements
    	List<Dish> dishesSkip2 = Dish.menu
    								 .stream()
    								 .filter(x -> x.getCalories() > 300)
    								 .skip(2)
    								 .collect(Collectors.toList())
    								 ;
    	System.out.println(dishesSkip2);
    }
}
