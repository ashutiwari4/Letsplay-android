package ashutosh.letsplay.data.tables.category;

import android.net.Uri;

import ashutosh.letsplay.data.tables.song.SongProvider;

/**
 * Created by ashutosh on 31/3/17.
 */

public class CategoryContract {
    public static final String CONTENT_AUTHORITY = "ashutosh.letsplay";
    public static final Uri BASE_URI = Uri.parse("content://ashutosh.letsplay");

    public interface CategoryColumns {
        /**
         * Type: INTEGER PRIMARY KEY AUTOINCREMENT
         */
        String _ID = "_id";

        String CATEGORY = "categories";

    }


    public static class Categories implements CategoryContract.CategoryColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ashutosh.letsplay.categories";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.ashutosh.letsplay.categories";

        public static final String DEFAULT_SORT = _ID + " DESC";
        /**
         * Matches: /items/
         */
        public static Uri buildDirUri() {
            return BASE_URI.buildUpon().appendPath(SongProvider.Tables.CATEGORY).build();
        }


        /**
         * Matches: /items/[_id]/
         */
        public static Uri buildItemUri(long _id) {
            return BASE_URI.buildUpon().appendPath(SongProvider.Tables.CATEGORY).appendPath(Long.toString(_id)).build();
        }

        /**
         * Read item ID item detail URI.
         */
        public static long getItemId(Uri itemUri) {
            return Long.parseLong(itemUri.getPathSegments().get(1));
        }
    }

    private CategoryContract() {
    }
}
