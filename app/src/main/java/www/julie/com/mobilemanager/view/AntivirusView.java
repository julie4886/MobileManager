package www.julie.com.mobilemanager.view;

import android.view.animation.Animation;
import android.widget.TextView;

/**
 * Created by liuxu on 16/6/28.
 */
public interface AntivirusView {
    void animateBackgroundImage(Animation animation);

    void setProgressValue(int i);

    void initView(int i);

    void setProText(String string);

    void showSelectDialog(int count);

    void addChileView(TextView textView);

    void StopAnimateImage();

}
