package www.julie.com.mobilemanager.interator.Impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import www.julie.com.mobilemanager.bean.AppInfo;
import www.julie.com.mobilemanager.interator.AppManagerInterator;

/**
 * Created by liuxu on 16/6/26.
 */
public class AppMannagerImpl implements AppManagerInterator {

    @Override
    public List<AppInfo> getAppInfo(Context context) {
        //得到一个java保证的 包管理器。
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packInfos = pm.getInstalledPackages(0);
        List<AppInfo> appinfos = new ArrayList<AppInfo>();
        for(PackageInfo packInfo:packInfos){
            AppInfo appinfo = new AppInfo();
            String packname = packInfo.packageName;
            appinfo.setApkPackageName(packname);
            Drawable icon = packInfo.applicationInfo.loadIcon(pm);
            appinfo.setIcon(icon);
            String appname = packInfo.applicationInfo.loadLabel(pm).toString();
            appinfo.setApkName(appname);
            //应用程序apk包的路径
            String apkpath = packInfo.applicationInfo.sourceDir;
            appinfo.setApkpath(apkpath);
            File file = new File(apkpath);
            long appSize = file.length();
            appinfo.setApkSize(appSize);
            //应用程序安装的位置。
            int flags = packInfo.applicationInfo.flags; //二进制映射  大bit-map
            if((ApplicationInfo.FLAG_EXTERNAL_STORAGE&flags)!=0){
                //外部存储
                appinfo.setIsRom(false);
            }else{
                //手机内存
                appinfo.setIsRom(true);
            }
            if((ApplicationInfo.FLAG_SYSTEM&flags)!=0){
                //系统应用
                appinfo.setUserApp(false);
            }else{
                //用户应用
                appinfo.setUserApp(true);
            }
            appinfos.add(appinfo);
            appinfo = null;
        }
        return appinfos;

    }
}
