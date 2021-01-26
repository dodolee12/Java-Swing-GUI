import static org.junit.Assert.*;

import org.junit.Test;

public class PhotoCaptionComparatorTest {
    // test compare on different captions
    @Test
    public void testCompare1() {
        PhotoCaptionComparator test1 = new PhotoCaptionComparator();
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-01", 3);
        Photo p2 = new Photo("cat.png", "this is a cat", "1930-03-02", 2);
        assertTrue("Compare method not working", test1.compare(p1, p2) > 0);
    }

    // test compare on itself
    @Test
    public void testCompare2() {
        PhotoCaptionComparator test1 = new PhotoCaptionComparator();
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-01", 3);
        assertTrue("Compare method not working", test1.compare(p1, p1) == 0);
    }

    // test compare on same captions
    @Test
    public void testCompare3() {
        PhotoCaptionComparator test1 = new PhotoCaptionComparator();
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-01", 3);
        Photo p2 = new Photo("dog.png", "this is a dog", "1930-03-02", 2);
        assertTrue("Compare method not working", test1.compare(p1, p2) < 0);
    }

    // testing compare on same captions but switching up order
    @Test
    public void testCompare4() {
        PhotoCaptionComparator test1 = new PhotoCaptionComparator();
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-01", 3);
        Photo p2 = new Photo("dog.png", "this is a dog", "1930-03-02", 2);
        assertTrue("Compare method not working", test1.compare(p2, p1) > 0);
    }
}
