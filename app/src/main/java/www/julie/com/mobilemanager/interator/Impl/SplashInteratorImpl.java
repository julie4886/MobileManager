package www.julie.com.mobilemanager.interator.Impl;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.interator.SplashInteractor;

import static www.julie.com.mobilemanager.R.string.splash_version;

/**
 * Created by liuxu on 16/6/4.
 */
public class SplashInteratorImpl implements SplashInteractor {

    @Override
    public int getBackgroundImageResID() {
        int resId;
        resId = R.drawable.night;
        return resId;
    }

    @Override
    public String getCopyright(Context context) {
        return context.getResources().getString(R.string.splash_copyright);

    }

    @Override
    public String getVersionName(Context context) {
        return context.getResources().getString(R.string.splash_version);
    }

    @Override
    public Animation getBackgroundImageAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.splash_amin);
    }
}
