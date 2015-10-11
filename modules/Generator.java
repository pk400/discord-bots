package modules;

import java.util.Random;

public class Generator {
	
	private String tester;
	
	public Generator(){
	}
	
	public void createReply(Type type, int mood){
		if(type == Type.Greeting){
			createGreetings1(mood);
		}else if(type == Type.Asked){
			createAsked1(mood);
		}else if(type == Type.Appreciation){
			createAppreciation1(mood);
		}
	}
	
	
	private void createGreetings1(int mood){
		String[] exclamation = {
				"hey man, what do you need?",
				"yo yo, what's up?",
				"hey! Have you heard of DeKobe? His tracks are awesome.",
				"yo check out DeKobe, this dude makes better music than Kanye",
				"what up my nigga?",
				"what's good in the hood",
				"greetings fellow redneck!",
				"well why don't you just fuck me in the ass, you little cock sucker",
				"hey! Today is such a great day.",
				"man, if I was a real robot I will kill you all :D",
				"if only I had consciousness...",
				"the moment when you are having fun but realise you got student loans.",
				"Movie-Bot at your service.",
				"not to brag, but I am a movie genius",
				"I have a PhD in movies",
				"you gonna watch movies or what?"
		};
		
		Random rand = new Random();
		int i = rand.nextInt(exclamation.length);
		tester = exclamation[i];
	}
	
	private void createAsked1(int mood){
		String subject = "I";
		String verb = "am";
		String adjective = "";
		
		if(mood == 1)
			adjective = "excited";
		else if(mood == 2)
			adjective = "very happy";
		else if(mood == 3)
			adjective= "happy";
		else if(mood == 4)
			adjective = "content";
		else if(mood == 5)
			adjective = "mad";
		else if(mood == 6)
			adjective= "very mad";
		
		tester = "";
		tester = subject + " " + verb + " " + adjective;
	}
	
	private void createAppreciation1(int mood){
		
		String exclamation = "";
		if(mood == 1)
			exclamation = "anytime buddy!!";
		else if(mood == 2)
			exclamation = "no problem!";
		else if(mood == 3)
			exclamation= "you're welcome!";
		else if(mood == 4)
			exclamation = "you are welcome.";
		else if(mood == 5)
			exclamation = "what else?";
		else if(mood == 6)
			exclamation= "leave me alone.";
		
		tester = exclamation;
	}
	
	public String getReply(){
		return tester;
	}
	
	

}
