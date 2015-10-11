package modules;

import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie{
	
	private boolean valid;
	private String movie;
	private String title,genre,rated,runtime,released,country,imdbRating,imdbID,imdbLink;
	
	public Movie(String movie){		
		String movieUrl = movie.replaceAll(" ","+");
		this.movie = "http://www.omdbapi.com/?t=" + movieUrl;
		this.create();
	}
	
	private void create(){
		try {
			JsonReader reader = new JsonReader();
			JSONObject json = reader.readJsonFromUrl(this.movie);
			String response = "" + json.get("Response");
			if(response.equals("False")){
				valid = false;
			}else{
				valid = true;
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
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String deAccent(String str) {
	    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
	
	public String getContent(){
		String s =  "\\n**Title: **" + title + "\\n**Genre: **" + genre +
					"\\n**Rated: **" + rated + "\\n**Runetime: **" + runtime + 
					"\\n**Released: **" + released + "\\n**Country: **" + country + 
					"\\n**imdbRating: **" + imdbRating + "\\n" + imdbLink;
		return deAccent(s);
	}

	public boolean isValid(){
		return valid;
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