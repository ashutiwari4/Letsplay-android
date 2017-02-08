package ashutosh.letsplay.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.AppWidgetTarget;

import java.util.Random;

import ashutosh.letsplay.R;
import ashutosh.letsplay.data.preferences.AppPreferences;
import ashutosh.letsplay.models.GooglePlaceApiModel;
import ashutosh.letsplay.models.GooglePlacesResultsModel;
import ashutosh.letsplay.util.AppConstant;

/**
 * Created by ashutosh on 27/1/17.
 */

public class WidgetProvider extends AppWidgetProvider {


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int i : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            int pos = new Random(10).nextInt(19) + 1;
            setData(context, remoteViews, pos, appWidgetIds);

            Intent intent = new Intent(context, WidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            //remoteViews.setOnClickPendingIntent(R.id.btn_widget_refresh, pendingIntent);
            appWidgetManager.updateAppWidget(i, remoteViews);
        }
    }


    public void setData(Context context, RemoteViews remoteViews, int position, int[] i) {
        GooglePlaceApiModel googlePlaceApiModel = AppPreferences.getInstance(context).getInstitute();
        if (googlePlaceApiModel != null) {
            GooglePlacesResultsModel googlePlacesResultsModel = null;

            if (googlePlaceApiModel.results.size() > position)
                googlePlacesResultsModel = googlePlaceApiModel.results.get(position);
            else if (googlePlaceApiModel.results.size() > 0) googlePlaceApiModel.results.get(0);
            else return;
            if (googlePlaceApiModel != null) {
                remoteViews.setViewVisibility(R.id.rl_error, View.GONE);
                remoteViews.setViewVisibility(R.id.rl_data_view, View.VISIBLE);
                if (googlePlacesResultsModel.getOpening_hours() != null)
                    remoteViews.setTextViewText(R.id.tv_widget_open_status, googlePlacesResultsModel.getOpening_hours().isOpen_now() == true ? context.getString(R.string.open_now) : context.getString(R.string.close_now));
                else
                    remoteViews.setTextViewText(R.id.tv_widget_open_status, context.getString(R.string.not_available));
                remoteViews.setTextViewText(R.id.tv_widget_Title, googlePlacesResultsModel.getName());
                remoteViews.setTextViewText(R.id.tv_widget_address, googlePlacesResultsModel.getAddress());
                AppWidgetTarget mAppWidgetTarget = new AppWidgetTarget(context.getApplicationContext(), remoteViews, R.id.iv_widget_thumbnail, 400, 400, i) {
                };


                String url = AppConstant.PLACE_IMAGE_URL + googlePlacesResultsModel.getPhotos().get(0).getPhoto_reference();
                Glide.with(context.getApplicationContext())
                        .load(url)
                        .asBitmap()
                        .centerCrop()
                        .into(mAppWidgetTarget);
            }
        } else setErrorView(remoteViews);

    }

    private void setErrorView(RemoteViews remoteViews) {
        remoteViews.setViewVisibility(R.id.rl_error, View.VISIBLE);
        remoteViews.setViewVisibility(R.id.rl_data_view, View.GONE);
    }
}
