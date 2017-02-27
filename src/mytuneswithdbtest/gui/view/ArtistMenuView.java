/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.gui.view;

import mytuneswithdbtest.be.Artist;
import mytuneswithdbtest.bll.ArtistManager;
import mytuneswithdbtest.gui.model.OptionModel;

public class ArtistMenuView extends AMenuView {

    private static ArtistMenuView instance;

    private final OptionModel optionModel;

    private final ArtistManager artistManager;

    private final String[] options;

    public static ArtistMenuView getInstance() {
        if (instance == null) {
            instance = new ArtistMenuView();
        }
        return instance;
    }

    private ArtistMenuView() {
        optionModel = OptionModel.getInstance();
        options = optionModel.getArtistCategoryOptions();
        artistManager = ArtistManager.getInstance();
    }

    @Override
    public void displayMenu() {
        System.out.println("Àrtist options");
        displayMenuOptions(options);
    }

    @Override
    public void reactToUserInput(int userOption) {
        switch (userOption) {
            case 0:
                MainMenuView.getInstance().displayMenu();
                setDoneWithMenu();
                break;
            case 1:
                getArtists();
                break;
            case 2:
                addArtist();
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Disaply Artists from the DB
     */
    private void getArtists() {
        System.out.println();
        System.out.println("Artists in database:");
        for (Artist artist : artistManager.getArtists()) {
            System.out.println("ID: " + artist.getID() + " - " + artist.getName());
        }
    }

    /**
     * Add a artist to the DB
     */
    private void addArtist() {
        Artist newArtist = promptUserToAddNewSong();
        artistManager.saveArtist(newArtist);
    }

    /**
     * Creates a new song from user input
     *
     * @return
     */
    private Artist promptUserToAddNewSong() {
        System.out.println();
        System.out.println("Add new artist:");
        String name = Console.Readers.readString("Name = ");
        Artist newArtist = new Artist(name);
        return newArtist;
    }

}
