package ashutosh.letsplay.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ashutosh on 20/1/17.
 */

public class SongsModel {

    private int id;
    private int genreId;
    @SerializedName("name")
    private String title;
    @SerializedName("tabs_and_chords")
    private String tabsAndChords;
    private String tags;


    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTabsAndChords() {
        return tabsAndChords;
    }

    public void setTabsAndChords(String tabsAndChords) {
        this.tabsAndChords = tabsAndChords;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
