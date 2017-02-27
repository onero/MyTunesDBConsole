/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.gui.model;

public class OptionModel {

    private static OptionModel instance;

    public static OptionModel getInstance() {
        if (instance == null) {
            instance = new OptionModel();
        }
        return instance;
    }

    private final String[] mainCategoryOptions = {
        "Exit program",
        "Song",
        "Artist",
        "Genre",
        "Playlist"
    };

    private final String[] songCategoryOptions = {
        "Back to categories",
        "Get songs",
        "Add song",
        "Update song",
        "Delete song"
    };

    public String[] getMainCategoryOptions() {
        return mainCategoryOptions;
    }

    public String[] getSongCategoryOptions() {
        return songCategoryOptions;
    }

}
