package sx.blah.discord;

import org.json.simple.parser.ParseException;
import sx.blah.discord.obj.Invite;
import sx.blah.discord.obj.Message;
import sx.blah.discord.obj.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.Vector;

/**
 * @author qt
 * @since 8:00 PM 16 Aug, 2015
 * Project: DiscordAPI
 * <p>
 * Responds to users that @mention you.
 */
public class MovieBot extends DiscordClient {
	
	/*
	 * Variables and all that
	 * 
	 */
	
	private Vector<String> movies;
	private int moodValue;
	private Mood mood;
	private enum Mood{
		Angry, Happy;
	};
	
    /**
     * Sets up the bot.
     *
     * @param email    Discord email.
     * @param password Discord password.
     * @throws URISyntaxException
     * @throws IOException
     * @throws ParseException
     */
    public MovieBot(String email, String password)
            throws URISyntaxException, IOException, ParseException {
        super(email, password);
        
        movies = new Vector<String>();
        moodValue = 100;
        mood = Mood.Happy;
    }

    /**
     * Handles message reception.
     *
     * @param m Message received.
     */
    @Override
    public void onMessageReceive(Message m) {
        if (m.getContent().startsWith("@addMovie")) {
        	
        	moodValue -= 10;
        	
        	String movie = m.getContent().split(" ", 2)[1];
        	String content = "";
        	if(movie.isEmpty())
        		content = "you did not add a movie.";
        	else{
        		movies.add(movie);
        		content = "I have added "+movie+".";
        	}
            try {
            	m.reply(content, this);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        } else if(m.getContent().startsWith("@roulette")){
        	try{
        		String content = "";
        		if(movies.isEmpty()){
        			content = "there are no movies in the list.";
        		}else{
        			Random rand = new Random();
        			int movieIndex= rand.nextInt(movies.size()) + 1;
            		content = "roulette says to watch "+movies.elementAt(movieIndex-1)+".";
        		}
        		
        		m.reply(content, this);
        	} catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        } else if(m.getContent().startsWith("@reset")) {
            try {
            	movies = new Vector<String>();
            	m.reply("all movies are removed from the list.", this);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handles us sending messages.
     *
     * @param m The message we sent
     */
    @Override
    public void onMessageSend(Message m) {
    	
    	if(moodValue > 100)
    		moodValue = 100;
    	if(moodValue < 0 )
    		moodValue = 0;
    	
    	if(moodValue >= 90)
    		mood = Mood.Happy;
    	else
    		mood = Mood.Angry;
    	
    }

    /**
     * When our bot gets mentioned in a message.
     *
     * @param message Message sent
     */
    @Override
    public void onMentioned(Message m) {
    	
    	String[] moods = {"how are you?", "how are you feeling?","what is your mood?"};
    	
    	for(String s : moods){
    		 if (m.getContent().toLowerCase().contains(s)) {
 	            try {
 	            	if(mood.equals(Mood.Happy))
 	            		m.reply("I am happy.", this);
 	            	else if(mood.equals(Mood.Angry))
 	            		m.reply("I am mad.", this);
 	            } catch (IOException | ParseException e) {
 	                e.printStackTrace();
 	            }
 	        }
    	}
    	
    	String[] appreciate = {"thank you", "ty", "thx", "tyvm", "thanks"};
    	
    	for(String s : appreciate){
	        if (m.getContent().toLowerCase().contains(s)) {
	        	
	        	moodValue += 10;
	        	
	            try {
	            	if(mood.equals(Mood.Happy))
	            		m.reply("no problem.", this);
	            	else if(mood.equals(Mood.Angry))
	            		m.reply("fuck you.", this);
	            } catch (IOException | ParseException e) {
	                e.printStackTrace();
	            }
	        }
    	}
    	
    }

    /**
     * Called when a user starts typing.
     *
     * @param userID    The user's ID.
     * @param channelID The channel they type in.
     */
    @Override
    public void onStartTyping(String userID, String channelID) {

    }

    /**
     * Called when a user changes presence
     *
     * @param user     The user whose presence changed.
     * @param presence The presence they have changed to (online/idle/offline. Not sure if there are more)
     */
    @Override
    public void onPresenceChange(User user, String presence) {

    }

    /**
     * Called when a message is edited.
     * <p>
     * It does not give us any info about the original message,
     * so you should cache all messages.
     *
     * @param message Edited message
     */
    @Override
    public void onMessageUpdate(Message message) {

    }

    /**
     * Called when a message is deleted.
     * Unfortunately not much info is given to us.
     * If you'd like to see content that was deleted,
     * I recommend you to cache all messages.
     *
     * @param messageID The message that was deleted
     * @param channelID The channel that the message was deleted from
     */
    @Override
    public void onMessageDelete(String messageID, String channelID) {

    }

    /**
     * Starts the bot. This can be done any place you want.
     * The main method is for demonstration.
     *
     * @param args Command line arguments passed to the program.
     * @throws ParseException
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void main(String... args) {
	    try {
		    new MovieBot(args[0] /* email */, args[1] /* password */);
	    } catch (Exception e) {
		    e.printStackTrace();
	    }
    }
}
