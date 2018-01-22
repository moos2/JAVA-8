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
		
		// �ִ밪 ���
		// argument�� Ÿ���� �൵�ǰ� ���൵ �ȴ�.
		// Ÿ���� �ַ��� �� ����Ѵ�.
		// maxOrMin �޼ҵ�� IntBinaryOperator�� �ޱ� ������
		// Ÿ���� int ���� �ٸ� ���� �� �� ����.
		int maxVal = maxOrMin((int n1, int n2) -> {
			if(n1 >= n2) return n1;
			else return n2;
		});
		System.out.println("�ִ밪: "+maxVal);
		
		// �ּҰ� ���
		int minVal = maxOrMin((n1, n2) -> {
			if(n1 <= n2) return n1;
			else return n2;
		});
		System.out.println("�ּҰ�: "+minVal);
	}
}
