package lambdasinaction._02stream.with.optional;

import java.util.Optional;

public class MobileTesterWithOptional {
	
	public static void main(String[] args) {
		ScreenResolution resolution = new ScreenResolution(750,1334);
		//Null�� ������� �ʴ� Optional.of()
		DisplayFeatures dfeatures = new DisplayFeatures("4.7", Optional.of(resolution));
		Mobile mobile = new Mobile(2015001, "Apple", "iPhone 6s", Optional.of(dfeatures));
		
		MobileService mService = new MobileService();
		
		int width = mService.getMobileScreenWidth(Optional.of(mobile));
		System.out.println("Apple iPhone 6s Screen Width = " + width);
		
		//ifPresent() �޼��� ���
		Optional<ScreenResolution> scr = dfeatures.getResolution();
		scr.ifPresent(x -> System.out.println(x));
		System.out.println(scr.isPresent());
		
		//Null�� ����ϴ� Optional.of()
		Mobile mobile2 = new Mobile(2015001, "Apple", "iPhone 6s", Optional.empty());		
		int width2 = mService.getMobileScreenWidth(Optional.of(mobile2));
		System.out.println("Apple iPhone 6s Screen Width = " + width2);
		
		//df.isPresent()�� false�̴�.
		//������ moblie2���� Optinal.empty()�� null�� �������� �����̴�.
		Optional<DisplayFeatures> df = mobile2.getDisplayFeatures();
		System.out.println(df.isPresent());
		
	}

}
