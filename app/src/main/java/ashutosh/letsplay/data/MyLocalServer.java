package ashutosh.letsplay.data;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;

import ashutosh.letsplay.data.tables.category.CategoryContract;
import ashutosh.letsplay.data.tables.song.SongContract;
import ashutosh.letsplay.models.GenreListModal;
import ashutosh.letsplay.models.GenreModal;
import ashutosh.letsplay.models.SongListModel;
import ashutosh.letsplay.models.SongsModel;

/**
 * Created by ashutosh on 23/1/17.
 */

public class MyLocalServer {
    private static final int ITEM_COUNT_IN_PAGE = 5;

    public MyLocalServer(Context context, SongListModel songListModel) {
        updateDatabase(context, songListModel);
    }


    public MyLocalServer(Context context, GenreListModal genreListModal) {
        updateDatabase(context, genreListModal);
    }

    public void updateDatabase(Context context, SongListModel songListModel) {
        System.out.println("In my song local server......................................");


        try {
            ArrayList<ContentProviderOperation> cpo = new ArrayList<ContentProviderOperation>();
            Uri dirUri = SongContract.Songs.buildDirUri();
            //TODO
            //cpo.add(ContentProviderOperation.newDelete(dirUri).build());


            for (int i = 0; i < songListModel.getResults().size(); i++) {
                ContentValues values = new ContentValues();
                SongsModel songsModel = songListModel.getResults().get(i);
                values.put(SongContract.Songs._ID, songsModel.getId());
                values.put(SongContract.Songs.TITLE, songsModel.getTitle());
                values.put(SongContract.Songs.TAB_AND_CHORD, songsModel.getTabsAndChords());
                values.put(SongContract.Songs.TAGS, songsModel.getTags());
                cpo.add(ContentProviderOperation.newInsert(dirUri).withValues(values).build());
            }

            context.getContentResolver().applyBatch(SongContract.CONTENT_AUTHORITY, cpo);
        } catch (RemoteException | OperationApplicationException e) {
            Log.e(getClass().getSimpleName(), "Error updating content.", e);
        }
        sendMessage(context);
    }

    public void updateDatabase(Context context, GenreListModal genreListModel) {
        System.out.println("In my genre local server......................................");


        try {
            ArrayList<ContentProviderOperation> cpo = new ArrayList<ContentProviderOperation>();
            Uri dirUri = CategoryContract.Categories.buildDirUri();
            //TODO
            //cpo.add(ContentProviderOperation.newDelete(dirUri).build());

            for (int i = 0; i < genreListModel.getResults().size(); i++) {
                ContentValues values = new ContentValues();
                GenreModal genreModal = genreListModel.getResults().get(i);
                values.put(CategoryContract.Categories._ID, genreModal.getId());
                values.put(CategoryContract.Categories.CATEGORY, genreModal.getName());
                cpo.add(ContentProviderOperation.newInsert(dirUri).withValues(values).build());
            }

            context.getContentResolver().applyBatch(CategoryContract.CONTENT_AUTHORITY, cpo);
        } catch (RemoteException | OperationApplicationException e) {
            Log.e(getClass().getSimpleName(), "Error updating content.", e);
        }
        sendMessage(context);
    }



    private void sendMessage(Context context) {
        Intent intent = new Intent("database_reloaded");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
