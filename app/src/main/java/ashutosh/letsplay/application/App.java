package ashutosh.letsplay.application;

import android.support.multidex.MultiDexApplication;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import ashutosh.letsplay.R;
import ashutosh.letsplay.util.AppConstant;

/**
 * Created by ashutosh on 20/1/17.
 */

@ReportsCrashes(formUri = "http://yourserver.com/yourscript", // will not be used
        mailTo = AppConstant.TO, mode = ReportingInteractionMode.SILENT, resToastText = R.string.app_name)
public class App extends MultiDexApplication {


    @Override
    public void onCreate() {
        //ACRA.init(this);
        super.onCreate();
    }
}
