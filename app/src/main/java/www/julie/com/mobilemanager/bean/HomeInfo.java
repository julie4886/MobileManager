package www.julie.com.mobilemanager.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liuxu on 15/6/26.
 */
public class HomeInfo {
    int iconId;
    String title;

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
