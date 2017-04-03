/*
package ashutosh.letsplay.ui.fragments;

import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.List;

import ashutosh.letsplay.R;
import ashutosh.letsplay.application.BaseFragment;
import ashutosh.letsplay.data.tables.song.SongContract;
import ashutosh.letsplay.data.tables.song.SongLoader;
import ashutosh.letsplay.data.preferences.AppPreferences;
import ashutosh.letsplay.data.syncadapter.GenericAccountService;
import ashutosh.letsplay.ui.activities.DetailsActivity;
import ashutosh.letsplay.ui.adapter.SongAdapter;
import ashutosh.letsplay.util.AppConstant;
import ashutosh.letsplay.util.CTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

*/
/**
 * Created by ashutosh on 30/3/17.
 *//*


public class SongViewFragment extends BaseFragment implements SongAdapter.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor>,
        View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "SongViewFragment";
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.fl_parent)
    FrameLayout flParent;
    @BindView(R.id.rv_songs)
    RecyclerView mRvSongs;
    @BindView(R.id.tv_more)
    CTextView ctvCatName;

    private SongAdapter songAdapter;
    private boolean loadingMore = false;
    int pageNo = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_song_view, container, false);
        findAllViews(v);
        return v;
    }


    private void findAllViews(ViewGroup v) {
        ButterKnife.bind(this, v);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        //catName.setText(category);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
        mRvSongs.setLayoutManager(linearLayoutManager);
        mRvSongs.setHasFixedSize(true);

        songAdapter = new SongAdapter(getContext(), null);
        mRvSongs.setAdapter(songAdapter);
        songAdapter.setOnItemClickListener(SongViewFragment.this);
        mRvSongs.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int maxPositions = layoutManager.getItemCount();

                if (lastVisibleItemPosition == maxPositions - 1) {
                    if (loadingMore)
                        return;

                    loadingMore = true;
                    getLocalData();
                }
            }
        });

        getLocalData();
        //mCvNewData.setOnClickListener(this);

        //}

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,
                new IntentFilter("database_reloaded"));

    }


    @Override
    public void onItemClick(SongAdapter.ItemHolder item, Cursor cursor, int position) {
        cursor.moveToPosition(position);
        startActivity(new Intent(getActivity(), DetailsActivity.class)
                .putExtra(AppConstant.TITLE, cursor.getString(SongLoader.Query.TITLE))
                .putExtra(AppConstant.LINK, cursor.getString(SongLoader.Query.TAGS))
                .putExtra(AppConstant.TAGS, cursor.getString(SongLoader.Query.TAGS))
                .putExtra(AppConstant.TABS_AND_CHORDS, cursor.getString(SongLoader.Query.TAB_AND_CHORD)));
    }


    private List<String> getCategories() {
        return null;
    }

    private void getLocalData() {
        Bundle b = new Bundle();
        pageNo += 1;
        b.putInt("page_no", pageNo);
        getActivity().getLoaderManager().restartLoader(0, b, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        AppPreferences.getInstance(getContext()).setPageNo(args.getInt("page_no"));
        return SongLoader.newAllArticlesInstance(getContext(), args.getInt("page_no"));
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Cursor cursor = ((SongAdapter) mRvSongs.getAdapter()).getCursor();
        if (cursor != null)
            pageNo = cursor.getCount() / 10;
        MatrixCursor mx = new MatrixCursor(SongLoader.Query.PROJECTION);
        fillMx(cursor, mx);
        fillMx(data, mx);

        ((SongAdapter) mRvSongs.getAdapter()).swapCursor(data);
        //toggleProgressbar(progressBar,mRvGenre);
        loadingMore = false;
    }

    private void fillMx(Cursor data, MatrixCursor mx) {
        if (data == null)
            return;

        data.moveToPosition(-1);
        while (data.moveToNext()) {
            mx.addRow(new Object[]{
                    data.getString(data.getColumnIndex(SongContract.Songs._ID)),
                    data.getString(data.getColumnIndex(SongContract.Songs.TITLE)),
                    data.getString(data.getColumnIndex(SongContract.Songs.COMPOSER)),
                    data.getString(data.getColumnIndex(SongContract.Songs.TAB_AND_CHORD)),
                    data.getString(data.getColumnIndex(SongContract.Songs.THUMB_URL)),
                    data.getString(data.getColumnIndex(SongContract.Songs.PHOTO_URL)),
                    data.getString(data.getColumnIndex(SongContract.Songs.ASPECT_RATIO)),
                    data.getString(data.getColumnIndex(SongContract.Songs.PUBLISHED_DATE)),
                    data.getString(data.getColumnIndex(SongContract.Songs.IS_FAVOURITE)),
                    data.getString(data.getColumnIndex(SongContract.Songs.TAGS)),
                    data.getString(data.getColumnIndex(SongContract.Songs.LINK))
            });
        }
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        ((SongAdapter) mRvSongs.getAdapter()).swapCursor(null);
    }


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateRefreshingUI(false);
            //mCvNewData.setVisibility(View.VISIBLE);
            songAdapter.notifyItemChanged(pageNo * 10);
            getLocalData();
            //mRvInstitute.scrollToPosition(pageNo * 10);
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        v.setVisibility(View.GONE);
    }

    private void updateRefreshingUI(boolean status) {
        mSwipeRefreshLayout.setRefreshing(status);
    }


    public void refreshData() {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        ContentResolver.requestSync(GenericAccountService.GetAccount(getContext()), SongContract.CONTENT_AUTHORITY, settingsBundle);
    }


    @Override
    public void onRefresh() {
        refreshData();
    }
}
*/
