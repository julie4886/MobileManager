package www.julie.com.mobilemanager.interator.Impl;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.util.ArrayList;
import java.util.List;

import www.julie.com.mobilemanager.bean.AppInfo;
import www.julie.com.mobilemanager.engine.AppInfos;
import www.julie.com.mobilemanager.interator.TaskManagerInterator;

/**
 * Created by liuxu on 16/6/28.
 */
public class TaskManagerInteratorImpl implements TaskManagerInterator {
    @Override
    public List<AppInfo> getUserApp(Context context) {
        List<AppInfo> userAppInfos = new ArrayList<AppInfo>();
        List<AppInfo> packageInfo = AppInfos.getAppInfo(context);

        return null;
    }

    @Override
    public List<AppInfo> getSysAPP(Context context) {
        return null;
    }
}
