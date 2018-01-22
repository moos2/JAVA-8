
public class LambdaHello {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 익명클래스 선언
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Hello 익명클래스");
			}
		})
		.start();
		
		// 람다식으로 선언
		new Thread(() -> System.out.println("Hello 람다")).start();
	}

}
