
public class LambdaHello {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// �͸�Ŭ���� ����
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Hello �͸�Ŭ����");
			}
		})
		.start();
		
		// ���ٽ����� ����
		new Thread(() -> System.out.println("Hello ����")).start();
	}

}
