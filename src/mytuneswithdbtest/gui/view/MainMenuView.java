/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.gui.view;

import mytuneswithdbtest.gui.model.OptionModel;

public class MainMenuView extends AMenuView {

    private static MainMenuView instance;

    private final OptionModel optionModel;

    private final String[] options;

    public static MainMenuView getInstance() {
        if (instance == null) {
            instance = new MainMenuView();
        }
        return instance;
    }

    private MainMenuView() {
        optionModel = OptionModel.getInstance();
        options = optionModel.getMainCategoryOptions();
    }

    @Override
    public void displayMenu() {
        System.out.println();
        System.out.println("Welcome to this awesome application!");
        System.out.println();
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
                terminateProgram();
                setDoneWithMenu();
                break;
            case 1:
                SongMenuView.getInstance().displayMenu();
                setDoneWithMenu();
                break;
            case 2:
                ArtistMenuView.getInstance().displayMenu();
                setDoneWithMenu();
                break;
            case 3:
                //TODO ALH: Add Genre categories
                break;
            case 4:
                //TODO ALH: Add Playlist categories
                break;
            default:
                invalidNumber();
        }
    }
}
