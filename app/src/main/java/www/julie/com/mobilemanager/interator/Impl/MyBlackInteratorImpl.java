package www.julie.com.mobilemanager.interator.Impl;

import www.julie.com.mobilemanager.interator.MyBlackInterator;

/**
 * Created by liuxu on 16/7/4.
 */
public class MyBlackInteratorImpl implements MyBlackInterator {
    @Override
    public String getTitle() {
        return "电话黑名单";
    }

    @Override
    public String getSubTitle() {
        return "添加";
    }
}
