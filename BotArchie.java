
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.format.DateTimeFormatter;

import org.json.simple.parser.ParseException;

import sx.blah.discord.DiscordClient;
import sx.blah.discord.handle.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.Message;

public class BotArchie {
	// TODO: move these to file on server later
	private final static String botemail 	= "botarchie400@cock.li";
	private final static String botpass 	= "abc123";
	private final static String os = System.getProperty("os.name").toLowerCase();
	
	public static void main(String... args) 
			throws IOException, ParseException, URISyntaxException {
		
		DiscordClient.get().login(botemail, botpass);
		
		DiscordClient.get().getDispatcher().registerListener(
				new IListener<MessageReceivedEvent>() {

			@Override
			public void receive(MessageReceivedEvent event) {
				Message m = event.getMessage();
				
				//------------------------------------------------------------//
				// WRITE TO LOG
				DateTimeFormatter dtf = 
						DateTimeFormatter.ofPattern("dd/MM HH:mm:ss");
				
				StringBuilder addlog = new StringBuilder();
				addlog.append(m.getTimestamp().format(dtf) 
						+ " **" + m.getAuthor().getName() 
						+ "**: *" + m.getContent() + "*\\n");
				
				BufferedWriter bw = null;
				try {
					if(os.contains("windows")) {
						bw = new BufferedWriter(
								new FileWriter(m.getChannelID().toString() 
										+ ".txt", true));
					} else {
						bw = new BufferedWriter(
								new FileWriter("/usr/share/nginx/html/logs/" 
										+ m.getChannelID().toString() 
										+ ".txt", true));
					}
					bw.write(addlog.toString());
					bw.newLine();
					bw.flush();
					if(bw != null)
						bw.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
				
				if(m.getContent().equals("/log")) {
					try {
						m.reply("http://54.172.169.246/logs/" 
								+ m.getChannelID().toString() + ".txt");
					} catch (IOException | ParseException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}
