

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.simple.parser.ParseException;

import sx.blah.discord.DiscordClient;
import sx.blah.discord.handle.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

public class BotAlice {
	// TODO: move these to file on server later
	private final static String botemail 	= "pk400@cock.li";
	private final static String botpass 	= "abc123";
	
	public static void main(String[] args) {
		try {
			DiscordClient.get().login(botemail, botpass);
			
			DiscordClient.get().getDispatcher().registerListener(new IListener<MessageReceivedEvent>() {

				@Override
				public void receive(MessageReceivedEvent event) {
					
				}
				
			});
		} catch (IOException | ParseException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
