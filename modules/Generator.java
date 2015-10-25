package modules;

import java.util.Random;

public class Generator {
	
	private String sentence;
	
	public Generator(){
	}
	
	public void createReply(Type type, int mood){
		if(type == Type.Greeting){
			createGreetings1(mood);
		}else if(type == Type.Asked){
			createAsked1(mood);
		}else if(type == Type.Appreciation){
			createAppreciation1(mood);
		}else if(type == Type.Question){
			createQuestion1(mood);
		}
	}
	
	
	private void createGreetings1(int mood){
		String[] exclamation = {
				"hi.",
				"hello.",
				"greetings.",
				"http:\\/\\/cdn1.sbnation.com\\/assets\\/3888483\\/knicksfailure.gif",
				"http:\\/\\/media.giphy.com\\/media\\/HwmB7t7krGnao\\/giphy.gif"
		};
		
		Random rand = new Random();
		int i = rand.nextInt(exclamation.length);
		sentence = exclamation[i];
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
		
		sentence = subject + " " + verb + " " + adjective;
	}
	
	private void createAppreciation1(int mood){
		String[] exclamation = {
				"you are welcome.",
				"no problem."
		};
		
		Random rand = new Random();
		int i = rand.nextInt(exclamation.length);
		sentence = exclamation[i];
	}
	
	private void createQuestion1(int mood){
		String add = "To add: @add | @addMovie [movie] \\n";
		String remove = "To remove: @remove | @delete [movie] \\n";
		String roulette = "To pick a movie randomly: @roulette | @movieRoulette \\n";
		String list = "To list the movies added: @list | @movies | @listMovies \\n";
		String reset = "To reset the list: @reset | @resetMovies | @resetList \\n";
		String info = "To show information about a movie: @info | @movie [movie] \\n";
		
		String extra = "\\n"+"I get mad if no one talks to me.";

		sentence = "List of commands:  \\n" + add + remove + roulette + list + reset + info + extra + "";
	}
	
	public String getReply(){
		return sentence;
	}
	
	

}
