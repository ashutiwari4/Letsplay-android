package ashutosh.letsplay.ui.adapter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ashutosh.letsplay.R;
import ashutosh.letsplay.data.tables.category.CategoryLoader;
import ashutosh.letsplay.data.tables.song.SongContract;
import ashutosh.letsplay.data.tables.song.SongLoader;

/**
 * Created by ashutosh on 3/4/17.
 */

public class GenreAdapter extends CursorRecyclerViewAdapter {

    private SongAdapter.OnItemClickListener onItemClickListener;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public GenreAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public GenreAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GenreAdapter.ItemHolder(layoutInflater.inflate(R.layout.fragment_song_view, parent, false), this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        GenreAdapter.ItemHolder holder = (GenreAdapter.ItemHolder) viewHolder;
        cursor.moveToPosition(cursor.getPosition());
        holder.setItem(cursor);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public SongAdapter.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(SongAdapter.OnItemClickListener listener) {
        onItemClickListener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(SongAdapter.ItemHolder item, Cursor cursor, int position);
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {
        private GenreAdapter parent;
        public View itemView;
        private TextView tvTitle;
        private TextView tvMore;
        private Cursor cursor;
        private RecyclerView rvRow;
        private TextView tvNoDataPlaceHolder;

        public ItemHolder(final View itemView, GenreAdapter parent) {
            super(itemView);
            this.parent = parent;
            this.itemView = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tv_cat_name);
            tvMore = (TextView) itemView.findViewById(R.id.tv_more);
            rvRow = (RecyclerView) itemView.findViewById(R.id.rv_songs);
            tvNoDataPlaceHolder = (TextView) itemView.findViewById(R.id.ctv_placeholder);
        }

        public void setItem(final Cursor cursor) {
            this.cursor = cursor;
            tvTitle.setText(cursor.getString(CategoryLoader.Query.CATEGORY));

            SongAdapter songAdapter = new SongAdapter(mContext, null);
            rvRow.setHasFixedSize(true);
            rvRow.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            rvRow.setAdapter(songAdapter);

            Log.e("GenreAdapter", "Pos: " + cursor.getPosition());
            getLocalData(cursor.getInt(CategoryLoader.Query._ID));

            tvMore.setOnClickListener(this);
        }

        private void getLocalData(int genreId) {
            Bundle b = new Bundle();
            b.putInt("page_no", 1);
            b.putInt("genreId", genreId);
            ((Activity) mContext).getLoaderManager().restartLoader(genreId, b, this);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_more:
                    Toast.makeText(mContext,"//TODO",Toast.LENGTH_LONG).show();
                default:
                    final SongAdapter.OnItemClickListener listener = parent.getOnItemClickListener();
                    if (listener != null) {
                        //listener.onItemClick(this, cursor, getPosition());
                    }
                    break;
            }
        }

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return SongLoader.newAllArticlesInstance(mContext, args.getInt("genreId"), args.getInt("page_no"));
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            Cursor cursor = ((SongAdapter) rvRow.getAdapter()).getCursor();
            if (data.getCount() > 0) {
                MatrixCursor mx = new MatrixCursor(SongLoader.Query.PROJECTION);
                fillSongMx(cursor, mx);
                fillSongMx(data, mx);

                ((SongAdapter) rvRow.getAdapter()).swapCursor(data);
            } else {
                rvRow.setVisibility(View.GONE);
                tvNoDataPlaceHolder.setVisibility(View.VISIBLE);
            }
        }

        private void fillSongMx(Cursor data, MatrixCursor mx) {
            if (data == null)
                return;


            data.moveToPosition(-1);
            while (data.moveToNext()) {
                mx.addRow(new Object[]{
                        data.getString(data.getColumnIndex(SongContract.Songs._ID)),
                        data.getString(data.getColumnIndex(SongContract.Songs.GENRE)),
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
            ((SongAdapter) rvRow.getAdapter()).swapCursor(null);
        }
    }
}

