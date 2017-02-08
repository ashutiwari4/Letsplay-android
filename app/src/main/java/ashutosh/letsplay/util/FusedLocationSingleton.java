package ashutosh.letsplay.util;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import ashutosh.letsplay.R;
import ashutosh.letsplay.application.BaseActivity;
import ashutosh.letsplay.ui.fragments.InstituteFragment;

/**
 * Created by ashutosh on 27/1/17.
 */

public class FusedLocationSingleton implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static FusedLocationSingleton mInstance = null;
    protected GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    public final static int FAST_LOCATION_FREQUENCY = 5 * 1000;
    public final static int LOCATION_FREQUENCY = 5 * 1000;
    private static InstituteFragment instituteFragment;


    public FusedLocationSingleton(InstituteFragment context) {
        instituteFragment = context;
        buildGoogleApiClient();
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        stopLocationUpdates();
    }

    public static FusedLocationSingleton getInstance(InstituteFragment fragment) {
        if (null == mInstance) {
            mInstance = new FusedLocationSingleton(fragment);
        }
        mInstance.instituteFragment = fragment;
        return mInstance;
    }


    private synchronized void buildGoogleApiClient() {
        // setup googleapi client
        mGoogleApiClient = new GoogleApiClient.Builder(instituteFragment.getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        // setup location updates
        configRequestLocationUpdate();
    }

    private void configRequestLocationUpdate() {
        mLocationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(LOCATION_FREQUENCY)
                .setFastestInterval(FAST_LOCATION_FREQUENCY);
    }

    private void requestLocationUpdates() {
        if (instituteFragment.getActivity() != null)
            if (instituteFragment.hasPermission(instituteFragment.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION, AppConstant.FINE_LOCATION_PERMISSION)
                    && instituteFragment.hasPermission(instituteFragment.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION, AppConstant.ACCESS_COARSE_LOCATION)) {
                LocationServices.FusedLocationApi.requestLocationUpdates(
                        mGoogleApiClient,
                        mLocationRequest,
                        this
                );
            } else {
                ((BaseActivity) instituteFragment.getActivity()).getSnackBar(Snackbar.LENGTH_LONG, instituteFragment.getString(R.string.permission_denied), null).show();
            }
    }

    public void startLocationUpdates() {
        // connect and force the updates
        mGoogleApiClient.connect();
        if (mGoogleApiClient.isConnected()) {
            requestLocationUpdates();
        }
    }

    public void stopLocationUpdates() {
        // stop updates, disconnect from google api
        if (null != mGoogleApiClient && mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }

    }

    public Location getLastLocation() {
        if (null != mGoogleApiClient && mGoogleApiClient.isConnected()) {
            // return last location
            if (ActivityCompat.checkSelfPermission(instituteFragment.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(instituteFragment.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
            return LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        } else {
            startLocationUpdates(); // start the updates
            return null;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        requestLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        // connection to Google Play services was lost for some reason
        if (null != mGoogleApiClient) {
            mGoogleApiClient.connect(); // attempt to establish a new connection
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null && instituteFragment != null) {
            // send location in broadcast
            Intent intent = new Intent(AppConstant.INTENT_FILTER_LOCATION_UPDATE);
            intent.putExtra(AppConstant.LBM_EVENT_LOCATION_UPDATE, location);
            LocalBroadcastManager.getInstance(instituteFragment.getContext().getApplicationContext()).sendBroadcast(intent);
        }
    }

}
