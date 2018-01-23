package lambdasinaction._02stream.basic2;
import java.util.Optional;

import lambdasinaction._02stream.basic1.Dish;
import static lambdasinaction._02stream.basic1.Dish.menu;

public class _04Finding {

    public static void main(String...args){
        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }
        
        System.out.println("allMatch :"+isHealthyMenu());
        System.out.println("noneMatch :"+isHealthyMenu2());
        
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    //1. anyMatch
    private static boolean isVegetarianFriendlyMenu(){
        return menu.stream()
        		   .anyMatch(Dish::isVegetarian)
        		   ;
    }
    //2.allMatch
    private static boolean isHealthyMenu(){
    	//만약 400보다 작거나 같다는 식으로 바꾼다면,
    	//Dish.menu의 모든 Data들이 조건에 만족하는 것이 아니기 때문에
    	//false를 리턴한다.
        return menu.stream()
        		   .allMatch(d -> d.getCalories() <= 800)
        		   ;
    }

    //3. noneMatch
    private static boolean isHealthyMenu2(){
    	//Dish.menu의 모든 data들이 조건에 만족하지 않으므로
    	//true를 리턴한다.
        return menu.stream()
     		   .noneMatch(d -> d.getCalories() > 800)
     		   ;
    }
    //4. findAny
    private static Optional<Dish> findVegetarianDish(){

        return menu.stream()
        		   .filter(Dish::isVegetarian)
        		   .findAny()
        		   ;
    }
    
}
