package ashutosh.letsplay.models;

import java.util.ArrayList;

/**
 * Created by ashutosh on 31/3/17.
 */

public class GenreListModal {
    private int count;
    private String next;
    private String previous;
    private ArrayList<GenreModal> results;

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

    public ArrayList<GenreModal> getResults() {
        return results;
    }

    public void setResults(ArrayList<GenreModal> results) {
        this.results = results;
    }
}
