package lambdasinaction._01lambda.functional.operator;

import java.util.function.IntBinaryOperator;

public class OperatorExample {
	private static int[] scores = { 92, 95, 87 };

	public static int maxOrMin(IntBinaryOperator operator) {
		int result = scores[0];
		for (int score : scores) {
			result = operator.applyAsInt(result, score);
		}
		return result; }

	public static void main(String[] args) {
		
		// 최대값 얻기
		// argument에 타입을 줘도되고 안줘도 된다.
		// 타입을 주려면 다 줘야한다.
		// maxOrMin 메소드는 IntBinaryOperator를 받기 때문에
		// 타입을 int 외의 다른 것을 줄 수 없다.
		int maxVal = maxOrMin((int n1, int n2) -> {
			if(n1 >= n2) return n1;
			else return n2;
		});
		System.out.println("최대값: "+maxVal);
		
		// 최소값 얻기
		int minVal = maxOrMin((n1, n2) -> {
			if(n1 <= n2) return n1;
			else return n2;
		});
		System.out.println("최소값: "+minVal);
	}
}
