/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.gui.view;

import mytuneswithdbtest.be.Song;
import mytuneswithdbtest.bll.SongManager;

public class MainView {

    private boolean isUserDone;

    private final SongManager songManager;

    public MainView() {
        songManager = SongManager.getInstance();
        isUserDone = false;
    }

    /**
     * Start the program
     */
    public void startProgram() {
        System.out.println("Welcome to this awesome application!");
        System.out.println();
        promptUserForOption();
    }

    /**
     * List options for the user
     */
    private void listOptions() {
        System.out.println("You have the following options:");
        System.out.println("0: Exit program");
        System.out.println("1: Get songs in database");
        System.out.println("2: Add song to database");
        System.out.println("3: Update song in database");
        System.out.println("4: Delete song from database");
    }

    /**
     * Prompt user for option
     */
    private void promptUserForOption() {
        while (!isUserDone) {
            System.out.println();
            listOptions();
            System.out.println();
            String option = Console.Readers.readString("Type your option: ");
            reactToUserInput(option);
        }
    }

    /**
     * Execute option depending on user choice
     */
    private void reactToUserInput(String option) {
        switch (option) {
            case "0":
                System.out.println();
                System.out.println("Terminating program.");
                System.out.println("Bye!");
                isUserDone = true;
                break;
            case "1":
                displaySongs();
                break;
            case "2":
                addSong();
                break;
            case "3":
                promptUserToUpdateSong();
                break;
            case "4":
                promptUserToDeleteSong();
                break;
            default:
                System.out.println();
                System.out.println("You must pick a valid number");
        }
    }

    /**
     * Add a song to the DB
     */
    public void addSong() {
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

}
