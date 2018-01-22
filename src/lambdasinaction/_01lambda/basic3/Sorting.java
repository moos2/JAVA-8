package lambdasinaction._01lambda.basic3;

import java.util.*;
//����ƽ �޼ҵ带 import �� ���� import ���� static�� �ٿ��ش�.
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
        
        // 2. ���� anonymous inner class 
        // [Apple{color='green', weight=30}, Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        inventory.sort(new Comparator<Apple>() {

			@Override
			public int compare(Apple arg0, Apple arg1) {
				// TODO Auto-generated method stub
				
				return arg0.getWeight().compareTo(arg1.getWeight());
			}
		});
        
        System.out.println("anonymous inner class ǥ��");
        System.out.println(inventory);

        // reshuffling things a little
        inventory.set(1, new Apple(20, "red"));
        
        // 3. ���� lambda 
        // [Apple{color='red', weight=20}, Apple{color='green', weight=30}, Apple{color='green', weight=155}]
        
        //���� comparing(static method)�� import ��Ű�� �ʾҴٸ� ������ ���� ������Ѵ�.
        // Comparator�� import�������� ��,
        //inventory.sort(Comparator.comparing(x -> x.getWeight()));
        inventory.sort(comparing(x -> x.getWeight()));
        System.out.println("���ٽ�ǥ��");
        System.out.println(inventory);
        
        
        //Comparable�� compareTo()�޼��� ȣ��
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println("comparTo() �޼��� ���");
        System.out.println(inventory);
        
        
        // reshuffling things a little
        inventory.set(1, new Apple(10, "red"));
        
        // 4. ���� Method Reference
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
