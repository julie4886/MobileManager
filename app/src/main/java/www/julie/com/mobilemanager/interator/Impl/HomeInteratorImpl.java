package www.julie.com.mobilemanager.interator.Impl;

import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.bean.HomeInfo;
import www.julie.com.mobilemanager.interator.HomeInterator;

/**
 * Created by liuxu on 16/6/26.
 */
public class HomeInteratorImpl implements HomeInterator {
    @Override
    public List<HomeInfo> getHomeInfoData() {
        List<HomeInfo> homeInfoList = new ArrayList<>();
        String[] mStrItems = {"手机杀毒", "通讯卫士", "软件管理", "进程控制", "流量统计", "工具箱"};
        int[] iconIds = {R.mipmap.home_trojan,
                R.mipmap.home_callmsgsafe,
                R.mipmap.home_apps,
                R.mipmap.home_taskmanager,
              };

        for (int i = 0; i < 4; i++) {
            HomeInfo info = new HomeInfo();
            info.setIconId(iconIds[i]);
            info.setTitle(mStrItems[i]);
            homeInfoList.add(info);
        }
        return homeInfoList;
    }
}
