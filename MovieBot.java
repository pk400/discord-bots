// Discord4J - Unofficial wrapper for Discord API
// Copyright (c) 2015
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; either version 2
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
import modules.Movie;

import org.json.simple.parser.ParseException;

import sx.blah.discord.Discord4J;
import sx.blah.discord.DiscordClient;
import sx.blah.discord.handle.IListener;
import sx.blah.discord.handle.impl.events.InviteReceivedEvent;
import sx.blah.discord.handle.impl.events.MentionEvent;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.Channel;
import sx.blah.discord.handle.obj.Invite;
import sx.blah.discord.handle.obj.Message;
import sx.blah.discord.handle.obj.User;

import java.awt.Window.Type;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

/**
 * @author jfsaaved
 * Project: MovieBot
 */
public class MovieBot extends BlankBot{

	private enum Mode{
		Normal, Max2;
	}
	
	private Mode mode;
	private Vector<Movie> movies;
	private Vector<String> contributor;

	public MovieBot(String... args){
		super();
		
		this.mode = Mode.Normal;
		this.contributor = new Vector<String>();
		this.movies = new Vector<Movie>();
		this.run(args[0], args[1]);
	}
	
	private String addMovie(Movie movie, User user){
		
		String message = "error";
		
		if(this.mode == Mode.Max2){
			int counter = 0;
			for(String name : contributor){
				if(name.equals(name))
					counter++;
			}
			if(counter >= 2)
				return "you already added 2 movies.";
		}
		
		movies.add(movie);
		contributor.add(user.getName());
		message = movie.getContent();
		
		return message;
	}
		
	private String deleteMovie(Movie m2){
		if(!m2.isValid()){
			subtractMoodVal(3);
			return "that is not a movie.";
		}
		else if(movies.isEmpty()){
			subtractMoodVal(3);
			return "there are no movies in the list.";
		}else{
			int i = 0;
			for(Movie m : movies){
				if(m.getImdbLink().equals(m2.getImdbLink())){
					movies.remove(i);
					contributor.remove(i);
					return "I have removed " + m2.getTitle() +".";
				}
				i++;
			}
		}
		subtractMoodVal(3);
		return "I could not find that movie in the list.";
	}
	
	private String movieRoulette(){
		if(movies.isEmpty()){
			subtractMoodVal(3);
			return "you have not added any movies or shows yet.";
		}else{
			Random rand = new Random();
			int i = rand.nextInt(movies.size());
			return movies.elementAt(i).getContent();
		}
	}
	
	private String listMovies(){
		if(movies.isEmpty()){
			subtractMoodVal(3);
			return "you have not added any movies or shows yet.";
		}else{
			String returnString = "";
			int i = 0;
			for(Movie mv : movies){
				returnString =  returnString + "\\n" + mv.getTitle() + " added by: " + contributor.elementAt(i);
				i++;
			}
			return returnString;
		}
	}
	
	private String resetMovies(){
		if(movies.isEmpty()){
			subtractMoodVal(3);
			return "you have not added any movies or shows yet.";
		}else{
			String returnString = "I have reset the list.";
			movies = new Vector<>();
			contributor = new Vector<>();
			return returnString;
		}
	}
	
	
	protected void run(String... args){
		try {
			DiscordClient.get().login(args[0], args[1]);

			DiscordClient.get().getDispatcher().registerListener(new IListener<MessageReceivedEvent>() {
				@Override public void receive(MessageReceivedEvent messageReceivedEvent) {
					Message m = messageReceivedEvent.getMessage();
					String replyString = "";
					
					/*
					 * Movie stuff starts here
					 */
					if (m.getContent().startsWith("@addMovie")
							|| m.getContent().startsWith("@add")) {
						Movie movie = new Movie(m.getContent().split(" ", 2)[1]);
						if(!movie.isValid())
							replyString = "**sorry, but I could not add: **"+m.getContent().split(" ", 2)[1]+".";
						else{
							replyString = addMovie(movie,m.getAuthor());
						}
						try {
							m.reply(replyString);
						} catch (IOException | ParseException e) {
							e.printStackTrace();
						}
					}  else if (m.getContent().startsWith("@delete")
							|| m.getContent().startsWith("@remove")){
						Movie movie = new Movie(m.getContent().split(" ", 2)[1]);
						try {
							m.reply(deleteMovie(movie));
						} catch (IOException | ParseException e) {
							e.printStackTrace();
						}
					} else if (m.getContent().startsWith("@movieRoulette")
							|| m.getContent().startsWith("@roulette")) {
						try {
							m.reply(movieRoulette());
						} catch (IOException | ParseException e) {
							e.printStackTrace();
						}
					} else if (m.getContent().startsWith("@listMovies")
							|| m.getContent().startsWith("@movies")
							|| m.getContent().startsWith("@list")){
						try {
							m.reply(listMovies());
						} catch (IOException | ParseException e) {
							e.printStackTrace();
						}
					} else if (m.getContent().startsWith("@reset")
							|| m.getContent().startsWith("@resetMovies")
							|| m.getContent().startsWith("@resetList")){
						try {
							m.reply(resetMovies());
						} catch (IOException | ParseException e) {
							e.printStackTrace();
						}
					} else if(m.getContent().startsWith("@mode")){
						String type = m.getContent().split(" ", 2)[1];
						if(type.toLowerCase().equals("one") || type.equals("1") || type.toLowerCase().equals("normal")){
							mode = Mode.Normal;
							replyString = "changed mode to: Normal";
						}else if(type.toLowerCase().equals("two") || type.equals("2") || type.toLowerCase().equals("max 2")){
							mode = Mode.Max2;
							replyString = "changed mode to: Maximum of 2 per person";
						}else if(type.equals("?") || type.toLowerCase().equals("what")){
							if(mode == Mode.Normal)
								replyString = "current mode: Normal";
							else
								replyString = "current mode: Maximum of 2 per person";
						}
						try {
							m.reply(replyString);
						} catch (IOException | ParseException e) {
							e.printStackTrace();
						}
					} else if (m.getContent().startsWith("@movie")
							|| m.getContent().startsWith("@info")){
						try{
							Movie movie = new Movie(m.getContent().split(" ", 2)[1]);
							if(!movie.isValid())
								replyString = "**sorry, but I could not find information about: **"+m.getContent().split(" ", 2)[1]+".";
							else
								replyString = movie.getContent();
							try {
								m.reply(replyString);
							} catch (IOException | ParseException e) {
								e.printStackTrace();
							}
						} catch(ArrayIndexOutOfBoundsException e){
							replyString = "no movie found.";
							try {
								m.reply(replyString);
							} catch (IOException | ParseException e2) {
								e2.printStackTrace();
							}
						}
					}
					/*
					 * Movie stuff ends here
					 */
					
					/*
					 * Other stuff starts here
					 */
					else if (m.getContent().startsWith("@clear")) {
						Channel c = DiscordClient.get().getChannelByID(m.getChannelID());
						if (null != c) {
							c.getMessages().stream().filter(message -> message.getAuthor().getID()
									.equalsIgnoreCase(DiscordClient.get().getOurUser().getID())).forEach(message -> {
								try {
									Discord4J.logger.debug("Attempting deletion of message {} by \"{}\" ({})", message.getMessageID(), message.getAuthor().getName(), message.getContent());
									DiscordClient.get().deleteMessage(message.getMessageID(), message.getChannelID());
								} catch (IOException e) {
									Discord4J.logger.error("Couldn't delete message {} ({}).", message.getMessageID(), e.getMessage());
								}
							});
						}
					}
					
					/*
					 * Other stuff ends here
					 */
				}
			});
			
			DiscordClient.get().getDispatcher().registerListener(new IListener<InviteReceivedEvent>() {
				@Override public void receive(InviteReceivedEvent event) {
					Invite invite = event.getInvite();
					try {
						Invite.InviteResponse response = invite.accept();
						event.getMessage().reply(String.format("I was invited to join #%s in the %s guild!", response.getChannelName(), response.getGuildName()));
						DiscordClient.get().sendMessage(String.format("Hello, #%s and the \\\"%s\\\" guild!", response.getChannelName(), response.getGuildName()),
								response.getChannelID());
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
			
			DiscordClient.get().getDispatcher().registerListener(new IListener<MentionEvent>() {
				@Override public void receive(MentionEvent event) {
					Message m = event.getMessage();
					String replyString = thoughts(m.getContent());
					addMoodVal(25);
					try {
						m.reply(replyString);
					} catch (IOException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String... args) {
		
		// E-mail and password of a user to be used as a bot
		new MovieBot("dekobemusic@gmail.com", "123abc");

	}
}
