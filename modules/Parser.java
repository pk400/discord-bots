package modules;

public class Parser {

	private Generator generator;
	private Type type;
	
	public Parser(){
		generator = new Generator();
	}
	
	public void parse(String message, int mood){
		String[] set1 = {"hello","hi","hey","sup","what's up","yo","ay"};
		for(String s : set1){
			if(message.contains(s)){
				type = Type.Greeting;
				generator.createReply(type, mood);
				return;
			}
		}
		
		String[] set2 = {"how are","going","feeling","mood"};
		for(String s : set2){
			if(message.contains(s)){
				type = Type.Asked;
				generator.createReply(type, mood);
				return;
			}
		}
		
		String[] set3 = {"thx","thanks","thank you","ty","tyvm","appreciate it"};
		for(String s : set3){
			if(message.contains(s)){
				type = Type.Appreciation;
				generator.createReply(type, mood);
				return;
			}
		}
	}

	public String generate(){
		return  generator.getReply();
	}
	
	
}
