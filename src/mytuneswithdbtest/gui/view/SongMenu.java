/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.gui.view;

import mytuneswithdbtest.be.Song;
import mytuneswithdbtest.be.abstractions.AMenu;
import mytuneswithdbtest.bll.SongManager;
import mytuneswithdbtest.gui.model.OptionModel;

public class SongMenu extends AMenu {

    private static SongMenu instance;

    private final OptionModel optionModel;

    private final SongManager songManager;

    private String[] options;

    public static SongMenu getInstance() {
        if (instance == null) {
            instance = new SongMenu();
        }
        return instance;
    }

    private SongMenu() {
        optionModel = OptionModel.getInstance();
        songManager = SongManager.getInstance();
        options = optionModel.getSongCategoryOptions();
    }

    @Override
    public void displayMenu() {
        System.out.println("Song options");
        displayMenuOptions(options);
    }

    /**
     * Add a song to the DB
     */
    private void addSong() {
        Song newSong = promptUserToAddNewSong();
        songManager.saveSong(newSong);
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
        for (Song song : songManager.getSongs()) {
            System.out.println("ID: " + song.getID() + " - " + song.getTitle());
        }
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
        songManager.updateSong(updatedSong);
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
        songManager.deleteSong(id);
    }

    @Override
    public void reactToUserInput(int userOption) {
        switch (userOption) {
            case 0:
                MainMenu.getInstance().displayMenu();
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

}
