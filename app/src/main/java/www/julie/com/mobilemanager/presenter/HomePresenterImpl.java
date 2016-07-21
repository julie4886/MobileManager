package www.julie.com.mobilemanager.presenter;

import android.content.Context;

import butterknife.OnItemClick;
import www.julie.com.mobilemanager.interator.Impl.HomeInteratorImpl;
import www.julie.com.mobilemanager.ui.adapter.HomeAdapter;
import www.julie.com.mobilemanager.view.HomeView;

/**
 * Created by liuxu on 16/6/26.
 */
public class HomePresenterImpl implements Presenter {
    private HomeView homeView;
    private HomeInteratorImpl homeInterator;
    private Context context;

    public HomePresenterImpl(HomeView homeView,  Context context) {
        this.homeView = homeView;
        this.context = context;
        homeInterator = new HomeInteratorImpl();
    }

    @Override
    public void initialized() {
        homeView.initView( homeInterator.getHomeInfoData());
    }
}
