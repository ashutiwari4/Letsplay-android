package ashutosh.letsplay.data.syncadapter;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.widget.Toast;

import ashutosh.letsplay.R;
import ashutosh.letsplay.data.MyLocalServer;
import ashutosh.letsplay.data.preferences.AppPreferences;
import ashutosh.letsplay.models.GenreListModal;
import ashutosh.letsplay.models.SongListModel;
import ashutosh.letsplay.retrofit.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ashutosh on 18/7/16.
 */
public class SongSyncAdapter extends AbstractThreadedSyncAdapter {

    public SongSyncAdapter(Context context, boolean autoInitialization) {
        super(context, autoInitialization);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {

        System.out.println("Performing syncing ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        getSongFromCloud("" + AppPreferences.getInstance(getContext()).getPageNo());
        getCategoryFromCloud(1);
    }


    private void getCategoryFromCloud(int pageNo) {

        Call<GenreListModal> recentMedia = ApiClient.getService().getGenreList("json", ""+pageNo);
        recentMedia.enqueue(new Callback<GenreListModal>() {
            @Override
            public void onResponse(Call<GenreListModal> call, Response<GenreListModal> response) {
                GenreListModal genreListModels = response.body();
                /*if (genreListModels.getNext() == null) {
                    Toast.makeText(getContext(), getContext().getString(R.string.already_syn), Toast.LENGTH_LONG).show();
                    return;
                }*/
                new MyLocalServer(getContext(), genreListModels);
            }

            @Override
            public void onFailure(Call<GenreListModal> call, Throwable t) {

            }
        });
    }

    private void getSongFromCloud(String pageNo) {
        Call<SongListModel> recentMedia = ApiClient.getService().getSongList("json", pageNo);
        recentMedia.enqueue(new Callback<SongListModel>() {
            @Override
            public void onResponse(Call<SongListModel> call, Response<SongListModel> response) {
                SongListModel songListModels = response.body();
                if (songListModels.getNext() == null) {
                    Toast.makeText(getContext(), getContext().getString(R.string.already_syn), Toast.LENGTH_LONG).show();
                    return;
                }
                new MyLocalServer(getContext(), songListModels);
            }

            @Override
            public void onFailure(Call<SongListModel> call, Throwable t) {

            }
        });
    }

}
