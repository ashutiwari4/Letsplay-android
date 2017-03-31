/*
package ashutosh.letsplay.data.tables.category;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import ashutosh.letsplay.data.ItemsDatabase;
import ashutosh.letsplay.data.SelectionBuilder;

*/
/**
 * Created by ashutosh on 31/3/17.
 *//*


public class CategoryProvider  extends ContentProvider {
    private SQLiteOpenHelper mOpenHelper;

    public interface Tables {
        String CATEGORY = "categories";
    }

    private static final int ITEMS = 0;
    private static final int ITEMS__ID = 1;
    private static final int TABLE_ITEMS = 2;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CategoryContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, "categories", ITEMS);
        matcher.addURI(authority, "categories/#", ITEMS__ID);
        matcher.addURI(authority, Tables.CATEGORY + "/offset/" + "#", TABLE_ITEMS);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new ItemsDatabase(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                return CategoryContract.Categories.CONTENT_TYPE;
            case ITEMS__ID:
                return CategoryContract.Categories.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    public static Uri urlForItems(int limit) {
        return Uri.parse("content://" + CategoryContract.CONTENT_AUTHORITY + "/" + Tables.CATEGORY + "/offset/" + limit);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final SelectionBuilder builder = buildSelection(uri);
        Cursor cursor = builder.where(selection, selectionArgs).query(db, projection, sortOrder);
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS: {
                final long _id = db.insertWithOnConflict(Tables.CATEGORY, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                if (_id == -1) {
                    System.out.println("Updating data");
                    db.update(Tables.CATEGORY, values, "_id=?", new String[]{values.getAsString(CategoryContract.CategoryColumns._ID)});
                } else System.out.println("Inserting data");
                getContext().getContentResolver().notifyChange(uri, null);
                return CategoryContract.Categories.buildItemUri(_id);
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final SelectionBuilder builder = buildSelection(uri);
        getContext().getContentResolver().notifyChange(uri, null);
        return builder.where(selection, selectionArgs).update(db, values);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final SelectionBuilder builder = buildSelection(uri);
        getContext().getContentResolver().notifyChange(uri, null);
        return builder.where(selection, selectionArgs).delete(db);
    }


    private SelectionBuilder buildSelection(Uri uri) {
        final SelectionBuilder builder = new SelectionBuilder();
        final int match = sUriMatcher.match(uri);
        return buildSelection(uri, match, builder);
    }

    private SelectionBuilder buildSelection(Uri uri, int match, SelectionBuilder builder) {
        final List<String> paths = uri.getPathSegments();
        switch (match) {
            case ITEMS: {
                return builder.table(Tables.CATEGORY);
            }
            case ITEMS__ID: {
                final String _id = paths.get(1);
                return builder.table(Tables.CATEGORY).where(CategoryContract.CategoryColumns._ID + "=?", _id);
            }
            case TABLE_ITEMS:{

            }
            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
    }

    */
/**
     * Apply the given set of {@link ContentProviderOperation}, executing inside
     * a {@link SQLiteDatabase} transaction. All changes will be rolled back if
     * any single one fails.
     *//*

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            final int numOperations = operations.size();
            final ContentProviderResult[] results = new ContentProviderResult[numOperations];
            for (int i = 0; i < numOperations; i++) {
                results[i] = operations.get(i).apply(this, results, i);
            }
            db.setTransactionSuccessful();
            return results;
        } finally {
            db.endTransaction();
        }
    }
}
*/
