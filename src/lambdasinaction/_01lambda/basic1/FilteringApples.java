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

		//filter method 호출
		List<Apple> result1 = filter(inventory, new AppleColorPredicate());
		System.out.println(result1);
		
		//익명클래스 호출
		List<Apple> result2 = filter(inventory, new ApplePredicate<Apple>() {
			
			@Override
			public boolean test(Apple a) {
				// TODO Auto-generated method stub
				return a.getColor().equals("green");
			}
		});
		
		//람다식 호출
		result2 = filter(inventory, a -> a.getColor().equals("green"));
		System.out.println(result2);
		
		//API에서 제공되는 Predicate 인터페이스를 익명클래스로 선언해서 호출
		result2 = filter2(inventory, new Predicate<Apple>() {

			@Override
			public boolean test(Apple arg0) {
				// TODO Auto-generated method stub
				return arg0.getWeight() > 100;
			}
		});
		
		//API에서 제공되는 Predicate 인터페이스를 람다식으로 선언해서 호출
		result2 = filter2(inventory, a -> a.getWeight() > 100);
		
		//Consumer 인터페이스를 익명클래스로 선언해서 호출
		printAppleInfo(inventory, new Consumer<Apple>() {
			@Override
			public void accept(Apple arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0);
			}
		});
		
		//Consumer 인터페이스를 람다식으로 선언해서 호출
		printAppleInfo(inventory, x -> System.out.println(x));
		
		System.out.println("Method Reference");
		//Consumer 인터페이스를 Method Reference로 호출
		//객체 타입을 Apple로 지정해줬기 때문에 Method Reference가 적용될 수 있다.
		//Consumer<Apple>이 아니라, 그냥 Consumer로 지정해주면
		//Method Reference가 적용되지 않는다.
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