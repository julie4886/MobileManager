package www.julie.com.mobilemanager.view;

import www.julie.com.mobilemanager.bean.BlackNumberInfo;

/**
 * Created by liuxu on 16/7/4.
 */
public interface SetBlackNumberView {
    void initView(String title, String subTitle);
    BlackNumberInfo saveInfo();
    String getString();
}
