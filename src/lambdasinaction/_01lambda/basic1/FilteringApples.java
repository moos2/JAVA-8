package lambdasinaction._01lambda.basic1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilteringApples {

	public static void main(String... args) {

		List<Apple> inventory = 
				Arrays.asList(new Apple(80, "green"), 
							  new Apple(155, "green"), 
							  new Apple(120, "red"));

		//filter method ȣ��
		List<Apple> result1 = filter(inventory, new AppleColorPredicate());
		System.out.println(result1);
		
		//�͸�Ŭ���� ȣ��
		List<Apple> result2 = filter(inventory, new ApplePredicate<Apple>() {
			
			@Override
			public boolean test(Apple a) {
				// TODO Auto-generated method stub
				return a.getColor().equals("green");
			}
		});
		
		//���ٽ� ȣ��
		result2 = filter(inventory, a -> a.getColor().equals("green"));
		System.out.println(result2);
		
		//API���� �����Ǵ� Predicate �������̽��� �͸�Ŭ������ �����ؼ� ȣ��
		result2 = filter2(inventory, new Predicate<Apple>() {

			@Override
			public boolean test(Apple arg0) {
				// TODO Auto-generated method stub
				return arg0.getWeight() > 100;
			}
		});
		
		//API���� �����Ǵ� Predicate �������̽��� ���ٽ����� �����ؼ� ȣ��
		result2 = filter2(inventory, a -> a.getWeight() > 100);
		
		//Consumer �������̽��� �͸�Ŭ������ �����ؼ� ȣ��
		printAppleInfo(inventory, new Consumer<Apple>() {
			@Override
			public void accept(Apple arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0);
			}
		});
		
		//Consumer �������̽��� ���ٽ����� �����ؼ� ȣ��
		printAppleInfo(inventory, x -> System.out.println(x));
		
		System.out.println("Method Reference");
		//Consumer �������̽��� Method Reference�� ȣ��
		//��ü Ÿ���� Apple�� ��������� ������ Method Reference�� ����� �� �ִ�.
		//Consumer<Apple>�� �ƴ϶�, �׳� Consumer�� �������ָ�
		//Method Reference�� ������� �ʴ´�.
		printAppleInfo(inventory, System.out::println);
	}
	
	public static void printAppleInfo(List<Apple> inventory,
			Consumer<Apple> consumer) {
		for (Apple apple : inventory) {
			consumer.accept(apple);
		}
	}
	
	
	public static List<Apple> filter2(List<Apple> inventory, Predicate<Apple> predicate) {
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory) {
			if(predicate.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if ("green".equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor().equals(color)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
	
	@FunctionalInterface
	interface ApplePredicate<T> {
		public boolean test(Apple a);
	}

	static class AppleWeightPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return apple.getWeight() > 150;
		}
	}

	static class AppleColorPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return "green".equals(apple.getColor());
		}
	}

	static class AppleRedAndHeavyPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return "red".equals(apple.getColor()) && apple.getWeight() > 150;
		}
	}
}