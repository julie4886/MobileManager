package www.julie.com.mobilemanager.engine;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import www.julie.com.mobilemanager.bean.AppInfo;

/**
 * Created by julie on 2015/12/19.
 */
public class AppInfos {
    /**
     * 获取手机所用应用进程
     * @param context
     * @return
     */
    public static List<AppInfo> getAppInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();//获取到包的管理者
        List<AppInfo> packageInfos = new ArrayList<>();

        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        for (PackageInfo installedPackage : installedPackages) {
            AppInfo appInfo = new AppInfo();

            Drawable drawable = installedPackage.applicationInfo.loadIcon(packageManager);
            String apkName = installedPackage.applicationInfo.loadLabel(packageManager).toString();
            String packageName = installedPackage.packageName;
            String sourceDir = installedPackage.applicationInfo.sourceDir;
            appInfo.setIcon(drawable);
            appInfo.setApkName(apkName);
            appInfo.setApkPackageName(packageName);
            appInfo.setApkpath(sourceDir);

            File file = new File(sourceDir);
            long apkSize = file.length();
            appInfo.setApkSize(apkSize);

            int flags = installedPackage.applicationInfo.flags;
            if ((flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                appInfo.setUserApp(false);
            } else {
                appInfo.setUserApp(true);
            }

            if ((flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                appInfo.setIsRom(false);
            } else {
                appInfo.setIsRom(true);
            }

            packageInfos.add(appInfo);

        }

        return packageInfos;
    }
}
