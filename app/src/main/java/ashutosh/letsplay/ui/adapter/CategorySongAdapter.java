/*
package ashutosh.letsplay.ui.adapter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

*/
/**
 * Created by ashutosh on 30/3/17.
 *//*


public class CategorySongAdapter extends RecyclerView.Adapter<UpcomingEventsAdapter.MyViewHolder> {
    private List<Event> eventList;
    private Fragment fragment;
    private boolean isFirstTime = false;

    public CategorySongAdapter(List<Event> eventList, Fragment fragment) {
        this.fragment = fragment;
        this.eventList = eventList;
        //getNextFewEvents();
    }


    public void getNextFewEvents() {
        eventList.clear();
        if (ActivityCompat.checkSelfPermission(fragment.getContext(), Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(fragment.getContext(), Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED) {

            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {

                    String whereClause = "dtstart" + ">=?";
                    String[] whereArgs = {Calendar.getInstance().getTimeInMillis() + ""};
                    Cursor cursor = fragment.getContext().getContentResolver()
                            .query(Uri.parse("content://com.android.calendar/events"),
                                    new String[]{"calendar_id", CalendarContract.Calendars._ID, "title", "description",
                                            "dtstart", "dtend", "eventLocation", "allDay", CalendarContract.Calendars.OWNER_ACCOUNT, CalendarContract.Calendars.ACCOUNT_TYPE, "eventColor"},
                                    whereClause,
                                    whereArgs,
                                    "dtstart ASC");
                    cursor.moveToFirst();

                    for (int i = 0; i < (cursor.getCount() > 5 ? 5 : cursor.getCount()); i++) {
                        Event event = new Event();
                        String eventFrom = cursor.getString(4);
                        String eventTo = cursor.getString(5);
                        if (eventFrom != null && eventTo != null && !eventFrom.equals("null") && !eventTo.equals("null")) {
                            event.id = Long.parseLong(cursor.getString(1));
                            event.title = cursor.getString(2);
                            //event.description = cursor.getString(3);
                            event.startMillis = cursor.getLong(4);
                            event.endMillis = cursor.getLong(5);
                            event.location = cursor.getString(6);
                            event.organizer = cursor.getString(8);
                            event.allDay = cursor.getInt(7) == 0 ? false : true;
                            event.color = cursor.getInt(10);
                            eventList.add(event);
                        }
                        cursor.moveToNext();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    notifyDataSetChanged();
                    ((DashboardFragments)fragment).checkEventEmptyView();
                }
            }.execute();

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fragment.getContext()).inflate(R.layout.row_upcoming_events, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setItem(eventList.get(position), fragment.getActivity(), position);
    }

    @Override
    public int getItemCount() {
        if (!isFirstTime) {
            if (eventList.size() > 0) {
                return eventList.size();
            } else {
                return 0;
            }
        } else {
            return eventList.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CTextView mCTvEventDate, mCtvEventName, mCtvEventDesc;
        private Event event;
        private View eventColor;
        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mCTvEventDate = (CTextView) itemView.findViewById(R.id.tv_event_date);
            mCtvEventName = (CTextView) itemView.findViewById(R.id.ctv_event_name);
            mCtvEventDesc = (CTextView) itemView.findViewById(R.id.ctv_event_desc);
            cardView = (CardView) itemView.findViewById(R.id.cv_irt_row);
            eventColor = itemView.findViewById(R.id.view_card_color);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.equals(cardView)) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                builder.appendEncodedPath("events/" + event.id);
                i.setData(builder.build());
                i.setClass(fragment.getContext(), EventInfoActivity.class);
                i.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, event.startMillis);
                i.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, event.endMillis);
                fragment.getActivity().startActivity(i);

            }
        }

        public void setItem(Event events, FragmentActivity activity, int position) {
            this.event = events;
            if (eventList.size() > 0) {
                try {
                    Date d = new Date(events.startMillis);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
                    String date = formatter.format(d);
                    mCTvEventDate.setText("" + date);
                    int displayColor = Utils.getDisplayColorFromColor(events.color);
                    eventColor.setBackgroundColor(displayColor);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                mCtvEventName.setText(events.title);
                mCtvEventDesc.setText(events.location);
            }
        }
    }
}*/
