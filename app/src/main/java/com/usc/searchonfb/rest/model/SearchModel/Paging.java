package com.usc.searchonfb.rest.model.SearchModel;

/**
 * Created by adarsh on 4/19/2017.
 */

public class Paging {

    String next;

    String previous;

    public Paging(String next, String previous) {
        this.next = next;
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }
}
