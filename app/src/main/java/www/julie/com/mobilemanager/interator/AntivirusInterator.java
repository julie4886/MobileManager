package www.julie.com.mobilemanager.interator;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.view.animation.Animation;

import java.util.List;

/**
 * Created by liuxu on 15/6/28.
 */
public interface AntivirusInterator {
    Animation getImageAnimation();

    int setProgressValue(int process);
    int setMaxProValue();

    String setTextValue(String str);

    List<PackageInfo> getPackageInfo(Context context);
}
