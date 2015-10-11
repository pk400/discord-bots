import modules.Parser;



public abstract class BlankBot {

	
	private Mood mood;
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
			mood = Mood.Excited;
		else if(moodVal <=90 && moodVal > 80)
			mood = Mood.VeryHappy;
		else if(moodVal <= 80 && moodVal > 70)
			mood = Mood.Happy;
		else if(moodVal <= 70 && moodVal > 60)
			mood = Mood.Content;
		else if(moodVal <= 60 && moodVal > 50)
			mood = Mood.Mad;
		else
			mood = Mood.VeryMad;
	}
	
	protected String thoughts(String message){
		calculateMood();
		parser.parse(message, mood.getValue(mood));
		return parser.generate();
	}
	
	protected abstract void run(String... args);

}
