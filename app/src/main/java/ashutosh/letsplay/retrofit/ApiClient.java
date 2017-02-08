package ashutosh.letsplay.retrofit;

import java.util.concurrent.TimeUnit;

import ashutosh.letsplay.remote.UrlConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by girish on 16/1/17.
 */

public class ApiClient {

    private static Retrofit retrofit;
    private static final int TIME_OUT_SECONDS = 30;

    private ApiClient(){

    }
    public static ApiService getService(){

        if(retrofit == null){

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(UrlConfig.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit .create(ApiService.class);
    }

}
