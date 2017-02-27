/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.gui.model;

import java.util.ArrayList;
import mytuneswithdbtest.be.Song;
import mytuneswithdbtest.bll.SongManager;

public class SongModel {

    private static SongModel instance;

    private final SongManager songManager;

    private final ArrayList<Song> songs;

    public static SongModel getInstance() {
        if (instance == null) {
            instance = new SongModel();
        }
        return instance;
    }

    private SongModel() {
        songManager = SongManager.getInstance();
        songs = new ArrayList<>();
    }

    /**
     * Get songs from DB
     */
    private void updateSongs() {
        songs.clear();
        songs.addAll(songManager.getSongs());
    }

    /**
     * Return the updated collection of songs
     *
     * @return
     */
    public ArrayList<Song> getSongs() {
        updateSongs();
        return songs;
    }

    /**
     * Save parsed song to DB
     *
     * @param songToSave
     */
    public void saveSong(Song songToSave) {
        songManager.saveSong(songToSave);
    }

    /**
     * Update parsed song in DB
     *
     * @param songToUpdate
     */
    public void updateSong(Song songToUpdate) {
        songManager.updateSong(songToUpdate);
    }

    /**
     * Delete song with matching ID from DB
     *
     * @param id
     */
    public void deleteSong(int id) {
        songManager.deleteSong(id);
    }

}
