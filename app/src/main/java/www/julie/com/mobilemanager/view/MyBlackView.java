package www.julie.com.mobilemanager.view;

import android.widget.BaseAdapter;

/**
 * Created by liuxu on 16/7/3.
 */
public interface MyBlackView {
    void initView(String title, String subTitle);
    void setAdapter(BaseAdapter adapter);
}
