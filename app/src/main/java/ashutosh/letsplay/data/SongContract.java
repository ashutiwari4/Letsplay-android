package ashutosh.letsplay.data;

import android.net.Uri;

public class SongContract {
    public static final String CONTENT_AUTHORITY = "ashutosh.letsplay";
    public static final Uri BASE_URI = Uri.parse("content://ashutosh.letsplay");

    interface SongColumns {
        /**
         * Type: INTEGER PRIMARY KEY AUTOINCREMENT
         */
        String _ID = "_id";
        /**
         * Type: TEXT
         */
        String TITLE = "title";
        /**
         * Type: TEXT NOT NULL
         */
        String COMPOSER = "composer";
        /**
         * Type: TEXT NOT NULL
         */
        String TAB_AND_CHORD = "body";
        /**
         * Type: TEXT NOT NULL
         */
        String THUMB_URL = "thumb_url";
        /**
         * Type: TEXT NOT NULL
         */
        String PHOTO_URL = "photo_url";
        /**
         * Type: REAL NOT NULL DEFAULT 1.5
         */
        String ASPECT_RATIO = "aspect_ratio";
        /**
         * Type: INTEGER NOT NULL DEFAULT 0
         */
        String PUBLISHED_DATE = "published_date";
        /**
         * Type: INTEGER NOT NULL DEFAULT 0
         */
        String IS_FAVOURITE = "favourite";
        /**
         * Type: TEXT NOT NULL
         */
        String TAGS = "tags";
        /**
         * Type: TEXT NOT NULL
         */
        String LINK = "link";

    }


    public static class Songs implements SongColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ashutosh.letsplay.songs";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.ashutosh.letsplay.songs";

        public static final String DEFAULT_SORT = PUBLISHED_DATE + " DESC";

        /**
         * Matches: /items/
         */
        public static Uri buildDirUri() {
            return BASE_URI.buildUpon().appendPath("songs").build();
        }

        /**
         * Matches: /items/[_id]/
         */
        public static Uri buildItemUri(long _id) {
            return BASE_URI.buildUpon().appendPath("songs").appendPath(Long.toString(_id)).build();
        }

        /**
         * Read item ID item detail URI.
         */
        public static long getItemId(Uri itemUri) {
            return Long.parseLong(itemUri.getPathSegments().get(1));
        }
    }

    private SongContract() {
    }
}
