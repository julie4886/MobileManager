package www.julie.com.mobilemanager.interator;

import android.content.Context;

import java.util.List;

import www.julie.com.mobilemanager.bean.AppInfo;

/**
 * Created by liuxu on 16/6/28.
 */
public interface TaskManagerInterator {
    List<AppInfo> getUserApp(Context context);
    List<AppInfo> getSysAPP(Context context);
}
