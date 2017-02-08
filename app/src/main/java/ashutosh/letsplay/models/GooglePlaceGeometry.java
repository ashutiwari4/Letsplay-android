package ashutosh.letsplay.models;

/**
 * Created by ashutosh on 25/1/17.
 */

public class GooglePlaceGeometry {

    private GoogleLocationModel location;
    private GoogleViewPortModel viewport;

    public GoogleLocationModel getLocation() {
        return location;
    }

    public void setLocation(GoogleLocationModel location) {
        this.location = location;
    }

    public GoogleViewPortModel getViewport() {
        return viewport;
    }

    public void setViewport(GoogleViewPortModel viewport) {
        this.viewport = viewport;
    }
}
