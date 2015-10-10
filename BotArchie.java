
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.time.format.DateTimeFormatter;

import org.json.simple.parser.ParseException;

import sx.blah.discord.DiscordClient;
import sx.blah.discord.obj.Message;

public class BotArchie extends DiscordClient {
	// TODO: move these to file on server later
	private final static String botemail 	= "botarchie400@cock.li";
	private final static String botpass 	= "abc123";

	StringBuilder history;
	PrintWriter file;
	
	public BotArchie(String email, String password) throws URISyntaxException, IOException, ParseException {
		//super(email, password);
		super(botemail, botpass);
		
		history = new StringBuilder();
		
		System.out.println("Archie is online.");
	}

	@Override
	public void connect() {
		// TODO Auto-generated method stub
		super.connect();
	}

	@Override
	public void onMessageReceive(Message m) {
		super.onMessageReceive(m);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM HH:mm:ss");
		
		/*history.append(m.getTimestamp().format(dtf) + " | "
				+ " **" + m.getAuthor().getName() + "**"
				+ ": *" + m.getContent() + "*\\n");*/
		
		StringBuilder hist = new StringBuilder();
		hist.append(m.getTimestamp().format(dtf) + " | "
				+ " **" + m.getAuthor().getName() + "**"
				+ ": *" + m.getContent() + "*\\n");

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(m.getChannelID().toString() + ".log", true));
			bw.write(hist.toString());
			bw.newLine();
			bw.flush();
			if(bw != null)
				bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(m.getContent().equals("/history")) {
			BufferedReader br = null;
			
			try {
				br = new BufferedReader(new FileReader(m.getChannelID().toString() + ".log"));
				StringBuilder sb = new StringBuilder();
				String line;
				
				sb.append("_**From " +m.getChannelID().toString() + "**_\\n\\n");
				
				while((line = br.readLine()) != null) {
					sb.append(line);
				}
				
				br.close();
				
				m.reply(sb.toString(), this);
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
	}
}
