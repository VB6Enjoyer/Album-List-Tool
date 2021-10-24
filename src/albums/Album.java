package albums;

public class Album {

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

	public String getRating() {
		return rating + "/100";
	}

	public boolean isReviewed() {
		return reviewed;
	}

	public String getReleaseType() {
		return releaseType.getTypeName();
	}
	//--------------------------------------------------------------------------
	
}
