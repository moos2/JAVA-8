package lambdasinaction._02stream.without.optional;

//Optional을 사용하지 않은 null check 방식
public class MobileService {
	public int getMobileScreenWidth(Mobile mobile){
		if(mobile != null){
			DisplayFeatures dfeatures = mobile.getDisplayFeatures();
			if(dfeatures != null){
				ScreenResolution resolution = dfeatures.getResolution();
				if(resolution != null){
					return resolution.getWidth();
				}
			}
		}
		return 0;
	}
}
