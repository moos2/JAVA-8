package lambdasinaction._01lambda.basic3;

import java.util.*;
//스태틱 메소드를 import 할 때는 import 옆에 static을 붙여준다.
import static java.util.Comparator.comparing;


public class Sorting {

    public static void main(String...args){

        // 1. static inner class 
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(new Apple(80,"green"), 
        		                       new Apple(155,"green"), 
        		                       new Apple(120,"red")));

        // [Apple{color='green', weight=80}, Apple{color='red', weight=120}, Apple{color='green', weight=155}]
        inventory.sort(new AppleComparator());
        System.out.println(inventory);

        // reshuffling things a little
        inventory.set(1, new Apple(30, "green"));
        
        // 2. 구현 anonymous inner class 
        // [Apple{color='green', weight=30}, Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        inventory.sort(new Comparator<Apple>() {

			@Override
			public int compare(Apple arg0, Apple arg1) {
				// TODO Auto-generated method stub
				
				return arg0.getWeight().compareTo(arg1.getWeight());
			}
		});
        
        System.out.println("anonymous inner class 표현");
        System.out.println(inventory);

        // reshuffling things a little
        inventory.set(1, new Apple(20, "red"));
        
        // 3. 구현 lambda 
        // [Apple{color='red', weight=20}, Apple{color='green', weight=30}, Apple{color='green', weight=155}]
        
        //만약 comparing(static method)를 import 시키지 않았다면 다음과 같이 써줘야한다.
        // Comparator만 import시켜줬을 때,
        //inventory.sort(Comparator.comparing(x -> x.getWeight()));
        inventory.sort(comparing(x -> x.getWeight()));
        System.out.println("람다식표현");
        System.out.println(inventory);
        
        
        //Comparable의 compareTo()메서드 호출
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println("comparTo() 메서드 사용");
        System.out.println(inventory);
        
        
        // reshuffling things a little
        inventory.set(1, new Apple(10, "red"));
        
        // 4. 구현 Method Reference
        // [Apple{color='red', weight=10}, Apple{color='red', weight=20}, Apple{color='green', weight=155}]
        inventory.sort(comparing(Apple::getWeight));
    
    }

    public static class Apple {
        private Integer weight = 0;
        private String color = "";

        public Apple(Integer weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                   "color='" + color + '\'' +
                   ", weight=" + weight +
                   '}';
        }
    }

    static class AppleComparator implements Comparator<Apple> {
        public int compare(Apple a1, Apple a2){
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }
}
