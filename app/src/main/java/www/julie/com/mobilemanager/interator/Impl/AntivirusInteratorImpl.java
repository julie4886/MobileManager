package www.julie.com.mobilemanager.interator.Impl;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import java.util.List;

import www.julie.com.mobilemanager.interator.AntivirusInterator;

/**
 * Created by liuxu on 16/6/28.
 */
public class AntivirusInteratorImpl implements AntivirusInterator {
    int proValue = 0;
    int maxProValue = 0;


    @Override
    public Animation getImageAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        return rotateAnimation;
    }

    @Override
    public int setProgressValue(int process) {
        return process;
    }

    @Override
    public int setMaxProValue() {
        return maxProValue;
    }

    @Override
    public String setTextValue(String str) {
        return str;
    }

    @Override
    public List<PackageInfo> getPackageInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        maxProValue = packageInfos.size();
        return packageInfos;
    }
}
