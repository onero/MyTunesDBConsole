/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.gui.view;

import java.util.ArrayList;
import mytuneswithdbtest.be.Artist;
import mytuneswithdbtest.be.Song;
import mytuneswithdbtest.gui.model.ArtistModel;
import mytuneswithdbtest.gui.model.OptionModel;
import mytuneswithdbtest.gui.model.SongModel;

public class SongMenuView extends AMenuView {

    private static SongMenuView instance;

    private final OptionModel optionModel;

    private final SongModel songModel;

    private final ArtistModel artistModel;

    private final String[] options;

    public static SongMenuView getInstance() {
        if (instance == null) {
            instance = new SongMenuView();
        }
        return instance;
    }

    private SongMenuView() {
        optionModel = OptionModel.getInstance();
        options = optionModel.getSongCategoryOptions();
        songModel = SongModel.getInstance();
        artistModel = ArtistModel.getInstance();
    }

    @Override
    public void displayMenu() {
        System.out.println("Song options");
        displayMenuOptions(options);
    }

    /**
     * According to item selected in menu options, carry out action
     *
     * @param userOption
     */
    @Override
    public void reactToUserInput(int userOption) {
        switch (userOption) {
            case 0:
                MainMenuView.getInstance().displayMenu();
                setDoneWithMenu();
                break;
            case 1:
                displaySongs();
                break;
            case 2:
                addSong();
                break;
            case 3:
                promptUserToUpdateSong();
                break;
            case 4:
                promptUserToDeleteSong();
                break;
            default:
                invalidNumber();
                System.out.println();
        }
    }

    /**
     * Add a song to the DB
     */
    private void addSong() {
        Song newSong = promptUserToAddNewSong();
        songModel.saveSong(newSong);
    }

    /**
     * Creates a new song from user input
     *
     * @return
     */
    private Song promptUserToAddNewSong() {
        System.out.println();
        System.out.println("Add new song:");
        String name = Console.Readers.readString("Name = ");
        int artistID = Console.Readers.readInt("Artist ID = ");
        int categoryID = Console.Readers.readInt("Category ID = ");
        String fileName = Console.Readers.readString("Filename = ");
        int duration = Console.Readers.readInt("Duration = ");
        Song newSong = new Song(name, artistID, categoryID);
        newSong.setDuration(duration);
        newSong.setFileName(fileName);
        return newSong;
    }

    /**
     * Display songs from DB
     */
    private void displaySongs() {
        System.out.println();
        System.out.println("Songs in database:");
        ArrayList<Song> songs = getSongData();
        for (Song song : songs) {
            Artist songArtist = artistModel.getArtistByID(song.getArtistID());
            System.out.println("ID: " + song.getID() + " - "
                    + song.getTitle()
                    + " by " + songArtist.getName());
        }
    }

    /**
     * Get relevant song data from database
     *
     * @return
     */
    private ArrayList<Song> getSongData() {
        ArrayList<Song> songs = songModel.getSongs();
        //Make sure artists are updated at least once from database
        artistModel.getArtists();
        return songs;
    }

    /**
     * Prompt user to update song
     */
    private void promptUserToUpdateSong() {
        System.out.println();
        int songID = Console.Readers.readInt("Type ID of song to update: ");
        String name = Console.Readers.readString("Name = ");
        int artistID = Console.Readers.readInt("Artist ID = ");
        int categoryID = Console.Readers.readInt("Category ID = ");
        String fileName = Console.Readers.readString("Filename = ");
        int duration = Console.Readers.readInt("Duration = ");
        Song updatedSong = new Song(songID, name, artistID, categoryID, fileName, duration);
        updateSong(updatedSong);
    }

    /**
     * Update parsedsong in DB
     */
    private void updateSong(Song updatedSong) {
        songModel.updateSong(updatedSong);
    }

    /**
     * Prompt user for which song to delete
     */
    private void promptUserToDeleteSong() {
        System.out.println();
        int songID = Console.Readers.readInt("Type ID of song to remove: ");
        deleteSong(songID);
    }

    /**
     * Delete parsed song from DB
     */
    private void deleteSong(int id) {
        songModel.deleteSong(id);
    }

}
