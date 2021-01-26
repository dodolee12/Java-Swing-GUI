import static org.junit.Assert.*;

import org.junit.Test;

public class PhotoRatingComparatorTest {
    // test compare on different ratings
    @Test
    public void testCompare1() {
        PhotoRatingComparator test1 = new PhotoRatingComparator();
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-01", 3);
        Photo p2 = new Photo("cat.png", "this is a cat", "1930-03-02", 2);
        assertTrue("Compare method not working", test1.compare(p1, p2) < 0);
    }

    // test compare on same object
    @Test
    public void testCompare2() {
        PhotoRatingComparator test1 = new PhotoRatingComparator();
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-01", 3);
        assertTrue("Compare method not working", test1.compare(p1, p1) == 0);
    }

    // test compare on same caption
    @Test
    public void testCompare3() {
        PhotoRatingComparator test1 = new PhotoRatingComparator();
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-01", 3);
        Photo p2 = new Photo("cat.png", "this is a cat", "1930-03-02", 3);
        assertTrue("Compare method not working", test1.compare(p1, p2) > 0);
    }

    // switching order of compare
    @Test
    public void testCompare4() {
        PhotoRatingComparator test1 = new PhotoRatingComparator();
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-01", 3);
        Photo p2 = new Photo("cat.png", "this is a cat", "1930-03-02", 4);
        assertTrue("Compare method not working", test1.compare(p1, p2) > 0);
    }

}
