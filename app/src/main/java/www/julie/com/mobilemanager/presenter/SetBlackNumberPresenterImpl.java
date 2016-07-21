package www.julie.com.mobilemanager.presenter;

import android.content.Context;
import android.widget.Toast;

import www.julie.com.mobilemanager.bean.BlackNumberInfo;
import www.julie.com.mobilemanager.db.BlackPhoneDao;
import www.julie.com.mobilemanager.interator.SetBlackNumberInterator;
import www.julie.com.mobilemanager.interator.Impl.SetBlackNumberInteratorImpl;
import www.julie.com.mobilemanager.view.SetBlackNumberView;

/**
 * Created by liuxu on 16/7/4.
 */
public class SetBlackNumberPresenterImpl implements SetBlackNumberPresenter {
    private Context context;
    private SetBlackNumberView setBlackNumberView;
    private SetBlackNumberInterator interator;
    private BlackPhoneDao dao;

    public SetBlackNumberPresenterImpl(Context context, SetBlackNumberView setBlackNumberView) {
        this.context = context;
        this.setBlackNumberView = setBlackNumberView;
        interator = new SetBlackNumberInteratorImpl();
        dao = new BlackPhoneDao(context);
    }

    @Override
    public void initialized() {
        setBlackNumberView.initView(interator.getTitle(), interator.getSubTitle());

    }

    @Override
    public void saveData() {
        BlackNumberInfo info = setBlackNumberView.saveInfo();
        if(dao.findNumber(info.getNumber()) == "2") {
            Toast.makeText(context, "已经在电话黑名单中", Toast.LENGTH_LONG).show();
        }
        dao.add(info.getNumber(), info.getMode());
    }
}
