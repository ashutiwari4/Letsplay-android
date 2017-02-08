package ashutosh.letsplay.models;

import java.util.List;

/**
 * Created by ashutosh on 25/1/17.
 */

public class GoogleOpeningHours {
    private boolean open_now;
    private List weekday_text;

    public boolean isOpen_now() {
        return open_now;
    }

    public void setOpen_now(boolean open_now) {
        this.open_now = open_now;
    }

    public List getWeekday_text() {
        return weekday_text;
    }

    public void setWeekday_text(List weekday_text) {
        this.weekday_text = weekday_text;
    }
}
