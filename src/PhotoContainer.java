/*
 * Edward Lee Homework 3 edl6zpg
 */

import java.util.ArrayList;

public abstract class PhotoContainer {
    protected String name;
    protected ArrayList<Photo> photos = new ArrayList<Photo>();

    /**
     * Photo container constructor
     * 
     * @param name to give photo container
     */
    public PhotoContainer(String name) {
        this.name = name;
    }

    /**
     * accessor for name
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * accessor for photos
     * 
     * @return arraylist of photos
     */
    public ArrayList<Photo> getPhotos() {
        return this.photos;
    }

    /**
     * mutator for name
     * 
     * @param name to change into
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * add photo to album
     * 
     * @param p photo to be added
     * @return whether that photo was successfully added
     */
    public boolean addPhoto(Photo p) {
        if (p == null) { // condition that p is null
            return false;
        }
        if (this.photos.contains(p)) { // condition that p is already inside album
            return false;
        } else {
            this.photos.add(p); // add photo to album if its not there
            return true;
        }
    }

    /**
     * checks whether the album has the photo
     * 
     * @param p photo to check
     * @return whether the photo is inside the album
     */
    public boolean hasPhoto(Photo p) {
        if (this.photos.contains(p)) { // condition that photo is inside hashset
            return true;
        } else {
            return false;
        }
    }

    /**
     * remove photo from album
     * 
     * @param p photo to remove
     * @return whether it was successfully removed
     */
    public boolean removePhoto(Photo p) {
        if (this.hasPhoto(p)) { // check whether it has the photo
            this.photos.remove(p);
            return true;
        } else {
            return false;
        }
    }

    /**
     * returns how many photos in the album
     * 
     * @return integer that represents how many photos
     */
    public int numPhotos() {
        return this.photos.size();
    }

    /**
     * overrides Objects equals method
     * 
     * @param o object to check
     * @return whether they are equal.
     */
    public boolean equals(Object o) {
        if (o instanceof Album && ((PhotoContainer) o).getName().equals(this.name)) {
            return true;

        } else {
            return false;
        }
    }

    /**
     * toString representation of album
     * 
     * @return a string representation of album
     */
    public String toString() {
        return "This is a Photo Container with name " + this.name + " and photos " + this.photos;
    }

    /**
     * override the hashcode method of object
     * 
     * @return the hashcode of the name
     */
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * returns arraylist of photos in a certain year
     * 
     * @param year to get photos from
     * @return an arraylist of photos in that year
     */
    public ArrayList<Photo> getPhotosInYear(int year) {
        if (year > 9999 || year < 0) { // see if the year is valid
            return null;
        }
        ArrayList<Photo> answer = new ArrayList<Photo>();
        for (Photo a : this.photos) {
            if (DateLibrary.getYear(a.getDateTaken()) == year) { // check if the year matches up
                answer.add(a);
            }
        }
        return answer;
    }

    /**
     * returns arraylist of photos taken in a month and year
     * 
     * @param month to check
     * @param year  of month to check
     * @return arraylist of photos in that same month and year
     */

    public ArrayList<Photo> getPhotosInMonth(int month, int year) {
        if (year > 9999 || year < 0) { // check if year is valid
            return null;
        }
        if (month > 12 || month < 1) { // check if month is valid
            return null;
        }
        ArrayList<Photo> answer = new ArrayList<Photo>();
        for (Photo a : this.photos) {
            if (DateLibrary.getYear(a.getDateTaken()) == year && DateLibrary.getMonth(a.getDateTaken()) == month) {
                answer.add(a);
            }
        }
        return answer;
    }

    /**
     * Return an arraylist of photos between the two dates
     * 
     * @param beginDate the first date
     * @param endDate   the second date
     * @return an arraylist of photos
     */
    public ArrayList<Photo> getPhotosBetween(String beginDate, String endDate) {
        ArrayList<Photo> answer = new ArrayList<Photo>();
        for (Photo a : this.photos) {
            if (DateLibrary.compare(a.getDateTaken(), beginDate) >= 0 && DateLibrary.compare(endDate, a.getDateTaken()) >= 0) {
                // using compare method to check if is before enddate and after begin date
                answer.add(a);
            }
        }
        if (!DateLibrary.isValidDate(beginDate) || !DateLibrary.isValidDate(endDate)) { // checking if begindate and enddates are
                                                                                        // real dates
            return null;
        }
        return answer;
    }

    /**
     * obtains photos inside library above the given rating
     * 
     * @param rating that needs to be met
     * @return arraylist of photos
     */
    public ArrayList<Photo> getPhotos(int rating) {
        // if (rating > 5 || rating < 1) { // if rating is not valid
        // return null;
        // }
        ArrayList<Photo> output = new ArrayList<Photo>();
        for (Photo a : this.photos) {
            if (a.getRating() >= rating) {
                output.add(a); // add all photos that have higher or equal rating of input
            }
        }
        return output;
    }

}
