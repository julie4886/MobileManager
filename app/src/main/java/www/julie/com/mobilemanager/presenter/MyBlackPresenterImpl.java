package www.julie.com.mobilemanager.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

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
public class MyBlackPresenterImpl implements MyBlackPresenter {
    private Context context;
    private MyBlackInterator myBlackInterator;
    private MyBlackView myBlackView;
    private BlackAdapter adapter;
    private List<BlackNumberInfo> blackNumberInfoList;
    private BlackPhoneDao dao;

    private int start = 0;
    private int maxCount = 20;
    private int totalNumber;

    public MyBlackPresenterImpl(MyBlackView myBlackView, Context context) {
        this.myBlackView = myBlackView;
        this.myBlackInterator = new MyBlackInteratorImpl();
        this.context = context;
    }

    @Override
    public void initialized() {
        myBlackView.initView(myBlackInterator.getTitle(), myBlackInterator.getSubTitle());
        dao = new BlackPhoneDao(context);
        blackNumberInfoList = new ArrayList<>();
        adapter = new BlackAdapter(context, blackNumberInfoList);
        myBlackView.setAdapter(adapter);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x01) {
                if (adapter == null) {
                    adapter = new BlackAdapter(context, blackNumberInfoList);
                    myBlackView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }
        }
    };

    @Override
    public void initData() {
        totalNumber = dao.getTotalNumber();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (blackNumberInfoList == null) {
                    blackNumberInfoList = dao.findPar(start, maxCount);
                } else {
                    blackNumberInfoList.addAll(dao.findPar(start, maxCount));
                }
                handler.sendEmptyMessage(0x01);
            }
        }).start();

    }
}
