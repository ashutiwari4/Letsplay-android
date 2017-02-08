package ashutosh.letsplay.data.syncadapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by ashutosh on 18/7/16.
 */
public class SyncService extends Service {
    private static final String TAG = "SyncService";

    public static SongSyncAdapter songSyncAdapter = null;

    private static final Object sSyncAdapterLock = new Object();

    /**
     * Thread-safe constructor, creates static {@link SongSyncAdapter} instance.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Service created");
        synchronized (sSyncAdapterLock) {
            if (songSyncAdapter == null)
                songSyncAdapter = new SongSyncAdapter(getApplicationContext(), true);

        }
    }

    @Override
    /**
     * Logging-only destructor.
     */
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Service destroyed");
    }


    @Override
    public IBinder onBind(Intent intent) {
        return songSyncAdapter.getSyncAdapterBinder();
    }
}
