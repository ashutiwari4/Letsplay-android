package ashutosh.letsplay.data.tables.category;

import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;

/**
 * Created by ashutosh on 30/3/17.
 */

public class CategoryLoader extends CursorLoader {
    private static int count = 10;

    public CategoryLoader(Context context) {
        super(context);
    }

    public static CategoryLoader newAllCategoryInstance(Context context, int pageNo) {
        System.out.println("Local data page no " + pageNo);
        return new CategoryLoader(context, CategoryContract.Categories.buildDirUri(), 0, pageNo * count);
    }

    private CategoryLoader(Context context, Uri uri, int start, int end) {
        super(context, uri, CategoryLoader.Query.PROJECTION, null, null, CategoryContract.Categories.DEFAULT_SORT + " limit " + start + "," + end);
    }

    public interface Query {
        String[] PROJECTION = {
                CategoryContract.CategoryColumns._ID,
                CategoryContract.CategoryColumns.CATEGORY,
        };

        int _ID = 0;
        int CATEGORY = 1;
    }
}
