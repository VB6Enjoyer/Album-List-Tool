package albums;

import java.io.File;
import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException {
//		Artist EvergreenTerrace = new Artist("Evergreen Terrace");
//		Album unAlbum = new Album("Burned Alive By Time", EvergreenTerrace, 2003, 75, false);
//		
//		System.out.println(unAlbum.getAlbumName());
//		System.out.println(unAlbum.getYear());
//		System.out.println(unAlbum.getAlbumArtist());
//		System.out.println(unAlbum.getRating());
//		System.out.println(unAlbum.getReleaseType());
//		
//		System.out.println();
//		
//		System.out.println(EvergreenTerrace.getArtistName());
		
		String a = "Vaginal Penetration of an Amelus with a Musty Carrot - In Involuntary Abortion We Trust (????) [EP]   (80/100) X";
		System.out.println(a = a.substring(a.indexOf("(????)") + 7));
		System.out.println(a = a.substring(1, a.indexOf("]")));
		
		//List albumes = new List(new File("Albums.txt"));
		//albumes.printFile();
		//albumes.getAllArtists();
	}

}
