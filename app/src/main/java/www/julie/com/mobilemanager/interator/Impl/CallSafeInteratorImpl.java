package www.julie.com.mobilemanager.interator.Impl;

import www.julie.com.mobilemanager.interator.CallSafeInterator;

/**
 * Created by liuxu on 16/7/3.
 */
public class CallSafeInteratorImpl implements CallSafeInterator {
    @Override
    public String getTitle() {
        return "妨骚扰";
    }

    @Override
    public String getMessageTitle() {
        return "短信黑名单";
    }
}
