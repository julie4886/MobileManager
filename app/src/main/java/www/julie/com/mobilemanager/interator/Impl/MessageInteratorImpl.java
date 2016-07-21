package www.julie.com.mobilemanager.interator.Impl;

import www.julie.com.mobilemanager.interator.MessageBlackInterator;

/**
 * Created by liuxu on 16/7/4.
 */
public class MessageInteratorImpl implements MessageBlackInterator {
    @Override
    public String getTitle() {
        return "短信黑名单";
    }

    @Override
    public String getSubTitle() {
        return "保存";
    }
}
