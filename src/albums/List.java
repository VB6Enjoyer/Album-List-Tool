package albums;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

	// Gets all the unique artists in the list and counts them
	public void getAllArtists() {
		String line, linePrev = null;
		ArrayList<Artist> artists = new ArrayList<Artist>();

		try {
			while ((line = bufferedReader.readLine()) != null) {
				line = line.substring(0, line.indexOf(" - ")); // Isolates the artist from the rest of the string

				if (line.indexOf(" /// ") != -1) {
					line = line.substring(0, line.indexOf(" /// ")); // Separates the first artist from the rest (for
																		// split releases)
				}

				if (line.indexOf("[") > 0) {
					line = line.substring(0, line.indexOf("[")); // Skips alternative names
				}

				if (line.indexOf("{") > 0) {
					line = line.substring(0, line.indexOf("{")); // Skips collaborators (for collaborations)
				}

				line = line.trim();

				if (linePrev != null && !line.toUpperCase().equals(linePrev.toUpperCase())) {
					// System.out.println(line);
					artists.add(new Artist(line));
				}

				linePrev = line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		Collections.sort(artists);

		for (Artist a : artists)
			System.out.println(a.getArtistName());

		System.out.println();
		System.out.println("Total unique artists: " + artists.size());
	}

	// Prints all albums and counts them
	//TODO I don't even know whether all of this works lol
	public void getAllAlbums() {
		String line = null;
		ArrayList<Album> albums = new ArrayList<Album>();
		ArrayList<Artist> artists = new ArrayList<Artist>();
		
		try {
			while((line = bufferedReader.readLine()) != null) {
				String albumName = line.substring(line.indexOf(" - ") + 2); // Separates the album from the artist
				
				// Separates the rest of the info from the album name
				if(albumName.indexOf("(19") != -1)
					albumName = albumName.substring(0, albumName.indexOf("(19"));
				
				if(albumName.indexOf("(20") != -1)
					albumName = albumName.substring(0, albumName.indexOf("(20"));
				
				if(albumName.indexOf("(????)") != -1)
					albumName = albumName.substring(0, albumName.indexOf("(????)"));
				
				albumName.trim();
				
				//-----------------------------------------------------------
				
				String artistName = line.substring(0, line.indexOf(" - ")); // Isolates the artist from the rest of the string
				
				if(line.indexOf(" /// ") != -1) {
					artistName = line.substring(0, line.indexOf(" /// ")); // Separates the first artist from the rest (for split releases)
				}
				
				if(line.indexOf("[") > 0) {
					artistName = line.substring(0, line.indexOf("[")); // Skips alternative names
				}
					
				if(line.indexOf("{") > 0) {
					artistName = line.substring(0, line.indexOf("{")); // Skips collaborators (for collaborations)
				}
				
				if(!artists.contains(new Artist(artistName)))
					artists.add(new Artist(artistName));
				
				//-----------------------------------------------------------
				
				//TODO I should probably optimize this code?
				String albumYear;
				
				albumYear = line.substring(0, 101).trim();
				
				// Isolates the release year from the rest of the info
				if(albumYear.indexOf("(19") != -1) {
					albumYear = albumYear.substring(albumYear.indexOf("(19") + 6);
					albumYear = albumYear.replace("(", "");
					albumYear = albumYear.replace(")", "");
				}
					
				if(albumYear.indexOf("(20") != -1) {
					albumYear = albumYear.substring(albumYear.indexOf("(20") + 6);
					albumYear = albumYear.replace("(", "");
					albumYear = albumYear.replace(")", "");
				}
				
				if(albumYear.indexOf("(????)") != -1)
					albumYear = "-1";
				
				//-----------------------------------------------------------
				
				// Isolates the rating of the album
				String albumRating = line.substring(102, 105).trim();
				
				if(albumRating.indexOf("(") != -1)
					albumRating = albumRating.replace("(", "").trim();
				
				//-----------------------------------------------------------
				
				// Checks whether the album has been reviewed or not
				boolean albumIsReviewed = false;
				
				if(line.charAt(111) == 'X') // 111 because Java has 0-based indexing, and ends all strings with a null character
					albumIsReviewed = true;	// otherwise it'd be 113
				
				//-----------------------------------------------------------
				//TODO I need to make it so all albums without an specified release type are taken as full-lengths by default
				
				String albumReleaseType = null;
				
				//TODO I should find a way to optimize this code
				if(line.indexOf("(19") != -1)
					albumReleaseType = line.substring(line.indexOf("(19") + 7);
					
				if(line.indexOf("(20") != -1)
					albumReleaseType = line.substring(line.indexOf("(20") + 7);
				
				if(line.indexOf("(????)") != -1)
					albumReleaseType = line.substring(line.indexOf("(????)") + 7);
				
				albumReleaseType = albumReleaseType.substring(1, albumReleaseType.indexOf("]")).toUpperCase();
				albumReleaseType = albumReleaseType.replace(" ", "_");
						
				//albums.add();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Collections.sort(artists);
		
		for(Artist a : artists)
			System.out.println(a.getArtistName());
		
		System.out.println();
		System.out.println("Total unique artists: " + artists.size());
	}

	// Shows all albums of the specified release type
	public void getAlbumsByReleaseType(ReleaseType type) {
	}

	// Gets all albums released in the specified year
	public void getAlbumsByYear(int year) {
	}

	// Gets all albums from an artist
	public void getAlbumsByArtist(Artist artist) {
	}

	// Gets all albums between the spcified rating range
	public void getAlbumsByRating(int ratingMin, int ratingMax) {
	}

	// Gets the average rating per album of all years
	public void getAverageRatingPerYear() {
	}

	// Gets the average rating per album of the specified year
	public void getAverageRatingOfYear(int year) {
	}

	// Gets all reviewed albums
	public void getAllReviewedAlbums() {
	}

	// Counts the average number of releases per artist
	public void getAverageAlbumsPerArtist() {
	}

	// Gets the average amount of albums per every discography completed
	public void getAverageAlbumsPerDiscog() {
	}

	// Gets the average amount of albums per release year
	public void getAverageAlbumPerYear() {
	}

	// Gets the average rating of all albums
	public void getAverageRatingPerAlbum() {
	}

	// Gets the average rating per album of all artists
	public void getAverageRatingPerArtist() {
	}

	// Gets the average rating per album of the specified artist
	public void getAverageRatingOfArtist(Artist artist) {
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
