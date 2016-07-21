package www.julie.com.mobilemanager.presenter;

import android.content.Context;

import www.julie.com.mobilemanager.interator.CallSafeInterator;
import www.julie.com.mobilemanager.interator.Impl.CallSafeInteratorImpl;
import www.julie.com.mobilemanager.view.CallSafeView;

/**
 * Created by liuxu on 16/7/3.
 */

public class CallSafePresenter implements Presenter {
    CallSafeView callSafeView;
    CallSafeInterator callSafeInterator;
    Context context;

    public CallSafePresenter( Context context,CallSafeView callSafeView) {
        this.callSafeView = callSafeView;
        this.callSafeInterator = new CallSafeInteratorImpl();
        this.context = context;
    }


    @Override
    public void initialized() {
        callSafeView.initView(callSafeInterator.getTitle(),callSafeInterator.getMessageTitle());
    }
}
