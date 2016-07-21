package www.julie.com.mobilemanager.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import www.julie.com.mobilemanager.bean.BlackNumberInfo;
import www.julie.com.mobilemanager.db.BlackPhoneDao;
import www.julie.com.mobilemanager.interator.Impl.MyBlackInteratorImpl;
import www.julie.com.mobilemanager.interator.MyBlackInterator;
import www.julie.com.mobilemanager.ui.adapter.BlackAdapter;
import www.julie.com.mobilemanager.view.MyBlackView;

/**
 * Created by liuxu on 16/7/4.
 */
public interface  MyBlackPresenter extends Presenter {
    void initData();
}
