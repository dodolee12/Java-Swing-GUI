import java.util.Comparator;

public class PhotoCaptionComparator implements Comparator<Photo> {
    /**
     * Compares 2 photos first based on caption and then rating
     * 
     * @param o1 is the first photo to compare
     * @param o2 is the second photo
     * @return a positive number if o1 then o2 is the correct order, a negative number if o2 comes first and 0 if they are
     *         the same
     */
    @Override
    public int compare(Photo o1, Photo o2) {
        if (o1.getCaption().compareTo(o2.getCaption()) != 0) { // checking caption is not the same
            return o1.getCaption().compareTo(o2.getCaption());
        } else {
            if (o1.getRating() > o2.getRating()) { // checking rating and returning based on ordering.
                return -1;
            } else if (o1.getRating() < o2.getRating()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
