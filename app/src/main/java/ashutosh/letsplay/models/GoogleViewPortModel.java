package ashutosh.letsplay.models;

/**
 * Created by ashutosh on 25/1/17.
 */

public class GoogleViewPortModel {

    private GoogleNorthEastModel norteast;
    private GoogleSouthEastModel southeast;

    public GoogleNorthEastModel getNorteast() {
        return norteast;
    }

    public void setNorteast(GoogleNorthEastModel norteast) {
        this.norteast = norteast;
    }

    public GoogleSouthEastModel getSoutheast() {
        return southeast;
    }

    public void setSoutheast(GoogleSouthEastModel southeast) {
        this.southeast = southeast;
    }
}
