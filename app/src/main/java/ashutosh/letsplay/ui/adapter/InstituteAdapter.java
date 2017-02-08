package ashutosh.letsplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ashutosh.letsplay.R;
import ashutosh.letsplay.models.GooglePlaceApiModel;
import ashutosh.letsplay.models.GooglePlacesResultsModel;
import ashutosh.letsplay.ui.fragments.InstituteFragment;
import ashutosh.letsplay.util.AppConstant;

/**
 * Created by ashutosh on 25/1/17.
 */

public class InstituteAdapter extends RecyclerView.Adapter<InstituteAdapter.ItemHolder> {
    public ArrayList<GooglePlacesResultsModel> data;
    private OnItemClickListener onItemClickListener;
    private LayoutInflater layoutInflater;
    private InstituteFragment instituteFragment;


    public InstituteAdapter(InstituteFragment fragment, GooglePlaceApiModel data) {
        layoutInflater = LayoutInflater.from(fragment.getContext());
        this.data = (ArrayList<GooglePlacesResultsModel>) data.results;
        this.instituteFragment = fragment;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public InstituteAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ItemHolder(layoutInflater.inflate(R.layout.row_institute, parent, false), this);
    }

    @Override
    public void onBindViewHolder(InstituteAdapter.ItemHolder holder, int position) {
        holder.setItem(data.get(position), instituteFragment.getContext(), position);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public void add(int location, GooglePlacesResultsModel iName) {
        data.add(location, iName);
        notifyItemInserted(location);
    }

    public interface OnItemClickListener {
        void onItemClick(ItemHolder item, GooglePlacesResultsModel aspirationModal, int position);
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private InstituteAdapter parent;
        public View itemView;
        private TextView tvTitle;
        private TextView tvAddress;
        private GooglePlacesResultsModel aspirationModal;
        private RatingBar ratingBar;
        private TextView openTime;
        private ImageView placeImage;

        public ItemHolder(final View itemView, InstituteAdapter parent) {
            super(itemView);
            this.parent = parent;
            this.itemView = itemView;


            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            openTime = (TextView) itemView.findViewById(R.id.tv_open_time);
            ratingBar = (RatingBar) itemView.findViewById(R.id.rb_place);
            placeImage = (ImageView) itemView.findViewById(R.id.iv_place);
            itemView.setOnClickListener(this);
        }

        public void setItem(final GooglePlacesResultsModel itemObject, final Context context, int position) {
            aspirationModal = itemObject;

            tvTitle.setText(itemObject.getName());
            System.out.println(itemObject.getName());
            tvAddress.setText(itemObject.getAddress());
            if(itemObject.getOpening_hours() != null)
                openTime.setText(itemObject.getOpening_hours().isOpen_now() == true ? instituteFragment.getString(R.string.open_now) : instituteFragment.getString(R.string.close_now));
            ratingBar.setProgress((int) itemObject.getRating());

            if(itemObject.getPhotos() != null) {
                String url = AppConstant.PLACE_IMAGE_URL + itemObject.getPhotos().get(0).getPhoto_reference();
                System.out.println(url);
                Glide.with(context).load(url).placeholder(R.drawable.music_place_holder).into(placeImage);
            }else{
                Glide.with(context).load(R.drawable.music_place_holder).placeholder(R.drawable.music_place_holder).into(placeImage);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                default:
                    final OnItemClickListener listener = parent.getOnItemClickListener();
                    if (listener != null) {
                        listener.onItemClick(this, aspirationModal, getPosition());
                    }
                    break;
            }
        }
    }
}
