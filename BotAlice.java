
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import modules.JsonReader;
import sx.blah.discord.DiscordClient;
import sx.blah.discord.obj.Message;

public class BotAlice extends DiscordClient {
	// TODO: move these to file on server later
	private final static String botemail 	= "pk400@cock.li";
	private final static String botpass 	= "abc123";
	
	JsonReader reader;
	Vector<String> mentionedStrings, answerQuestions;
	Boolean playAccepted;
	
	public BotAlice(String email, String password) throws URISyntaxException, IOException, ParseException {
		super(botemail, botpass);
		
		reader = new JsonReader();
		
		mentionedStrings = new Vector<String>();
		mentionedStrings.add("**Yes master, I am here.:blue_heart: What is your command?**");
		mentionedStrings.add("**I am awake. :)**");
		mentionedStrings.add("**Who calls me?**");
		mentionedStrings.add("**~yawn~**");

		answerQuestions = new Vector<String>();
		answerQuestions.add("**Absolutely!**");
		answerQuestions.add("**Yes.**");
		answerQuestions.add("**Most likely.**");
		answerQuestions.add("**Maybe.**");
		answerQuestions.add("**I am not going to answer that.**");
		answerQuestions.add("**Not really.**");
		answerQuestions.add("**No.**");
		answerQuestions.add("**Absolutely not!**");
		
		
		playAccepted = false;
		
		System.out.println("Alice is online.");
	}
	
	private void getMovieInfo(String movie, Message m) throws JSONException, IOException {
		JSONObject json = reader.readJsonFromUrl(movie);
		StringBuilder sb = new StringBuilder();
		try {
			sb.append(json.get("Poster") + "\\n");
			sb.append("**" + json.get("Title") + "**");
			sb.append(" (" + json.get("Year") + ")");
			sb.append("\\nRating: " + json.get("Rated"));
			sb.append("\\nGenre(s): " + json.get("Genre"));
			sb.append("\\nPlot: " + json.get("Plot"));
			m.reply(sb.toString(), this);
		} catch(IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onMessageReceive(Message message) {
		// TODO Auto-generated method stub
		super.onMessageReceive(message);
		if(message.getContent().contains("Alice go play")) {
			try {
				message.reply("Alright. Hey @" + getUserByID("101513350944428032").getName() + ", Wanna Play?", this);
				message.reply("@GoPlay", this);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(message.getContent().contains("@dump")) {
			try {
				message.reply(message.getAuthor().getID(), this);
				playAccepted = true;
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(message.getContent().startsWith("@omdb")) {
			try {
				getMovieInfo("http://www.omdbapi.com/?t=" + message.getContent().substring(6), message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onMentioned(Message message) {
		// TODO Auto-generated method stub
		super.onMentioned(message);
		
		if(message.getContent().endsWith("?")) {
			try{
				Random gen = new Random();
				int rand = gen.nextInt(answerQuestions.size());
				message.reply((String)answerQuestions.get(rand), this);
			} catch(IOException | ParseException e) {
				e.printStackTrace();
			}
		} else if(message.getContent().contains("wanna play a game?")) {
			try {
				message.reply("**Sure! I love games! Let's play **@" + message.getAuthor().getName() + "**!**", this);
				playAccepted = true;
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(message.getContent().contains("@dump")) {
			try {
				message.reply(message.getAuthor().getID(), this);
				playAccepted = true;
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} /*else {
			Random gen = new Random();
			int rand = gen.nextInt(mentionedStrings.size());
			
			try {
				message.reply((String)mentionedStrings.get(rand), this);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
	
	
	/*public static void main(String... args) {
	    try {
		    new BotAlice("pk400@cock.li", "abc123");
	    } catch (Exception e) {
		    e.printStackTrace();
	    }
	}*/
	
}
