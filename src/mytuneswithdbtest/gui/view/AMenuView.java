/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.gui.view;

public abstract class AMenuView {

    private boolean doneWithMenu = false;

    /**
     * Display the menu to the user
     */
    public abstract void displayMenu();

    /**
     * Display each menu item according to it's number
     *
     * @param options
     */
    public void displayMenuOptions(String[] options) {
        do {
            System.out.println();
            System.out.println("Choose an option");
            int optionNr = 0;
            for (String option : options) {
                System.out.println(optionNr + ": " + option);
                optionNr++;
            }
            int userOption = prompUserForInput();
            reactToUserInput(userOption);
        } while (!doneWithMenu);
    }

    /**
     * According to the number chosen by the user, carry out according action
     *
     * @param userOption
     */
    public abstract void reactToUserInput(int userOption);

    /**
     * Prompt the user for a choice
     *
     * @return
     */
    public int prompUserForInput() {
        int userOption = Console.Readers.readInt("Your choice: ");
        return userOption;
    }

    /**
     * Set doneWithMenu true to indicate that the user is moving to next menu
     */
    public void setDoneWithMenu() {
        doneWithMenu = true;
    }

    /**
     * Inform user of invalid input
     */
    public void invalidNumber() {
        System.out.println();
        System.out.println("You must pick a valid number");
    }

    /**
     * Terminate program
     */
    public void terminateProgram() {
        System.out.println();
        System.out.println("Terminating program.");
        System.out.println("Bye!");
    }

}
