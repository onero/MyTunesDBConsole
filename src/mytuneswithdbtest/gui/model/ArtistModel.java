/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.gui.model;

import java.util.ArrayList;
import mytuneswithdbtest.be.Artist;
import mytuneswithdbtest.bll.ArtistManager;

public class ArtistModel {

    private static ArtistModel instance;

    private final ArtistManager artistManager;

    private final ArrayList<Artist> artists;

    public static ArtistModel getInstance() {
        if (instance == null) {
            instance = new ArtistModel();
        }
        return instance;
    }

    private ArtistModel() {
        artistManager = ArtistManager.getInstance();
        artists = new ArrayList<>();
    }

    /**
     * Get artists from DB
     */
    private void updateArtists() {
        artists.clear();
        artists.addAll(artistManager.getArtists());
    }

    /**
     * Return the updated collection of artists
     *
     * @return
     */
    public ArrayList<Artist> getArtists() {
        updateArtists();
        return artists;
    }

    /**
     * Get artist based on the artist ID
     *
     * @param id
     * @return Searched artist
     */
    public Artist getArtistByID(int id) {
        Artist artistWithParsedID;
        for (Artist artist : artists) {
            if (artist.getID() == id) {
                artistWithParsedID = artist;
                return artistWithParsedID;
            }
        }
        return null;
    }

}
