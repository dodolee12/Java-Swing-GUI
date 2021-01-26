
/*
 * Homework 2 Edward Lee edl6zpg
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class LibraryTest {
    // testing if get photos returns arraylist with higher rating photos
    @Test
    public void testGetPhotos1() {
        Photo Photo1 = new Photo("Dog.png", "This is a dog", "1901-01-01", 2);
        Library test = new Library("Cats", 2);
        test.addPhoto(Photo1);
        ArrayList<Photo> expected = new ArrayList<Photo>();
        expected.add(Photo1);
        assertEquals("this should be true", expected, test.getPhotos(1));

    }

    // testing if there are no higher photos, it returns empty arraylist.
    @Test
    public void testGetPhotos2() {
        Photo Photo1 = new Photo("Dog.png", "This is a dog");
        Photo Photo2 = new Photo("Dog.png", "This is a dog", "1901-01-01", 2);
        Library test = new Library("Cats", 2);
        test.addPhoto(Photo1);
        test.addPhoto(Photo2);
        ArrayList<Photo> expected = new ArrayList<Photo>();
        ;
        assertEquals("This should be empty", expected, test.getPhotos(3));

    }

    // testing if it correctly returns photos in the month and year
    @Test
    public void testGetPhotosInMonth1() {
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-02", 2);
        Library test = new Library("Cats", 2);
        test.addPhoto(p1);
        ArrayList<Photo> a = new ArrayList<Photo>();
        a.add(p1);
        assertEquals("There should be one photo", a, test.getPhotosInMonth(3, 1930));
    }

    // testing if it correctly returns empty arraylist
    @Test
    public void testGetPhotosInMonth2() {
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-02", 2);
        Library test = new Library("Cats", 2);
        test.addPhoto(p1);
        ArrayList<Photo> a = new ArrayList<Photo>();
        assertEquals("There should be no photo", a, test.getPhotosInMonth(3, 1932));
    }

    // testing if the month is not valid, it returns null
    @Test
    public void testGetPhotosInMonth3() {
        Library test = new Library("Cats", 2);
        assertNull("this should be null", test.getPhotosInMonth(0, 1932));
    }

    // testing if the year is not valid, it returns null
    @Test
    public void testGetPhotosInMonth4() {
        Library test = new Library("Cats", 2);
        assertNull("this should be null", test.getPhotosInMonth(2, -2));
    }

    // testing if the photos between the dates are returned.
    @Test
    public void testGetPhotosBetween1() {
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-02", 3);
        Photo p2 = new Photo("cat.png", "this is a cat", "1902-04-02", 3);
        Library test = new Library("Animals", 2);
        test.addPhoto(p1);
        test.addPhoto(p2);
        ArrayList<Photo> expected = new ArrayList<Photo>();
        expected.add(p1);
        assertEquals("There should be only one photo", expected, test.getPhotosBetween("1930-01-01", "1931-02-02"));
    }

    // testing if there are no photos between dates, it should return empty arraylist
    @Test
    public void testGetPhotosBetween2() {
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-02", 3);
        Photo p2 = new Photo("cat.png", "this is a cat", "1902-04-02", 2);
        Library test = new Library("Animals", 2);
        test.addPhoto(p1);
        test.addPhoto(p2);
        ArrayList<Photo> expected = new ArrayList<Photo>();
        assertEquals("There should be no photo", expected, test.getPhotosBetween("1932-01-01", "1933-02-02"));
    }

    // testing if the photo is successfully deleted
    @Test
    public void testRemovePhoto1() {
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-02", 3);
        Photo p2 = new Photo("cat.png", "this is a cat", "1902-04-02", 2);
        Library test = new Library("Animals", 2);
        test.addPhoto(p1);
        assertTrue("photo should have been successfully deleted", test.removePhoto(p1));
    }

    // testing if there is no photo, it should not delete and return false
    @Test
    public void testRemovePhoto2() {
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-02", 3);
        Photo p2 = new Photo("cat.png", "this is a cat", "1902-04-02", 2);
        Library test = new Library("Animals", 2);
        test.addPhoto(p1);
        assertFalse("Photo should not have been deleted", test.removePhoto(p2));
    }

    // testing that the photo should be removed from all albums along with library
    @Test
    public void testRemovePhoto3() {
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-02", 3);
        // Photo p2 = new Photo("cat.png", "this is a cat", "1902-04-02", 2);
        Library test = new Library("Animals", 2);
        test.addPhoto(p1);
        test.createAlbum("dogs");
        test.addPhotoToAlbum(p1, "dogs");
        for (Album a : test.getAlbums()) {
            System.out.println(a);
        }
        test.removePhoto(p1);
        for (Album a : test.getAlbums()) {
            assertFalse("Photo should have been deleted from dog album", a.getPhotos().contains(p1));
        }
    }

    // testing if two libraries with different photos have 0 similarity
    @Test
    public void testSimilarity1() {
        Library test1 = new Library("Animals", 2);
        Library test2 = new Library("empty", 3);
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-02", 3);
        Photo p2 = new Photo("cat.png", "this is a cat", "1902-04-02", 2);
        test1.addPhoto(p1);
        test1.addPhoto(p2);
        assertEquals("They should be 100% similar", 0, Library.similarity(test1, test2), 0.01);
    }

    // testing if all of one libraries photos are contained in the other, it returns 1.
    @Test
    public void testSimilarity2() {
        Library test1 = new Library("Animals", 2);
        Library test2 = new Library("Dogs", 3);
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-02", 3);
        Photo p2 = new Photo("cat.png", "this is a cat", "1902-04-02", 2);
        test1.addPhoto(p1);
        test1.addPhoto(p2);
        test2.addPhoto(p1);
        assertEquals("They should be 100% similar", 1, Library.similarity(test1, test2), 0.01);
    }

    // testing if switching up the arguments changes the answer
    @Test
    public void testSimilarity3() {
        Library test1 = new Library("Animals", 2);
        Library test2 = new Library("Dogs", 3);
        Photo p1 = new Photo("dog.png", "this is a dog", "1930-03-02", 3);
        Photo p2 = new Photo("cat.png", "this is a cat", "1902-04-02", 2);
        test1.addPhoto(p1);
        test1.addPhoto(p2);
        test2.addPhoto(p1);
        assertEquals("They should be 100% similar", 1, Library.similarity(test2, test1), 0.01);
    }

}
