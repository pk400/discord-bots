import modules.Parser;



public abstract class BlankBot {

	private int mood;
	private float moodVal;
	private Parser parser;
	
	public BlankBot(){
		parser = new Parser();
		moodVal = 100f;
		calculateMood();
	}
	
	protected void subtractMoodVal(float x){
		moodVal -= x;
		if(moodVal < 0)
			moodVal = 0;
	}
	
	protected void addMoodVal(float x){
		moodVal += x;
		if(moodVal > 100)
			moodVal = 100;
	}
	
	private void calculateMood(){
		if(moodVal <= 100 && moodVal > 90)
			mood = 1;
		else if(moodVal <=90 && moodVal > 80)
			mood = 2;
		else if(moodVal <= 80 && moodVal > 70)
			mood = 3;
		else if(moodVal <= 70 && moodVal > 60)
			mood = 4;
		else if(moodVal <= 60 && moodVal > 50)
			mood = 5;
		else if(moodVal <= 50)
			mood = 6;
	}
	
	protected String thoughts(String message){
		calculateMood();
		parser.parse(message, mood);
		return parser.generate();
	}
	
	protected abstract void run(String... args);

}
