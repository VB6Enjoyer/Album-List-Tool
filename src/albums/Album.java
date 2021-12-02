package albums;

public class Album implements Comparable<Album>{

	protected String albumName;
	protected Artist albumArtist;
	protected int year;
	protected int rating;
	protected boolean reviewed = false;
	protected ReleaseType releaseType;
	
	// Constructors
	//--------------------------------------------------------------------------
	public Album(String albumName, Artist albumArtist, int year, int rating, boolean reviewed, ReleaseType releaseType) {
		setAlbumName(albumName);
		setAlbumArtist(albumArtist);
		setYear(year);
		setRating(rating);
		setReviewed(reviewed);
		setReleaseType(releaseType);
	}
	
	public Album(String albumName, Artist albumArtist, int year, int rating, boolean reviewed) {
		setAlbumName(albumName);
		setAlbumArtist(albumArtist);
		setYear(year);
		setRating(rating);
		setReviewed(reviewed);
		setReleaseType(ReleaseType.FULL_LENGTH);
	}
	//--------------------------------------------------------------------------
	
	// Setters
	//--------------------------------------------------------------------------
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public void setAlbumArtist(Artist albumArtist) {
		this.albumArtist = albumArtist;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}

	public void setReleaseType(ReleaseType releaseType) {
		this.releaseType = releaseType;
	}
	//--------------------------------------------------------------------------
	
	// Getters
	//--------------------------------------------------------------------------
	public String getAlbumName() {
		return albumName;
	}

	public Artist getAlbumArtist() {
		return albumArtist;
	}

	public int getYear() {
		return year;
	}

	public int getRating() {
		return rating;
	}

	public boolean isReviewed() {
		return reviewed;
	}

	public ReleaseType getReleaseType() {
		return releaseType;
	}
	//--------------------------------------------------------------------------

	// ToString methods
	//--------------------------------------------------------------------------
	// TODO Unknown release dates are printed as -1, they should be printed as "????"
	
	@Override
	public String toString() {
		String reviewMark = " ";
		String albumRating = null;
		String albumType = "";
		String releaseYear = String.valueOf(this.getYear());
		
		if(this.reviewed == true) {
			reviewMark = "REVIEWED";
		}
		
		if(this.getRating() == -1) {
			albumRating = "  N/A   ";
		} else {
			albumRating = "(" + this.getRating() + "/100)";
		}

		if(this.getReleaseType() != ReleaseType.FULL_LENGTH) {
			albumType = "[" + this.getReleaseType().getTypeName().toUpperCase() + "]";
		}
		
		if(releaseYear.equals("-1"))
			releaseYear = "????";
	
		String fullAlbumInfo = this.getAlbumArtist().toString() + " - " + this.getAlbumName() + " (" + releaseYear + ") " + albumType;

		String blankPadding = "";
		for(int i = 0; i < 103 - fullAlbumInfo.length(); i++)
			blankPadding = blankPadding.concat(" ");
		
		return fullAlbumInfo + blankPadding + albumRating + " " + reviewMark;
	}
	
	public String toStringWithoutReleaseType() {
		String reviewMark = " ";
		String albumRating = null;
		String releaseYear = String.valueOf(this.getYear());
		
		if(this.reviewed == true) {
			reviewMark = "REVIEWED";
		}
		
		if(this.getRating() == -1) {
			albumRating = "  N/A   ";
		} else {
			albumRating = "(" + this.getRating() + "/100)";
		}
		
		if(releaseYear.equals("-1"))
			releaseYear = "????";
		
		String fullAlbumInfo = this.getAlbumArtist().toString() + " - " + this.getAlbumName() + " (" + releaseYear + ")";

		String blankPadding = "";
		for(int i = 0; i < 103 - fullAlbumInfo.length(); i++)
			blankPadding = blankPadding.concat(" ");
		
		return fullAlbumInfo + blankPadding + albumRating + " " + reviewMark;
	}
	
	public String toStringWithoutYear() {
		String reviewMark = " ";
		String albumRating = null;
		
		if(this.reviewed == true) {
			reviewMark = "REVIEWED";
		}
		
		if(this.getRating() == -1) {
			albumRating = "  N/A   ";
		} else {
			albumRating = "(" + this.getRating() + "/100)";
		}
		
		String releaseType = "[" + this.getReleaseType().getTypeName() + "]";
		if(releaseType.equals("[Full-length]"))
			releaseType = "";
		
		String fullAlbumInfo = this.getAlbumArtist().toString() + " - " + this.getAlbumName() + " " + releaseType.toUpperCase();

		String blankPadding = "";
		for(int i = 0; i < 103 - fullAlbumInfo.length(); i++)
			blankPadding = blankPadding.concat(" ");
		
		return fullAlbumInfo + blankPadding + albumRating + " " + reviewMark;
	}
	
	public String toStringWithoutArtist() {
		String reviewMark = " ";
		String albumRating = null;
		String albumType = "";
		String releaseYear = String.valueOf(this.getYear());
		
		if(this.reviewed == true) {
			reviewMark = "REVIEWED";
		}
		
		if(this.getRating() == -1) {
			albumRating = "  N/A   ";
		} else {
			albumRating = "(" + this.getRating() + "/100)";
		}

		if(this.getReleaseType() != ReleaseType.FULL_LENGTH) {
			albumType = "[" + this.getReleaseType().getTypeName().toUpperCase() + "]";
		}
		
		if(releaseYear.equals("-1"))
			releaseYear = "????";
	
		String fullAlbumInfo = this.getAlbumName() + " (" + releaseYear + ") " + albumType;

		String blankPadding = "";
		for(int i = 0; i < 103 - fullAlbumInfo.length(); i++)
			blankPadding = blankPadding.concat(" ");
		
		return fullAlbumInfo + blankPadding + albumRating + " " + reviewMark;
	}
	
	public String toStringWithoutReviewed() {
		String albumRating = null;
		String albumType = "";
		String releaseYear = String.valueOf(this.getYear());
		
		if(this.getRating() == -1) {
			albumRating = "  N/A   ";
		} else {
			albumRating = "(" + this.getRating() + "/100)";
		}

		if(this.getReleaseType() != ReleaseType.FULL_LENGTH) {
			albumType = "[" + this.getReleaseType().getTypeName().toUpperCase() + "]";
		}
		
		if(releaseYear.equals("-1"))
			releaseYear = "????";
	
		String fullAlbumInfo = this.getAlbumArtist().toString() + " - " + this.getAlbumName() + " (" + releaseYear + ") " + albumType;

		String blankPadding = "";
		for(int i = 0; i < 103 - fullAlbumInfo.length(); i++)
			blankPadding = blankPadding.concat(" ");
		
		return fullAlbumInfo + blankPadding + albumRating;
	}
	//--------------------------------------------------------------------------

	@Override
	public int compareTo(Album o) {
		return Integer.compare(getYear(), o.getYear());
	}
	
}
