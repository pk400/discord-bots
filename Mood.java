
public enum Mood {
	Excited, 
	VeryHappy,
	Happy,
	Content,
	Mad,
	VeryMad;
	
	public int getValue(Mood mood){
		switch(mood){
		case Excited:
			return 1;
		case VeryHappy:
			return 2;
		case Happy:
			return 3;
		case Content:
			return 4;
		case Mad:
			return 5;
		case VeryMad:
			return 6;
		}
		return 0;
	}
}
