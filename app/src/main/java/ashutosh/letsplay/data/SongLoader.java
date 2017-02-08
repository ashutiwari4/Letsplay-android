package ashutosh.letsplay.data;

import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;

/**
 * Helper for loading a list of articles or a single article.
 */
public class SongLoader extends CursorLoader {
    public static int count = 10;

    public static SongLoader newAllArticlesInstance(Context context, int pageNo) {
        System.out.println("Local data page no " + pageNo);
        return new SongLoader(context, SongContract.Songs.buildDirUri(), 0, pageNo * count);
    }

    public static SongLoader newInstanceForItemId(Context context, long itemId) {
        return new SongLoader(context, SongContract.Songs.buildItemUri(itemId));
    }

    private SongLoader(Context context, Uri uri, int start, int end) {
        super(context, uri, Query.PROJECTION, null, null, SongContract.Songs.DEFAULT_SORT + " limit " + start + "," + end);
    }

    private SongLoader(Context context, Uri uri) {
        super(context, uri, Query.PROJECTION, null, null, SongContract.Songs.DEFAULT_SORT);
    }

    public interface Query {
        String[] PROJECTION = {
                SongContract.SongColumns._ID,
                SongContract.SongColumns.TITLE,
                SongContract.SongColumns.TAB_AND_CHORD,
                SongContract.SongColumns.LINK,
                SongContract.SongColumns.TAGS,
                SongContract.SongColumns.IS_FAVOURITE,
                SongContract.SongColumns.COMPOSER,
                SongContract.SongColumns.THUMB_URL,
                SongContract.SongColumns.PHOTO_URL,
                SongContract.SongColumns.ASPECT_RATIO,
                SongContract.SongColumns.PUBLISHED_DATE,
        };

        int _ID = 0;
        int TITLE = 1;
        int TAB_AND_CHORD = 2;
        int LINK = 3;
        int TAGS = 4;
        int IS_FAVOURITE = 5;
        int COMPOSER = 6;
        int THUMB_URL = 7;
        int PHOTO_URL = 8;
        int ASPECT_RATIO = 9;
        int PUBLISHED_DATE = 10;

    }


}
