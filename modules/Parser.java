package modules;

public class Parser {

	private Generator generator;
	private Type type;
	
	public Parser(){
		generator = new Generator();
	}
	
	public void parse(String message, int mood){
		String[] set1 = {"hello","hi","hey"};
		for(String s : set1){
			if(message.contains(s)){
				type = Type.Greeting;
				generator.createReply(type, mood);
				return;
			}
		}
		
		String[] set2 = {"status"};
		for(String s : set2){
			if(message.contains(s)){
				type = Type.Asked;
				generator.createReply(type, mood);
				return;
			}
		}
		
		String[] set3 = {"thx","thank","ty","appreciate"};
		for(String s : set3){
			if(message.contains(s)){
				type = Type.Appreciation;
				generator.createReply(type, mood);
				return;
			}
		}
		
		String[] set4 = {"help","what","how","?"};
		for(String s : set4){
			if(message.contains(s)){
				type = Type.Question;
				generator.createReply(type, mood);
				return;
			}
		}
	}

	public String generate(){
		return  generator.getReply();
	}
	
	
}
