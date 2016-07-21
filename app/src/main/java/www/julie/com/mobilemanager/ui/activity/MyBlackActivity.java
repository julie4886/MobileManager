package www.julie.com.mobilemanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.presenter.MyBlackPresenter;
import www.julie.com.mobilemanager.presenter.MyBlackPresenterImpl;
import www.julie.com.mobilemanager.presenter.Presenter;
import www.julie.com.mobilemanager.view.MyBlackView;


/**
 * Created by liuxu on 16/7/3.
 */
public class MyBlackActivity extends BaseActivity implements MyBlackView {
    @BindView(R.id.icon_top_back)
    ImageView iconTopBack;
    @BindView(R.id.tv_appmanager_title)
    TextView tvAppmanagerTitle;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.list_view)
    ListView listView;
    private Unbinder unbinder;
    private  MyBlackPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_black);
        unbinder = ButterKnife.bind(this);
        presenter = new MyBlackPresenterImpl(this, this);
        presenter.initialized();
        presenter.initData();

    }

    @Override
    public void initView(String title, String subTitle) {
        tvAppmanagerTitle.setText(title);
        tvClear.setText(subTitle);

    }

    @Override
    public void setAdapter(@Nullable BaseAdapter adapter) {
        listView.setAdapter(adapter);
    }


    @Override
    void showPreviousPage() {
        startActivity(new Intent(this, CallSafeActivity.class));
        finish();
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
    }

    @Override
    void showNextPage() {
        startActivity(new Intent(this, SetBlackNumberActivity.class));
        finish();
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);

    }

    @OnClick({R.id.icon_top_back, R.id.tv_clear})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_top_back:
                showPreviousPage();
                break;
            case R.id.tv_clear:
                showNextPage();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
