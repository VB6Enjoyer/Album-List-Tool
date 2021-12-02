package albums;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class List {
	FileReader fileReader;
	BufferedReader bufferedReader;

	// Constructors
	// --------------------------------------------------------------------------
	public List(File listaTxt) throws IOException {
		this.setFileReader(listaTxt);
		;
		this.setBufferedReader(fileReader);
	}
	// --------------------------------------------------------------------------

	// Functions
	// --------------------------------------------------------------------------

	//TODO Multiple search functions
	//TODO Need to find a fix for split releases showing up twice
	
	// Prints the entire file.
	public void printFile() {
		try {
			String line;

			while ((line = bufferedReader.readLine()) != null)
				System.out.println(line);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Gets all the unique artists in the list
	
	public ArrayList<Artist> getAllArtists() {
		ArrayList<Album> albums = this.getAllAlbums();
		ArrayList<Artist> artists = new ArrayList<Artist>();
		int i = 0;
		
		artists.add(albums.get(0).getAlbumArtist());
		
		for(Album a : albums) {
			if(!a.getAlbumArtist().getArtistName().equals(artists.get(i).getArtistName())) {
				artists.add(a.getAlbumArtist());
				i++;
			}
		}
		
		return artists;
	}
	
	// Prints and counts all unique artists
	public void printAllArtists() {
		ArrayList<Artist> artists = this.getAllArtists();
		
		Collections.sort(artists);

		for (Artist a : artists)
			System.out.println(a.getArtistName());

		System.out.println();
		System.out.println("Total unique artists: " + artists.size());
	}

	// Prints all albums and counts them
	public ArrayList<Album> getAllAlbums() {
		String line = null;
		ArrayList<Album> albums = new ArrayList<Album>();
		Artist artist = null;
		int counter = 0;
		
		try {
			while((line = bufferedReader.readLine()) != null) {
				String albumName = line.substring(line.indexOf(" - ") + 2); // Separates the album from the artist
				
				// Separates the rest of the info from the album name
				if(albumName.indexOf("(19") != -1)
					albumName = albumName.substring(0, albumName.indexOf("(19")).trim();
				
				if(albumName.indexOf("(20") != -1)
					albumName = albumName.substring(0, albumName.indexOf("(20")).trim();
				
				if(albumName.indexOf("(????)") != -1)
					albumName = albumName.substring(0, albumName.indexOf("(????)")).trim();
				
				//-----------------------------------------------------------
				
				String artistName = line.substring(0, line.indexOf(" - ")); // Isolates the artist from the rest of the string
				
				if(artistName.indexOf(" /// ") != -1) {
					artistName = artistName.substring(0, line.indexOf(" /// ")); // Separates the first artist from the rest (for split releases)
				}
				
				if(artistName.indexOf("[") > 0) {
					artistName = artistName.substring(0, line.indexOf("[")); // Skips alternative names
				}
					
				if(artistName.indexOf("{") > 0) {
					artistName = artistName.substring(0, line.indexOf("{")); // Skips collaborators (for collaborations)
				}
				
				if(counter > 0 && artistName.equals(albums.get(counter-1).getAlbumArtist().getArtistName())) {
					artist = albums.get(counter-1).getAlbumArtist();
				} else {
					artist = new Artist(artistName);
				}
						
				//-----------------------------------------------------------
				
				//TODO Might want to implement a mechanism to allow multiple release dates
				//TODO I should probably optimize this code?
				String albumYear = line.substring(line.indexOf(" - ") + 3, 100).trim();
				
				// Isolates the release year from the rest of the info
				if(albumYear.indexOf("(19") != -1) {
					albumYear = albumYear.substring(albumYear.indexOf("(19") + 1, albumYear.indexOf("(19") + 5);
				}
					
				if(albumYear.indexOf("(20") != -1) {
					albumYear = albumYear.substring(albumYear.indexOf("(20") + 1, albumYear.indexOf("(20") + 5);
				}
				
				if(albumYear.indexOf("(????)") != -1)
					albumYear = "-1";
				
				//-----------------------------------------------------------
				
				// Isolates the rating of the album
				String albumRating = null; 
				
				if(line.indexOf("/100)") != -1) {
					albumRating = line.substring(line.indexOf("/100") - 3, line.indexOf("/100)")).trim();
					albumRating = albumRating.replace("(", "").trim();
				} else {
					albumRating = "-1"; // If the rating is "N/A" then it sets it to -1
				}
					
				//-----------------------------------------------------------
				
				// Checks whether the album has been reviewed or not
				boolean albumIsReviewed = false;
				
				if(line.indexOf("/100) X") != -1) // 111 because Java has 0-based indexing, and ends all strings with a null character
					albumIsReviewed = true;	// otherwise it'd be 113
				
				//-----------------------------------------------------------
				
				// Sets the type of release based on what's on the string, or defaults to full-length
				String albumReleaseType = line.substring(line.indexOf(" - ") + 3,  100);
				
				//TODO I should find a way to optimize this code
				if(albumReleaseType.indexOf("(19") != -1) {
					albumReleaseType = albumReleaseType.substring(albumReleaseType.indexOf("(19") + 7);
				}
					
				if(albumReleaseType.indexOf("(20") != -1) {
					albumReleaseType = albumReleaseType.substring(albumReleaseType.indexOf("(20") + 7);
				}
					
				if(albumReleaseType.indexOf("(????)") != -1) {
					albumReleaseType = albumReleaseType.substring(albumReleaseType.indexOf("(????)") + 7);
				}
				
				// If there's not a [ (which is expected to be followed by the album type, it defaults to full-length.
				if(albumReleaseType.charAt(0) != '[') {
					albumReleaseType = "FULL_LENGTH";
				} else {
					albumReleaseType = albumReleaseType.substring(1, albumReleaseType.indexOf("]")).toUpperCase().trim();
					albumReleaseType = albumReleaseType.replace(" ", "_");
				}
				
				//-----------------------------------------------------------
				
				albums.add(new Album(albumName, artist, Integer.parseInt(albumYear), Integer.parseInt(albumRating), albumIsReviewed, ReleaseType.valueOf(albumReleaseType)));
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return albums;
	}
	
	// Prints all albums
	public void printAllAlbums() {
		ArrayList<Album> albums = this.getAllAlbums();
		
		for (Album a : albums)
			System.out.println(a.toString());

		System.out.println();
		System.out.println("Total releases: " + albums.size());
	}

	// Shows all albums of the specified release type
	public void getAlbumsByReleaseType(ReleaseType type) { // TODO Probably add a method that lets the user input a String instead of a ReleaseType, maybe let them pick
		ArrayList<Album> albums = this.getAllAlbums();
		albums.removeIf(album -> album.getReleaseType() != type);
		
		for (Album a : albums)
			System.out.println(a.toStringWithoutReleaseType());
		
		System.out.println();
		System.out.println("Total " + type.getTypeName() + " releases: " + albums.size()); //TODO Move this sort of stuff to another function
		//TODO Splits are counted twice and appear twice
	}

	// Gets all albums released in the specified year
	public void getAlbumsByYear(int year) {
		ArrayList<Album> albums = this.getAllAlbums();
		albums.removeIf(album -> album.getYear() != year);
		
		int averageRating = 0;
		
		for (Album a : albums) {
			System.out.println(a.toStringWithoutYear());
			averageRating += a.getRating();
		}
			
		System.out.println();
		System.out.println("Total releases from " + year + ": " + albums.size()); //TODO Move this sort of stuff to another function
		System.out.println();
		System.out.println("The average album rating from " + year + " is " + averageRating / albums.size());
	}

	// Gets all albums from an artist
	public void getAlbumsByArtist(String artist) {
		ArrayList<Album> albums = this.getAllAlbums();
		albums.removeIf(album -> !album.getAlbumArtist().getArtistName().equals(artist));
		
		for (Album a : albums)
			System.out.println(a.toStringWithoutArtist());
		
		System.out.println();
		System.out.println("Total releases by " + artist + ": " + albums.size());
	}

	// Gets all albums between the specified rating range
	public void getAlbumsByRating(int ratingMin, int ratingMax) {
		ArrayList<Album> albums = this.getAllAlbums();
		albums.removeIf(album -> album.getRating() < Math.min(ratingMin, ratingMax) || album.getRating() > Math.max(ratingMin, ratingMax));
		
		for (Album a : albums)
			System.out.println(a.toString());
		
		System.out.println();
		System.out.println("Total releases with a rating between " + Math.min(ratingMin, ratingMax) + " and " + Math.max(ratingMin, ratingMax) + ": " + albums.size());
	}
	
	// Gets all albums with a certain rating
	public void getAlbumsByRating(int rating) {
		ArrayList<Album> albums = this.getAllAlbums();
		albums.removeIf(album -> album.getRating() != rating);
			
		for (Album a : albums)
			System.out.println(a.toString());
			
		System.out.println();
		System.out.println("Total releases with a rating of " + rating + "/100: " + albums.size());
	}
	
	// Gets the average album rating across all albums
	public void getAverageAlbumRating() {
		ArrayList<Album> albums = this.getAllAlbums();
		int averageRating = 0;
			
		Collections.sort(albums, new AlbumYearComparator());
			
		for (Album a : albums)
			averageRating += a.getRating();
			
		System.out.println("The average album rating (across " + albums.size() + " albums) is " + averageRating / albums.size() + "/100");
	}
	
	// Gets the average rating per album of all years
	// TODO Gotta find a way to order this by rating, maybe through a parameter.
	public void getAverageRatingPerYear() {
		ArrayList<Album> albums = this.getAllAlbums();
		int averageRating = 0, year, auxYear, albumCounter = 0;
		
		Collections.sort(albums, new AlbumYearComparator());
		year = albums.get(0).getYear();
		
		for (Album a : albums) {
			auxYear = a.getYear();
			
			if(auxYear != year) {
				if(year == -1)
					System.out.println("Unknown: " + averageRating / albumCounter + " (" + albumCounter + " albums)");
				else
					System.out.println(year + ": " + averageRating / albumCounter + " (" + albumCounter + " albums)");
				
				year = a.getYear();
				albumCounter = 0;
				averageRating = 0;
			}
			
			albumCounter++;
			
			averageRating += a.getRating();
		}
	}

	// Gets all reviewed albums
	public void getAllReviewedAlbums() {
		ArrayList<Album> albums = this.getAllAlbums();
		albums.removeIf(album -> !album.isReviewed());
		
		int averageRating = 0;
		
		for (Album a : albums) {
			System.out.println(a.toStringWithoutReviewed());
			averageRating += a.getRating();
		}
			
		System.out.println();
		System.out.println("Total releases reviewed: " + albums.size());
		System.out.println();
		System.out.println("The average rating for reviewed albums is " + averageRating / albums.size());
	}

	// Counts the average number of releases per every artist
	public void getAverageAlbumsPerArtist() {
		ArrayList<Album> albums = this.getAllAlbums();
		ArrayList<Artist> artists = new ArrayList<Artist>();
		
		// TODO this entire code is literally the getAllArtists() function, but for whatever reason
		// the damn ArrayLists aren't working properly.
		int i = 0;
		
		artists.add(albums.get(0).getAlbumArtist());
		
		for(Album a : albums) {
			if(!a.getAlbumArtist().getArtistName().equals(artists.get(i).getArtistName())) {
				artists.add(a.getAlbumArtist());
				i++;
			}
		}
		
		float avg = (float) albums.size() / artists.size();
		DecimalFormat decFormat = new DecimalFormat("#.##");
		
		System.out.println("You have listened to " + decFormat.format(avg) + " albums per artist.");
	}

	// Gets the average amount of albums per year
	public void getAverageAlbumsPerYear() {
		ArrayList<Album> albums = this.getAllAlbums();
		int years = 0, lastYear = 0, currentYear = 0;
		
		Collections.sort(albums, new AlbumYearComparator());
		lastYear = albums.get(0).getYear();
		
		for (Album a : albums) {
			currentYear = a.getYear();
			
			if(currentYear != lastYear) {
				years++;
				lastYear = currentYear;
			}
		}
		
		float avg = (float) albums.size() / years;
		DecimalFormat decFormat = new DecimalFormat("#.##");
			
		System.out.println("The average number of releases per year is " + decFormat.format(avg));
	}

	// Gets the average rating per album of all artists
	public void getAverageRatingPerArtist() {
	}

	// Gets the average rating per album of the specified artist
	public void getAverageRatingOfArtist(Artist artist) {
	}
	
	// Gets the average amount of albums per every discography completed
	public void getAverageAlbumsPerDiscog() {
	}

	// Counts all albums by type of release
	public void countAlbumsByType() {
	}

	// Counts all albums per year
	public void countAlbumsPerYear() {
	}

	// Counts all albums per decade
	public void countAlbumsPerDecade() {
	}

	// Counts all albums released in a certain year
	public void countAlbumsFromYear(int year) {
	}

	// Counts the amount of complete discographies
	public void countCompletedDiscogs() {
	}

	// Finds the specified artist
	public void findArtist(Artist artist) {
	}

	// Finds the specified artist by its name
	public void findArtistByName(String line) {
	}

	// Finds the specified album
	public void findAlbum(Album album) {
	}

	// Finds the specified album by its name
	public void findAlbumByName(String line) {
	}
	// --------------------------------------------------------------------------
	
	// Auxiliary methods
	// --------------------------------------------------------------------------
	
	/* TODO Doesn't work properly and returns -1 beecause of the way the comparator works
	   So I might want to create a new comparator or find a workaround for this, or just delete it.
	*/
	public int findOldestAlbum() {
		ArrayList<Album> albums = this.getAllAlbums();
		int year;
		
		Collections.sort(albums, new AlbumYearComparator());
		year = albums.get(0).getYear();
		
		return year;
	}
	// --------------------------------------------------------------------------

	// Setters
	// --------------------------------------------------------------------------
	public void setFileReader(File file) throws FileNotFoundException {
		this.fileReader = new FileReader(file);
	}

	public void setBufferedReader(FileReader fr) {
		this.bufferedReader = new BufferedReader(fr);
	}
	// --------------------------------------------------------------------------
}

// Old code, in case it needs to be reused
//--------------------------------------------------------------------------

//public ArrayList<Artist> getAllArtists() {
//String line, linePrev = null;
//ArrayList<Artist> artists = new ArrayList<Artist>();
//
//try {
//	while ((line = bufferedReader.readLine()) != null) {
//		line = line.substring(0, line.indexOf(" - ")); // Isolates the artist from the rest of the string
//
//		if (line.indexOf(" /// ") != -1) {
//			line = line.substring(0, line.indexOf(" /// ")); // Separates the first artist from the rest (for
//																// split releases)
//		}
//
//		if (line.indexOf("[") > 0) {
//			line = line.substring(0, line.indexOf("[")); // Skips alternative names
//		}
//
//		if (line.indexOf("{") > 0) {
//			line = line.substring(0, line.indexOf("{")); // Skips collaborators (for collaborations)
//		}
//
//		line = line.trim();
//
//		if (linePrev != null && !line.toUpperCase().equals(linePrev.toUpperCase())) {
//			// System.out.println(line);
//			artists.add(new Artist(line));
//		}
//
//		linePrev = line;
//	}
//} catch (IOException e) {
//	e.printStackTrace();
//}
//
//return artists;
//}
