package www.julie.com.mobilemanager.interator.Impl;

import www.julie.com.mobilemanager.bean.BlackNumberInfo;
import www.julie.com.mobilemanager.db.BlackPhoneDao;
import www.julie.com.mobilemanager.interator.SetBlackNumberInterator;

/**
 * Created by liuxu on 16/7/4.
 */
public class SetBlackNumberInteratorImpl implements SetBlackNumberInterator {

    @Override
    public String getTitle() {
        return "添加黑名单";
    }

    @Override
    public String getSubTitle() {
        return "保存";
    }

    @Override
    public BlackNumberInfo getInfo() {

        return null;
    }
}
