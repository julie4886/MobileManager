package www.julie.com.mobilemanager.view;

import android.view.animation.Animation;

/**
 * Created by liuxu on 16/6/4.
 */
public interface SplashView {

    void animateBackgroundImage(Animation animation);

    void initViews(String versionName,String copyright,int backgroundResId);


    void navigateToHomePage();

}
