package ashutosh.letsplay.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ashutosh.letsplay.models.GooglePlaceApiModel;

/**
 * Created by ashutosh on 23/1/17.
 */

public final class AppPreferences {


    private static final String TAG = "AppSharedPreference";
    private static SharedPreferences sSharedPreferences;
    private static SharedPreferences.Editor editor;
    private static Context _context;
    private static int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "app_preference";
    private static AppPreferences uniqueInstance;


    /* Shared Preferences Keys */
    private static final String PAGE_NO = "page_no";

    private static final String INSTITUTE_JSON = "institute_json";


    private AppPreferences() {

    }

    public static AppPreferences getInstance(Context context) {
        if (uniqueInstance == null) {
            _context = context;
            sSharedPreferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = sSharedPreferences.edit();
            uniqueInstance = new AppPreferences();
        }
        return uniqueInstance;
    }

    public int getPageNo() {
        return sSharedPreferences.getInt(PAGE_NO, 1);
    }


    public void setPageNo(int pageNo) {
        synchronized (this) {
            editor.putInt(PAGE_NO, pageNo);
            editor.apply();
        }
    }


    public void commitInstitute(String json) {
        editor.putString(INSTITUTE_JSON, json);
        editor.apply();
    }

    public GooglePlaceApiModel getInstitute() {
        return new Gson().fromJson(sSharedPreferences.getString(INSTITUTE_JSON,null), new TypeToken<GooglePlaceApiModel>() {}.getType());
    }


}
