
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

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
	
	private static long start;
	
	public static void main(String... args) 
			throws IOException, ParseException, URISyntaxException {
		
		start = System.nanoTime();
		
		DiscordClient.get().login(botemail, botpass);
		
		DiscordClient.get().getDispatcher().registerListener(
				new IListener<MessageReceivedEvent>() {
					
			@Override
			public void receive(MessageReceivedEvent event) {
				Message m = event.getMessage();
				
				DateTimeFormatter logdtf, logsavedtf; 
				logdtf 		= DateTimeFormatter.ofPattern("dd/MM HH:mm:ss");
				logsavedtf 	= DateTimeFormatter.ofPattern("YYYYMMdd");
				
				//------------------------------------------------------------//
				// WRITE TO LOG
				
				StringBuilder addlog = new StringBuilder();
				addlog.append(m.getTimestamp().format(logdtf) 
						+ " **" + m.getAuthor().getName() 
						+ "**: *" + m.getContent() + "*\\n");
				
				BufferedWriter bw = null;
				try {
					File loc;
					if(os.contains("windows")) {
						loc = new File("logs/"
								+ m.getChannelID().toString());
						loc.mkdirs();
						bw = new BufferedWriter(
								new FileWriter(loc + "/"
										+ m.getTimestamp().format(logsavedtf)
										+ ".txt", true));
					} else {
						loc = new File("/usr/share/nginx/html/logs/" 
								+ m.getChannelID().toString());
						loc.mkdirs();
						bw = new BufferedWriter(
								new FileWriter(loc + "/"
										+ m.getTimestamp().format(logsavedtf)
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
				
				if(m.getContent().equals("!log")) {
					try {
						m.reply("http://54.172.169.246/logs/" 
								+ m.getChannelID().toString() + "/"
								+ m.getTimestamp().format(logsavedtf)
								+ ".txt");
						//DiscordClient.get().deleteMessage(
						//		m.getMessageID(), m.getChannelID());
					} catch (IOException | ParseException e) {
						e.printStackTrace();
					}
				} else if(m.getContent().equals("!uptime")) {
					long hour = TimeUnit.HOURS.convert(
							System.nanoTime() - start, TimeUnit.NANOSECONDS);
					long minute = TimeUnit.MINUTES.convert(
							System.nanoTime() - start, TimeUnit.NANOSECONDS);
					long second = TimeUnit.SECONDS.convert(
							System.nanoTime() - start, TimeUnit.NANOSECONDS);
					
					try {
						m.reply("I have been online for **"
								+ hour + "** *hour(s)* **"
								+ minute + "** *minute(s)* "
								+ " and **" + second + "** *seconds*");
						//DiscordClient.get().deleteMessage(
						//		m.getMessageID(), m.getChannelID());
					} catch (IOException | ParseException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}
