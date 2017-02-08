package ashutosh.letsplay.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ashutosh.letsplay.R;
import ashutosh.letsplay.data.SongLoader;

/**
 * Created by ashutosh on 20/1/17.
 */

public class SongAdapter extends CursorRecyclerViewAdapter {

    private OnItemClickListener onItemClickListener;
    private LayoutInflater layoutInflater;

    public SongAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public SongAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       /* if (viewType == super.getItemCount() - 1) {
            return new ItemHolder(layoutInflater.inflate(R.layout.footer, parent, false), this);
        } else*/
            return new ItemHolder(layoutInflater.inflate(R.layout.row_song, parent, false), this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        ItemHolder holder = (ItemHolder) viewHolder;
        cursor.moveToPosition(cursor.getPosition());
        holder.setItem(cursor);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(ItemHolder item, Cursor cursor, int position);
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private SongAdapter parent;
        public View itemView;
        private TextView tvTitle;
        private Cursor cursor;
        private TextView tvThumbnail;

        public ItemHolder(final View itemView, SongAdapter parent) {
            super(itemView);
            this.parent = parent;
            this.itemView = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tv_song_title);
            tvThumbnail = (TextView) itemView.findViewById(R.id.tv_thumbnail);
        }

        public void setItem(final Cursor cursor) {
            this.cursor = cursor;
            if (itemView.getId() == R.id.pb_footer) {

            } else {
                tvTitle.setText(cursor.getString(SongLoader.Query.TITLE));
                tvThumbnail.setText(cursor.getString(SongLoader.Query.TITLE).substring(0,1));
                itemView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                default:
                    final OnItemClickListener listener = parent.getOnItemClickListener();
                    if (listener != null) {
                        listener.onItemClick(this, cursor, getPosition());
                    }
                    break;
            }
        }
    }
}

