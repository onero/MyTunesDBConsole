/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import mytuneswithdbtest.be.Song;
import mytuneswithdbtest.dal.SongDAO;

public class SongManager {

    private static SongManager instance;

    private final SongDAO songDAO;

    private ArrayList<Song> songsFromDB;

    public static SongManager getInstance() {
        if (instance == null) {
            instance = new SongManager();
        }
        return instance;
    }

    private SongManager() {
        songDAO = SongDAO.getInstance();
    }

    /**
     * Adds a song to the database
     *
     * @param song
     */
    public void saveSong(Song song) {
        songDAO.addSongToDB(song);
    }

    /**
     * Get a list of all songs from the DB
     *
     * @return
     */
    public ArrayList<Song> getSongs() {
        songsFromDB = new ArrayList<>();
        try {
            songsFromDB.addAll(songDAO.getAllSongs());
        } catch (SQLException e) {
            System.out.println("Couldn't get songs");
            System.out.println(e);
        }
        return songsFromDB;
    }

    /**
     * Update song in DB
     *
     * @param song
     */
    public void updateSong(Song song) {
        songDAO.updateSong(song);
    }

    /**
     * Request database to delete song with parsed ID
     *
     * @param id
     */
    public void deleteSong(int id) {
        songDAO.deleteSong(id);
    }

}
