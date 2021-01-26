import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/*
 * Homework 1 Edward Lee, edl6zpg
 */

/*
 * Homework 2 Edward Lee edl6zpg
 */

/*
 * Homework 4 Edward Lee edl6zpg
 */

public class Photo implements Comparable<Photo> {
    // instance variables listed in assignment
    // Caption of the Photo
    private final String CAPTION;
    // Filename of the Photo
    private final String FILENAME;
    // Rating of the Photo
    private int rating;
    // Date taken
    private final String dateTaken;
    // image data
    private BufferedImage imagedata;

    /**
     * Constructor with two parameters with default rating and dateTaken
     * 
     * @param FILENAME to be given
     * @param CAPTION  to be given
     */
    public Photo(String FILENAME, String CAPTION) {
        this.FILENAME = FILENAME;
        this.CAPTION = CAPTION;
        this.rating = 1;
        this.dateTaken = "1901-01-01";
    }

    /**
     * Constructor with all parameters
     * 
     * @param FILENAME  of photo
     * @param CAPTION   of photo
     * @param rating    of photo
     * @param dateTaken of photo
     */
    public Photo(String FILENAME, String CAPTION, String dateTaken, int rating) {
        this.FILENAME = FILENAME;
        this.CAPTION = CAPTION;
        if (rating >= 1 && rating <= 5) { // checking if rating is valid
            this.rating = rating;
        } else {
            this.rating = 1;
        }
        if (DateLibrary.isValidDate(dateTaken)) { // checking if date is valid
            this.dateTaken = dateTaken;
        } else {
            this.dateTaken = "1901-01-01";
        }
    }

    /**
     * accessor method for caption
     * 
     * @return caption
     */
    public String getCaption() {
        return this.CAPTION;
    }

    /**
     * accessor method for filename
     * 
     * @return filename
     */
    public String getFilename() {
        return this.FILENAME;
    }

    /**
     * accessor method for rating
     * 
     * @return rating
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * accessor method for date taken
     * 
     * @return date taken
     */
    public String getDateTaken() {
        return this.dateTaken;
    }

    /**
     * accessor for image data
     * 
     * @return
     */
    public BufferedImage getImageData() {
        return this.imagedata;
    }

    /**
     * overrides the hashcode of the object class and replaces it with the hashcode of the filename
     * 
     * @return hashcode of filename
     */
    public int hashCode() {
        return this.FILENAME.hashCode();
    }

    /**
     * set rating if valid
     * 
     * @param newRating to be set
     * @return whether it was successfully changed
     */
    public boolean setRating(int newRating) {
        if (newRating != this.rating && newRating >= 1 && newRating <= 5) { // checking if new rating is not the same and a valid
                                                                            // rating
            this.rating = newRating;
            return true;
        } else {
            return false;
        }
    }

    /**
     * image data setter
     * 
     * @param image image to set
     */
    public void setImageData(BufferedImage image) {
        this.imagedata = image;
    }

    /**
     * overrides object equals method
     * 
     * @param o the object to be checked
     * @return whether it is equal or not
     */
    public boolean equals(Object o) {
        if (o instanceof Photo) {
            if (((Photo) o).getFilename().equals(this.FILENAME) && ((Photo) o).getCaption().equals(this.CAPTION)) {
                return true;
            } else {
                return false;
            }
        }

        else {
            return false;
        }
    }

    /**
     * a toString implementation to override object class
     * 
     * @return the string representation of photo
     */
    public String toString() {
        return "Photo with FILENAME " + this.FILENAME + " with CAPTION " + this.CAPTION + " with rating " + this.rating;
    }

    // main method testing of photo
    public static void main(String[] args) {
        Photo Photo1 = new Photo("Dog.png", "This is a dog"); // creating a photo "Dog" with CAPTION "This is a dog"
        Photo Photo2 = new Photo("Dog.png", "This is a dog", "1901-01-01", 2);// creating a photo "Dog" with CAPTION "This is a
                                                                              // dog" with rating
        // 2
        Photo Photo3 = new Photo("Cat.png", "This is a cat"); // creating a photo "Cat" with CAPTION "THis is a cat"
        System.out.println(Photo1.getRating()); // should print rating of photo1 which should be 1
        System.out.println(Photo1.setRating(3)); // should successfully change rating of photo 1 and print true
        System.out.println(Photo1.getRating()); // should print rating of photo1 after changing it and should print 3
        System.out.println(Photo2.getRating()); // should print rating of photo2 which should be 2
        System.out.println(Photo2.setRating(6)); // should fail to change rating of photo 2 as 6 is out of bounds and should print
                                                 // false
        System.out.println(Photo2.getRating()); // should get the rating after failing to change it to 6 and should print 2
        System.out.println(Photo1.equals(Photo2)); // should print true as photo1 has the same CAPTIONs and FILENAME as photo2
        System.out.println(Photo1.equals(Photo3)); // should print false as photo1 and photo 3 do not have the same CAPTIONs nor
                                                   // FILENAME.

    }

    /**
     * compare to method using date taken and then caption afterwards if the date taken is the same
     * 
     * @param photo to compare to
     * @return a positive number if the first object is higher is natural ordering and vice versa
     */
    @Override
    public int compareTo(Photo o) {
        if (DateLibrary.compare(this.dateTaken, o.dateTaken) != 0) {
            return DateLibrary.compare(this.dateTaken, o.dateTaken); // return compare between dates that already incorporates the
                                                                     // compareTo
        } else {
            return this.CAPTION.compareTo(o.CAPTION); // comparing captions and returning
        }
    }

    /**
     * load the image data photo data field
     * 
     * @param filename the filename of the photo
     * @return whether it was successfully loaded
     */
    public boolean loadImageData(String filename) {
        try {
            this.imagedata = ImageIO.read(new File(filename)); // read image data from string(from hw 4 doc)
            return true;
        } catch (Exception e) { // i dont need to catch specific exceptions because it doesnt matter how it fails, if it fails it
                                // should return false
            return false;
        }
    }
}
