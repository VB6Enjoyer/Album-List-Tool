package albums;

import java.util.Objects;

public class Artist implements Comparable<Artist> {

	protected String artistName;
	protected String additionalInfo;
	
	// Constructors
	//--------------------------------------------------------------------------
	public Artist(String name) {
		setArtistName(name);
	}

	public Artist(String name, String additionalInfo) {
		setArtistName(name);
		setAdditionalInfo(additionalInfo);
	}
	//--------------------------------------------------------------------------

	// Getters
	//--------------------------------------------------------------------------
	public String getArtistName() {
		return artistName;
	}
	
	public String getAdditionalinfo() {
		return additionalInfo;
	}
	//--------------------------------------------------------------------------
	
	// Setters
	//--------------------------------------------------------------------------
	public void setArtistName(String name) {
		this.artistName = name;
	}
	
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	//--------------------------------------------------------------------------
	
	@Override
	public String toString() {
		if(this.getAdditionalinfo() != null)
			return this.getArtistName() + " (" + this.getAdditionalinfo() + ")";
		else {
			return this.getArtistName();
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(artistName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		return Objects.equals(artistName, other.artistName);
	}

	@Override
	public int compareTo(Artist o) {
		return this.getArtistName().compareTo(o.getArtistName());
	}

//	@Override
//	public int compare(Object o1, Object o2) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
