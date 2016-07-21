package www.julie.com.mobilemanager.view;

import java.util.List;

import www.julie.com.mobilemanager.bean.HomeInfo;

/**
 * Created by liuxu on 16/6/25.
 */
public interface HomeView {
    void initView(List<HomeInfo> homeInfoList);
    void readyToNext(Class clz);
}
