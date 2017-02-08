package ashutosh.letsplay.retrofit;

import android.content.Context;
import android.widget.Toast;


import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by girish on 16/1/17.
 */

public abstract class ResponseResolver<T> implements retrofit2.Callback<T>{

private final String TAG = getClass().getSimpleName();
    private final Context context;

    protected  ResponseResolver(Context context){
        this.context = context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if(response == null){

            showError("No response received");
            return;
        }

        T body = response.body();
        if(body == null){
            showError("Failed to parse");
            return;
        }

        onSuccess(body);
    }



    /**
     * Method to show error to the User
     * @param message
     */
    private void showError(String message) {

        // Never show errors if customized to false

        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();

    }

    /**
     * Override this method to receive success callbacks
     *
     * @param response
     */
    protected abstract void onSuccess(T response);

}
