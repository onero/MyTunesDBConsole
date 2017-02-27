/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.be;

public abstract class AMenu {

    public abstract void displayMenu();

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
