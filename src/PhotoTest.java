import static org.junit.Assert.*;

import org.junit.Test;

public class PhotoTest {
    // test compare on different date
    @Test
    public void testCompareTo1() {
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-01", 3);
        Photo p2 = new Photo("cat.png", "this is a cat", "1930-03-02", 2);
        assertTrue("CompareTo method not working", p1.compareTo(p2) < 0);
    }

    // test compare on same dates but different rating
    @Test
    public void testCompareTo2() {
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-02", 3);
        Photo p2 = new Photo("cat.png", "this is a cat", "1930-03-02", 2);
        assertTrue("CompareTo method not working", p1.compareTo(p2) > 0);
    }
}
