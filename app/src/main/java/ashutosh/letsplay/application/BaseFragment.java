package ashutosh.letsplay.application;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by ashutosh on 20/1/17.
 */

public class BaseFragment extends Fragment {


    protected void toggleProgressbar(ProgressBar progressBar, RecyclerView recyclerView) {
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    public boolean hasPermission(Activity context, String permission, int reqId) {
        int result = ContextCompat.checkSelfPermission(context, permission);
        if (result == PackageManager.PERMISSION_GRANTED) return true;
        else {
            requestPermissions(new String[]{permission}, reqId);
            return false;
        }
    }

}
