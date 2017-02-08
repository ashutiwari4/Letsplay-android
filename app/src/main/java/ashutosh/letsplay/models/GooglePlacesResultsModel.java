package ashutosh.letsplay.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ashutosh on 25/1/17.
 */

public class GooglePlacesResultsModel {

    @SerializedName("formatted_address")
    private String address;
    @SerializedName("geometry")
    private GooglePlaceGeometry googlePlaceGeometry;
    private GoogleOpeningHours opening_hours;
    private String icon;
    private String id;
    private String name;
    private String place_id;
    private double rating;
    private String reference;
    private List<GooglePhotosModel> photos;
    private List types;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GooglePlaceGeometry getGooglePlaceGeometry() {
        return googlePlaceGeometry;
    }

    public void setGooglePlaceGeometry(GooglePlaceGeometry googlePlaceGeometry) {
        this.googlePlaceGeometry = googlePlaceGeometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoogleOpeningHours getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(GoogleOpeningHours opening_hours) {
        this.opening_hours = opening_hours;
    }

    public List<GooglePhotosModel> getPhotos() {
        return photos;
    }

    public void setPhotos(List<GooglePhotosModel> photos) {
        this.photos = photos;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List getTypes() {
        return types;
    }

    public void setTypes(List types) {
        this.types = types;
    }
}
