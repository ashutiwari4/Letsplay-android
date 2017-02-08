package ashutosh.letsplay.util;

/**
 * Created by ashutosh on 20/1/17.
 */

public class AppConstant {

    public static final String TO = "ashutiwari4@gmail.com,ashutosh.tiwari@affle.com";
    public static final String PLACE_API_KEY = "AIzaSyAkIaCsL27z5Unf05bNW7n9AUSbBhc4TPc";
    public static final String PLACE_IMAGE_URL = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key=" + AppConstant.PLACE_API_KEY +
            "&photoreference=";

    public static final String TITLE = "title";
    public static final String TABS_AND_CHORDS = "tabs_n_chords";
    public static final String LINK = "link";
    public static final String TAGS = "tags";

    public static final String SEARCH_QUERY = "Music school";

    public static final String LBM_EVENT_LOCATION_UPDATE = "lbmLocationUpdate";
    public static final String INTENT_FILTER_LOCATION_UPDATE = "intentFilterLocationUpdate";
    public static final int MUSIC_INSTITUTE_SEARCH_RADIUS = 200;

    public static final int FINE_LOCATION_PERMISSION = 0x0001;
    public static final int ACCESS_COARSE_LOCATION = 0x0002;
}

