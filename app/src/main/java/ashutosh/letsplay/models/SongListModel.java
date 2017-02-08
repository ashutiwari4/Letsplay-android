package ashutosh.letsplay.models;

import java.util.ArrayList;

/**
 * Created by ashutosh on 20/1/17.
 */

public class SongListModel {
    private int count;
    private String next;
    private String previous;
    private ArrayList<SongsModel> results;

    public ArrayList<SongsModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<SongsModel> results) {
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
