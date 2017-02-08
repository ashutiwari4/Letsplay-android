package ashutosh.letsplay.retrofit;


import ashutosh.letsplay.models.GooglePlaceApiModel;
import ashutosh.letsplay.models.SongListModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by girish on 16/1/17.
 */
public interface ApiService {


    @GET("songs/")
    Call<SongListModel> getSongList(@Query("format") String format, @Query("page") String page);

    @GET("https://maps.googleapis.com/maps/api/place/textsearch/json")
    Call<GooglePlaceApiModel> getPlaces(@Query("query") String query, @Query("location") String location, @Query("radius") String radius, @Query("key") String key);


}
