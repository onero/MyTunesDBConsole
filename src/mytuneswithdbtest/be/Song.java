/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.be;

import mytuneswithdbtest.bll.IDFactory;

public class Song {

    private final int ID;

    private final String title;

    private final int artistID;

    private final int categoryID;

    private String fileName;

    private int duration;

    public Song(String title, int artistID, int categoryID) {
        this.ID = IDFactory.getNewID();
        this.title = title;
        this.artistID = artistID;
        this.categoryID = categoryID;
    }

    public Song(int ID, String title, int artistID, int categoryID, String fileName, int duration) {
        this.ID = ID;
        this.title = title;
        this.artistID = artistID;
        this.categoryID = categoryID;
        this.fileName = fileName;
        this.duration = duration;
    }

    /**
     * Get the value of duration
     *
     * @return the value of duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Set the value of duration
     *
     * @param duration new value of duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Get the value of fileName
     *
     * @return the value of fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Set the value of fileName
     *
     * @param fileName new value of fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get the value of categoryID
     *
     * @return the value of categoryID
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * Get the value of ArtistID
     *
     * @return the value of ArtistID
     */
    public int getArtistID() {
        return artistID;
    }

    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the value of ID
     *
     * @return the value of ID
     */
    public int getID() {
        return ID;
    }

}
