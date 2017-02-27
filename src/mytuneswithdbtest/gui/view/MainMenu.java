/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.gui.view;

import mytuneswithdbtest.be.abstractions.AMenu;
import mytuneswithdbtest.bll.SongManager;
import mytuneswithdbtest.gui.model.OptionModel;

public class MainMenu extends AMenu {

    private static MainMenu instance;

    private final SongManager songManager;

    private final OptionModel optionModel;

    private final String[] options;

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    private MainMenu() {
        songManager = SongManager.getInstance();
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

    @Override
    public void reactToUserInput(int userOption) {
        switch (userOption) {
            case 0:
                terminateProgram();
                setDoneWithMenu();
                break;
            case 1:
                SongMenu.getInstance().displayMenu();
                setDoneWithMenu();
                break;
            case 2:
                //TODO ALH: Add Artist categories
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
