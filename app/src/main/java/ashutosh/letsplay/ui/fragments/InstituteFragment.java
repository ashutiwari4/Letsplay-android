package ashutosh.letsplay.ui.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import ashutosh.letsplay.R;
import ashutosh.letsplay.application.BaseActivity;
import ashutosh.letsplay.application.BaseFragment;
import ashutosh.letsplay.data.preferences.AppPreferences;
import ashutosh.letsplay.models.GooglePlaceApiModel;
import ashutosh.letsplay.models.GooglePlacesResultsModel;
import ashutosh.letsplay.retrofit.ApiClient;
import ashutosh.letsplay.ui.adapter.InstituteAdapter;
import ashutosh.letsplay.util.AppConstant;
import ashutosh.letsplay.util.FusedLocationSingleton;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashutosh on 20/1/17.
 */

public class InstituteFragment extends BaseFragment implements GoogleApiClient.OnConnectionFailedListener,
        SwipeRefreshLayout.OnRefreshListener,
        InstituteAdapter.OnItemClickListener {
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_songs)
    RecyclerView mRvInstitute;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private boolean updateFlag = false;

    InstituteAdapter instituteAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_song, container, false);
        findAllView(v);
        FusedLocationSingleton.getInstance(InstituteFragment.this).startLocationUpdates();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mLocationUpdated,
                new IntentFilter(AppConstant.INTENT_FILTER_LOCATION_UPDATE));
        return v;
    }


    public void findAllView(View v) {
        ButterKnife.bind(this, v);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        ((BaseActivity) getActivity()).getSnackBar(Snackbar.LENGTH_LONG, getString(R.string.err_unable_to_fetch_location), null).show();
    }

    private void getGooglePlacesData(String input, String location, String radius) {
        Call<GooglePlaceApiModel> recentMedia = null;
        toggleProgressbar(progressBar, mRvInstitute);
        mSwipeRefreshLayout.setRefreshing(false);
        try {
            recentMedia = ApiClient.getService().getPlaces(URLEncoder.encode(input, "utf8"), location, radius, AppConstant.PLACE_API_KEY);
            recentMedia.enqueue(new Callback<GooglePlaceApiModel>() {
                @Override
                public void onResponse(Call<GooglePlaceApiModel> call, Response<GooglePlaceApiModel> response) {
                    AppPreferences.getInstance(getContext()).commitInstitute(new Gson().toJson(response.body()).toString());
                    final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());


                    //mLayoutManager.setReverseLayout(true);
                    mRvInstitute.setLayoutManager(mLayoutManager);


                    instituteAdapter = new InstituteAdapter(InstituteFragment.this, response.body());
                    mRvInstitute.setAdapter(instituteAdapter);
                    instituteAdapter.setOnItemClickListener(InstituteFragment.this);
                    instituteAdapter.notifyDataSetChanged();


                }

                @Override
                public void onFailure(Call<GooglePlaceApiModel> call, Throwable t) {
                    ((BaseActivity) getActivity()).getSnackBar(Snackbar.LENGTH_LONG, getString(R.string.err_unable_to_connect_with_server), null).show();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(InstituteAdapter.ItemHolder item, GooglePlacesResultsModel obj, int position) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + obj.getAddress()));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);

        /**
         * Below snippet can be used to show location as per latitude and longitude
         */
        /*Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?f=d&daddr="+obj.getGooglePlaceGeometry().getLocation().getLat()+","+obj.getGooglePlaceGeometry().getLocation().getLng()));
        intent.setComponent(new ComponentName("com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"));
        startActivity(intent);*/
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mLocationUpdated);
    }

    private BroadcastReceiver mLocationUpdated = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                Location location = (Location) intent.getParcelableExtra(AppConstant.LBM_EVENT_LOCATION_UPDATE);
                if (!updateFlag) {
                    getGooglePlacesData("music school", "" + location.getLatitude() + "," + location.getLongitude(), "" + AppConstant.MUSIC_INSTITUTE_SEARCH_RADIUS);
                    updateFlag = !updateFlag;
                }
            } catch (Exception e) {
                ((BaseActivity) getActivity()).getSnackBar(Snackbar.LENGTH_LONG, getString(R.string.err_unable_to_fetch_location), null).show();

            }
        }
    };

    @Override
    public void onRefresh() {
        Location location = FusedLocationSingleton.getInstance(InstituteFragment.this).getLastLocation();
        if (location != null)
            getGooglePlacesData(AppConstant.SEARCH_QUERY, "" + location.getLatitude() + "," + location.getLongitude(), "" + AppConstant.MUSIC_INSTITUTE_SEARCH_RADIUS);
        else {
            ((BaseActivity) getActivity()).getSnackBar(Snackbar.LENGTH_LONG, getString(R.string.err_turn_on_gps), null).show();
            FusedLocationSingleton.getInstance(InstituteFragment.this).startLocationUpdates();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case AppConstant.FINE_LOCATION_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onRefresh();
                }
                return;
            case AppConstant.ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onRefresh();
                }
                return;
        }
    }
}
