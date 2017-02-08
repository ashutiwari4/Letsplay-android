package ashutosh.letsplay.remote;

/**
 * Created by ashutosh on 20/1/17.
 */

public class UrlConfig {
    public static final String BASE_URL = "http://ashutiwari4.pythonanywhere.com/api/";

    public static final String API_SONG = BASE_URL + "songs/?format=json&page=";
    public static final String API_GENRE = BASE_URL + "genre/?format=json&page=";
    public static String API_SONG_URL = BASE_URL + "song_links/?format=json&page=";

}
