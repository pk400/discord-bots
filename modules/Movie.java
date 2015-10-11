package modules;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import sx.blah.discord.handle.obj.Message;

public class Movie{
	
	private JsonReader reader;
	private String movie;
	private String title,genre,rated,runtime,released,country,imdbRating,imdbID,imdbLink;
	
	public Movie(String movie){		
		
		movie = movie.replaceAll(" ","+");
		System.out.println(movie);
		this.movie = "http://www.omdbapi.com/?t=" + movie;
		
		try {
			this.create(this.movie);
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void create(String movie) throws IOException, JSONException{
		reader = new JsonReader();
		JSONObject json = reader.readJsonFromUrl(movie);
		
		title = "" + json.get("Title");
		genre = "" + json.get("Genre");
		rated = "" + json.get("Rated");
		runtime = "" + json.get("Runtime");
		released = "" + json.get("Released");
		country = "" + json.get("Country");
		imdbRating = "" + json.get("imdbRating");
		imdbID = "" + json.get("imdbID");
		imdbLink = "" + "http:\\/\\/www.imdb.com\\/title\\/" + json.get("imdbID") + "\\/";
		
	}
	
	public String content(){
		return "\\n**Title: **" + title + "\\n**Genre: **" + genre +
				"\\n**Rated: **" + rated + "\\n**Runetime: **" + runtime + 
				"\\n**Released: **" + released + "\\n**Country: **" + country + 
				"\\n**imdbRating: **" + imdbRating + "\\n" + imdbLink;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getImdbLink() {
		return imdbLink;
	}

	public void setImdbLink(String imdbLink) {
		this.imdbLink = imdbLink;
	}

	
}