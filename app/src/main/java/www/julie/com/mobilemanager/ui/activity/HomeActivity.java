package www.julie.com.mobilemanager.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.bean.HomeInfo;
import www.julie.com.mobilemanager.presenter.HomePresenterImpl;
import www.julie.com.mobilemanager.presenter.Presenter;
import www.julie.com.mobilemanager.ui.adapter.AppManagerAdapter;
import www.julie.com.mobilemanager.ui.adapter.HomeAdapter;
import www.julie.com.mobilemanager.ui.adapter.ToolManagerAdapter;
import www.julie.com.mobilemanager.view.HomeView;

/**
 * Created by liuxu on 16/6/4.
 */
public class HomeActivity extends Activity implements HomeView {
    @BindView(R.id.img_home)
    ImageView imgHome;
    @BindView(R.id.gv_home)
    GridView mGridView;
    private HomeAdapter adapter;
    private Presenter presenter;
    private Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        unbinder = ButterKnife.bind(this);
        presenter = new HomePresenterImpl(this, this);
        presenter.initialized();


    }

    @OnItemClick(R.id.gv_home)
    void onItemClick(int position) {
        switch (position) {
            case 0:
                readyToNext(AntivirusActivity.class);
                break;
            case 1:
                readyToNext(CallSafeActivity.class);
                break;
            case 2:
                readyToNext(AppManagerActivity.class);

                break;
            case 3:
                readyToNext(TaskManagerActivity.class);
                break;
            default:
                break;
        }

    }


    @Override
    public void initView(List<HomeInfo> homeInfoList) {
        adapter = new HomeAdapter(this, homeInfoList);
        mGridView.setAdapter(adapter);
    }

    @Override
    public void readyToNext(Class clz) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
