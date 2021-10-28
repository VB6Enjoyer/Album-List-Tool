package albums;

import java.io.File;
import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException {
//		String line;
//		FileReader fr = new FileReader(new File("Albums.txt"));
//		BufferedReader br = new BufferedReader(fr);
//		
//		while ((line = br.readLine()) != null) {
//			String albumReleaseType = null;
//			albumReleaseType = line.substring(line.indexOf(" - ") + 3, 100);
//			System.out.println(albumReleaseType);
//			int typeTagIndex = 0;
//
//			if (line.indexOf("(19") != -1) {
//				albumReleaseType = albumReleaseType.substring(albumReleaseType.indexOf("(19") + 6);
//				typeTagIndex = albumReleaseType.indexOf("(19") + 6;
//			}
//
//			if (line.indexOf("(20") != -1) {
//				albumReleaseType = albumReleaseType.substring(albumReleaseType.indexOf("(20") + 6);
//				typeTagIndex = albumReleaseType.indexOf("(20") + 6;
//			}
//
//			if (line.indexOf("(????)") != -1) {
//				typeTagIndex = albumReleaseType.indexOf("(????)") + 6;
//				albumReleaseType = albumReleaseType.substring(albumReleaseType.indexOf("(????)") + 6);
//			}
//
//			// If there's not a [ (which is expected to be followed by the album type, it
//			// defaults to full-length.
//			if (albumReleaseType.charAt(typeTagIndex) != '[') {
//				albumReleaseType = "FULL_LENGTH";
//			} else {
//				albumReleaseType = albumReleaseType.substring(1, albumReleaseType.indexOf("]")).toUpperCase().trim();
//				albumReleaseType = albumReleaseType.replace(" ", "_");
//			}
//		}
		
		List albums = new List(new File("Albums.txt"));
		// albums.printFile();
		// albums.printAllArtists();
		// albums.printAllAlbums();
		 albums.getAlbumsByReleaseType(ReleaseType.FULL_LENGTH);
	}

}
