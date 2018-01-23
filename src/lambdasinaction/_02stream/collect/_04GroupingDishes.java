package lambdasinaction._02stream.collect;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;
import static lambdasinaction._02stream.collect.Dish.menu;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class _04GroupingDishes {

    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String ... args) {
        System.out.println("Dishes grouped by type: " + groupDishesByType());
        System.out.println("Dishes grouped by caloric level: " + groupDishesByCaloricLevel());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());
        System.out.println("Count dishes in groups: " + countDishesInGroups());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByType());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByTypeWithoutOptionals());
        System.out.println("Sum calories by type: " + sumCaloriesByType());
        System.out.println("Caloric levels by type: " + caloricLevelsByType());
    }

    //1. type별 그룹핑
    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return menu.stream()
        		   .collect(Collectors.groupingBy(Dish::getType));
    }
    //2. 칼로리별 그룹핑
    //groupingBy 안의 람다식의 인자 d는 Dish.menu이다.
    //return 타입은 Map<CaloricLevel, List<Dish>>인데,
    //Key부분의 enum타입 CaloricLevel이 전부 정의되지 않으면 오류가 난다.
    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        return menu.stream()
        		   .collect(groupingBy(
        				   d -> {
        					   if(d.getCalories() <= 400) return CaloricLevel.DIET;
        					   else if(d.getCalories() <= 700) return CaloricLevel.NORMAL;
        					   else return CaloricLevel.FAT;
        				   }
        				   ));
    }
    //3. type별로 그룹핑 후에 다시 칼로리별로 그룹핑
    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        //groupingBy(type, groupingBy())
    	//groupinBy() 안에 또 다른 groupingBy()
    	return menu.stream()
    			   .collect(groupingBy(d -> d.getType(), 
    					   groupingBy(d -> {
    						   if(d.getCalories() <= 400) return CaloricLevel.DIET;
    						   else if(d.getCalories() <= 700) return CaloricLevel.NORMAL;
    						   else return CaloricLevel.FAT;
    					   })
    					   ));
    }
    //4. type별 갯수 카운팅
    private static Map<Dish.Type, Long> countDishesInGroups() {
        return menu.stream()
        		   .collect(groupingBy(Dish::getType, counting()));
    }
    //5. type별 그룹에서 가장 칼로리가 높은 Dish 찾기
    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        // reducing((d1, d2) -> d1.getCal() > d2.getCal() ? d1 : d2)
    	return menu.stream()
    			   .collect(groupingBy(Dish::getType, 
    					   reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)))
    			   ;
    }
    //5.1 type별 그룹에서 가장 칼로리가 높은 Dish 찾기 - collectingAndThen() 사용
    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptionals() {
        return menu.stream().collect(groupingBy(x -> x.getType(), 
        										collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)))
        		   ;
        		    
    }

    //6. type별로 그룹핑하여 칼로리의 합계 내기
    private static Map<Dish.Type, Integer> sumCaloriesByType() {
        return menu.stream()
        		   .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)))
        		   ;
    }
    
    //7. type별로 그룹핑 한 후에 CaloricLevel 종류를 set 형태로 반환
    // Map<Dish.Type, Set<CaloricLevel>>에서 value 타입이 List<CaloricLevel>이면
    // toSet() 대신에 toList()를 사용한다. 
    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType, mapping(
                        dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                        else return CaloricLevel.FAT; },
                        toSet() )))
        		;
    }
}
