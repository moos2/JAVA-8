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
    	//���� 400���� �۰ų� ���ٴ� ������ �ٲ۴ٸ�,
    	//Dish.menu�� ��� Data���� ���ǿ� �����ϴ� ���� �ƴϱ� ������
    	//false�� �����Ѵ�.
        return menu.stream()
        		   .allMatch(d -> d.getCalories() <= 800)
        		   ;
    }

    //3. noneMatch
    private static boolean isHealthyMenu2(){
    	//Dish.menu�� ��� data���� ���ǿ� �������� �����Ƿ�
    	//true�� �����Ѵ�.
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
