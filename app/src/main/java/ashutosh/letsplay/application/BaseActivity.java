package ashutosh.letsplay.application;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import ashutosh.letsplay.R;

/**
 * Created by ashutosh on 20/1/17.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void replaceFragment(int id, Fragment fragment) {
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction().replace(id, fragment).addToBackStack(null).commit();
    }

    public Snackbar getSnackBar(int length, String msg, String actionMsg) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.ll_home), msg, length);
        return snackbar;

    }

    /*public void replaceFragmentWithAnimation(android.support.v4.app.Fragment fragment) {
        getSupportFragmentManager().popBackStack();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out, R.anim.slide_right_in, R.anim.slide_right_out);
        transaction.replace(R.id.fl_root, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }*/
}
