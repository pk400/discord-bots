package sx.blah.discord;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.parser.ParseException;

import sx.blah.discord.obj.Channel;
import sx.blah.discord.obj.Guild;
import sx.blah.discord.obj.Invite;
import sx.blah.discord.obj.Message;
import sx.blah.discord.obj.User;

public class BotAlice extends DiscordClient {
	Vector<String> mentionedStrings;
	Boolean playAccepted;
	
	public BotAlice(String email, String password) throws URISyntaxException, IOException, ParseException {
		super(email, password);
		// TODO Auto-generated constructor stub
		System.out.println("Initializing Alice.");
		
		mentionedStrings = new Vector<String>();
		mentionedStrings.add("**Yes master, I am here.:blue_heart: What is your command?**");
		mentionedStrings.add("**I am awake. :)**");
		mentionedStrings.add("**Who calls me?**");
		mentionedStrings.add("**~yawn~**");
		
		playAccepted = false;
		
		System.out.println("Alice is awake.");
	}

	@Override
	public void connect() {
		// TODO Auto-generated method stub
		super.connect();
	}

	@Override
	public void onOpen(ServerHandshake serverHandshake) {
		// TODO Auto-generated method stub
		super.onOpen(serverHandshake);
	}

	@Override
	public void onMessage(String message) {
		// TODO Auto-generated method stub
		super.onMessage(message);
	}

	@Override
	public void onClose(int i, String s, boolean b) {
		// TODO Auto-generated method stub
		super.onClose(i, s, b);
	}

	@Override
	public void onError(Exception e) {
		// TODO Auto-generated method stub
		super.onError(e);
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
		}
	}

	@Override
	public void onMessageSend(Message message) {
		// TODO Auto-generated method stub
		super.onMessageSend(message);
	}

	@Override
	public void onMentioned(Message message) {
		// TODO Auto-generated method stub
		super.onMentioned(message);
		
		if(message.getContent().contains("wanna play a game?")) {
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

	@Override
	public void onStartTyping(String userID, String channelID) {
		// TODO Auto-generated method stub
		super.onStartTyping(userID, channelID);
	}

	@Override
	public void onPresenceChange(User user, String presence) {
		// TODO Auto-generated method stub
		super.onPresenceChange(user, presence);
	}

	@Override
	public void onMessageUpdate(Message message) {
		// TODO Auto-generated method stub
		super.onMessageUpdate(message);
	}

	@Override
	public void onMessageDelete(String messageID, String channelID) {
		// TODO Auto-generated method stub
		super.onMessageDelete(messageID, channelID);
	}

	@Override
	public Message sendMessage(String content, String channelID, String... mentions)
			throws IOException, ParseException {
		// TODO Auto-generated method stub
		return super.sendMessage(content, channelID, mentions);
	}

	@Override
	public void deleteMessage(String messageID, String channelID) throws IOException {
		// TODO Auto-generated method stub
		super.deleteMessage(messageID, channelID);
	}

	@Override
	public void changeAccountInfo(String username, String email, String password)
			throws UnsupportedEncodingException, ParseException {
		// TODO Auto-generated method stub
		super.changeAccountInfo(username, email, password);
	}

	@Override
	public Invite acceptInvite(String inviteCode) throws IOException, ParseException {
		// TODO Auto-generated method stub
		return super.acceptInvite(inviteCode);
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return super.isReady();
	}

	@Override
	public User getOurUser() {
		// TODO Auto-generated method stub
		return super.getOurUser();
	}

	@Override
	public Channel getChannelByID(String id) {
		// TODO Auto-generated method stub
		return super.getChannelByID(id);
	}

	@Override
	public Guild getGuildByID(String guildID) {
		// TODO Auto-generated method stub
		return super.getGuildByID(guildID);
	}

	@Override
	public List<Guild> getGuildList() {
		// TODO Auto-generated method stub
		return super.getGuildList();
	}

	@Override
	public User getUserByID(String userID) {
		// TODO Auto-generated method stub
		return super.getUserByID(userID);
	}
	
	public static void main(String... args) {
	    try {
		    new BotAlice(args[0], args[1]);
	    } catch (Exception e) {
		    e.printStackTrace();
	    }
	}
	
}
