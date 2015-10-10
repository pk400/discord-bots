import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import sx.blah.discord.DiscordClient;

public class BotFactory {
	private List<DiscordClient> activated;
	
	public BotFactory(String[] args) throws URISyntaxException, IOException, ParseException {
		activated = new ArrayList<DiscordClient>();
		
		for(int i = 0; i < args.length; i++) {
			switch(args[i]) {
			case "0": activated.add(new BotAlice(null, null)); 	break;
			case "1": activated.add(new BotArchie(null, null)); break;
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			new BotFactory(args);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
