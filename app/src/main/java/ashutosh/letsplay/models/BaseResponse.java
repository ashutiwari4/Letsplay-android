package ashutosh.letsplay.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by girish on 16/1/17.
 */

public class BaseResponse {

    @SerializedName("error_code")
    private int status;

    @SerializedName("response_string")
    private String message;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
