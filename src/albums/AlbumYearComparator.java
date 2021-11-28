package albums;

import java.util.Comparator;

public class AlbumYearComparator implements Comparator<Album> {

	@Override
	public int compare(Album o1, Album o2) {
		Integer o1y = (Integer) o1.getYear();
		Integer o2y = (Integer) o2.getYear();
		
		return o1y.compareTo(o2y);
	}

}
