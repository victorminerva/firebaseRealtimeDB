package com.victorminerva.app.firebaserealtimedb.model;

import java.util.Date;

/**
 * Created by victo on 07/12/2017.
 */

public class Loan {

    private String loanID;
    private Favored favored;
    private String what;
    private String whatDescription;
    private Date untilWhen;
    private Boolean returned;

    public String getLoanID() {
        return loanID;
    }

    public void setLoanID(String loanID) {
        this.loanID = loanID;
    }

    public Favored getFavored() {
        return favored;
    }

    public void setFavored(Favored favored) {
        this.favored = favored;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getWhatDescription() {
        return whatDescription;
    }

    public void setWhatDescription(String whatDescription) {
        this.whatDescription = whatDescription;
    }

    public Date getUntilWhen() {
        return untilWhen;
    }

    public void setUntilWhen(Date untilWhen) {
        this.untilWhen = untilWhen;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanID='" + loanID + '\'' +
                ", favored=" + favored +
                ", what='" + what + '\'' +
                ", whatDescription='" + whatDescription + '\'' +
                ", untilWhen=" + untilWhen +
                ", returned=" + returned +
                '}';
    }
}
