
/*
 * Homework 2 Edward Lee edl6zpg
 */

import java.util.ArrayList;
import java.util.HashSet;

public class Library extends PhotoContainer {
    // instance variables as listed in assignment

    // ID of the library
    private final int ID;

    // albums
    private HashSet<Album> albums;

    // constructor with name and id
    public Library(String name, int id) {
        super(name); // invoking superclass constructor
        this.ID = id;
        this.albums = new HashSet<Album>();
    }

    /**
     * accessor for ID
     * 
     * @return ID
     */
    public int getId() {
        return this.ID;
    }

    /**
     * accessor for albums
     * 
     * @return hashset of albums
     */
    public HashSet<Album> getAlbums() {
        return this.albums;
    }

    /**
     * removes the photo if it is inside the library, if its not there it returns false. It also removed all photos inside
     * albums
     * 
     * @param p the photo to delete
     * @return whether the photo was in the library and removed
     */
    public boolean removePhoto(Photo p) {
        for (Album a : this.albums) {
            a.removePhoto(p); // remove all photos inside albums that are equivalent to input
        }

        if (this.hasPhoto(p)) {
            this.photos.remove(p); // remove it from the library
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks whether the id of the object passed through is equal to the library's id
     * 
     * @param o the object to be checked
     * @return returns true if the objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Library) {
            if (((Library) o).getId() == this.ID) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Writes the library out to a String
     * 
     * @return the string representation
     */
    @Override
    public String toString() {
        String listOfPhotos = "";
        for (Photo a : this.photos) {
            listOfPhotos += a.toString() + "\n";
        }
        return "Library with id " + this.ID + " and name " + this.name + " with albums " + this.albums + " with photos:\n"
                + listOfPhotos;
    }

    /**
     * returns an arraylist of common photos
     * 
     * @param a the first library
     * @param b the second library
     * @return the arraylist of common photos
     */
    public static ArrayList<Photo> commonPhotos(Library a, Library b) {
        ArrayList<Photo> common = new ArrayList<Photo>();
        for (Photo photo : a.photos) {
            if (b.hasPhoto(photo)) {
                common.add(photo); // check whether b also has the photos of a
            }
        }
        return common;
    }

    /**
     * returns similarity as a percent between two libraries
     * 
     * @param a the first library
     * @param b the second library
     * @return a double representing the percentage of similar photos
     */
    public static double similarity(Library a, Library b) {
        double numerator = commonPhotos(a, b).size();
        if (a.numPhotos() == 0 || b.numPhotos() == 0) { // check if both libraries have 0 photos
            return 0;
        } else if (a.numPhotos() > b.numPhotos()) { // checking to see which number to divide by
            return numerator / b.numPhotos();
        } else {
            return numerator / a.numPhotos();
        }
    }

    /**
     * check if theres an album already in the library if not add it
     * 
     * @param albumName of the new album
     * @return whether it was successfully added to the library
     */
    public boolean createAlbum(String albumName) {
        for (Album a : this.albums) {
            if (a.getName().equals(albumName)) { // checking if an album is already there
                return false;
            }
        }
        Album newAlbum = new Album(albumName);
        this.albums.add(newAlbum);
        return true;
    }

    /**
     * remove album from the library
     * 
     * @param albumName name of album to be removed
     * @return whether it was successfully removed
     */
    public boolean removeAlbum(String albumName) {
        for (Album a : this.albums) {
            if (a.getName().equals(albumName)) {
                this.albums.remove(a);
                return true;
            }
        }
        return false;
    }

    /**
     * add photo to album in library if the photo is in library and also if the album does not already have the photo
     * 
     * @param p         the photo to add
     * @param albumName the album to add the photo too
     * @return whether the photo was successfully added
     */
    public boolean addPhotoToAlbum(Photo p, String albumName) {
        boolean photoInAlbum = false;
        for (Photo a : this.photos) { // checking if the photo is inside the library
            if (a.equals(p)) {
                photoInAlbum = true;
            }
        }
        if (photoInAlbum) {
            for (Album a : this.albums) {
                if (a.getName().equals(albumName)) {
                    for (Photo b : a.getPhotos()) {// checking if the photo is not already inside the album
                        if (b.equals(p)) {
                            return false;
                        }

                    }
                    a.addPhoto(p); // add photo if not there
                    return true;
                }
            }
            return false;
        }

        else {
            return false;
        }
    }

    /**
     * remove photo from album inside library
     * 
     * @param p         photo to remove
     * @param albumName album to remove the photo from
     * @return whether the photo was successfully removed
     */
    public boolean removePhotoFromAlbum(Photo p, String albumName) {
        for (Album a : this.albums) {
            if (a.getName().equals(albumName)) { // check if the album is inside the library
                for (Photo b : a.getPhotos()) {
                    if (b.equals(p)) { // check if the photo is inside the album
                        a.removePhoto(b);
                        return true;
                    }

                }
                return false;
            }
        }
        return false;
    }

    /**
     * private helper method to get album by name
     * 
     * @param albumName name of album to get
     * @return album with the name
     */
    private Album getAlbumByName(String albumName) {
        for (Album a : this.albums) {
            if (a.getName().equals(albumName)) {
                return a;
            }
        }
        return null;
    }

}
