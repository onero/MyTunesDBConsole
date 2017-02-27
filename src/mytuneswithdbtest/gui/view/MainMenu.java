/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.gui.view;

import mytuneswithdbtest.be.AMenu;
import mytuneswithdbtest.bll.SongManager;
import mytuneswithdbtest.gui.model.OptionModel;

public class MainMenu extends AMenu {

    private static MainMenu instance;

    private final SongManager songManager;

    private final OptionModel optionModel;

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    private MainMenu() {
        songManager = SongManager.getInstance();
        optionModel = OptionModel.getInstance();
    }

    @Override
    public void displayMenu() {
        System.out.println();
        System.out.println("Welcome to this awesome application!");
        displayMainCategories();
    }

    private void displayMainCategories() {
        boolean nextMenu = true;
        do {
            System.out.println();
            System.out.println("Choose a category");
            int optionNr = 0;
            for (String mainCategoryOption : optionModel.getMainCategoryOptions()) {
                System.out.println(optionNr + ": " + mainCategoryOption);
                optionNr++;
            }
            int userOption = prompUserForInput();
            nextMenu = reactToUserInputForMainCategory(userOption, nextMenu);
        } while (!nextMenu);

    }

    /**
     * Execute option depending on user choice
     */
    private boolean reactToUserInputForMainCategory(int option, boolean nextMenu) {
        switch (option) {
            case 0:
                terminateProgram();
                break;
            case 1:
                SongMenu.getInstance().displayMenu();
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
                nextMenu = false;
        }
        return nextMenu;
    }

}
