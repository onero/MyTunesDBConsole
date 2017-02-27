/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import mytuneswithdbtest.be.Artist;
import mytuneswithdbtest.dal.ArtistDAO;

public class ArtistManager {

    private static ArtistManager instance;

    private final ArtistDAO artistDAO;

    public static ArtistManager getInstance() {
        if (instance == null) {
            instance = new ArtistManager();
        }
        return instance;
    }

    private ArtistManager() {
        artistDAO = ArtistDAO.getInstance();
    }

    /**
     * Get a list of all songs from the DB
     *
     * @return
     */
    public ArrayList<Artist> getArtists() {
        ArrayList<Artist> artistsFromDB = new ArrayList<>();
        try {
            artistsFromDB.addAll(artistDAO.getAllArtists());
        } catch (SQLException e) {
            System.out.println("Couldn't get artists");
            System.out.println(e);
        }
        return artistsFromDB;
    }

    /**
     * Adds a song to the database
     *
     * @param artist
     */
    public void saveArtist(Artist artist) {
        artistDAO.addArtistToDB(artist);
    }

}
