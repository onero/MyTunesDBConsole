/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.be;

import mytuneswithdbtest.bll.IDFactory;

public class Artist {

    private final int mID;

    private final String mName;

    public Artist(String name) {
        mID = IDFactory.getNewID();
        mName = name;
    }

    public Artist(int mID, String mName) {
        this.mID = mID;
        this.mName = mName;
    }

    public int getmID() {
        return mID;
    }

    public String getmName() {
        return mName;
    }

}
