package ashutosh.letsplay.models;

import java.util.List;

/**
 * Created by ashutosh on 25/1/17.
 */

public class GooglePhotosModel {

    private int height;
    private List html_attributions;
    private String photo_reference;
    private int width;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List html_attributions) {
        this.html_attributions = html_attributions;
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
