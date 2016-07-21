package www.julie.com.mobilemanager.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.bean.AppInfo;
import www.julie.com.mobilemanager.engine.AppInfos;
import www.julie.com.mobilemanager.ui.adapter.AppManagerAdapter;

/**
 * Created by liuxu on 16/6/5.
 */
public class AppManagerActivity extends Activity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener, View.OnClickListener {
    private final static String TAG = "julie";
    @BindView(R.id.icon_top_back)
    ImageView mIconBack;
    @BindView(R.id.lv_appmanager_userinfo)
    ListView mListView;
    @BindView(R.id.tv_app)
    TextView mAppInfoCount;
    @BindView(R.id.proBar)
    ProgressBar proBar;
    private List<AppInfo> systemAppInfos;
    private List<AppInfo> userAppInfos;
    private List<AppInfo> packageInfos;
    private AppInfo clickAppInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_appmanager);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.lv_appmanager_userinfo);
        mIconBack = (ImageView) findViewById(R.id.icon_top_back);
        mAppInfoCount = (TextView) findViewById(R.id.tv_app);

        mListView.setOnItemClickListener(this);
        mListView.setOnScrollListener(this);
        mIconBack.setOnClickListener(this);

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                AppManagerAdapter adapter = new AppManagerAdapter(AppManagerActivity.this, systemAppInfos, userAppInfos);
                mListView.setAdapter(adapter);
                proBar.setVisibility(View.GONE);
                overridePendingTransition(R.anim.tran_in,R.anim.tran_out);
                adapter.notifyDataSetChanged();
            }
        }
    };

    /**
     * 启动子线程去读取手机中的应用信息，并发送信息
     */
    private void initData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                packageInfos = AppInfos.getAppInfo(AppManagerActivity.this);
                userAppInfos = new ArrayList<AppInfo>();
                systemAppInfos = new ArrayList<AppInfo>();

                for (AppInfo appInfo : packageInfos) {
                    if (appInfo.getUserApp()) {
                        userAppInfos.add(appInfo);
                    } else {
                        systemAppInfos.add(appInfo);
                    }
                }
                mHandler.sendEmptyMessage(0);

            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_top_back:
                startActivity(new Intent(this, HomeActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        if (userAppInfos != null && systemAppInfos != null) {
            if (i <= userAppInfos.size() + 1) {
                mAppInfoCount.setText("用户用户(" + userAppInfos.size() + ")个");
            } else {
                mAppInfoCount.setText("系统应用(" + systemAppInfos.size() + ")个");
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Object obj = mListView.getItemAtPosition(position);
        String appName;
        String apkPackageName;

        if (obj != null && obj instanceof AppInfo) {
            clickAppInfo = (AppInfo) obj;
            appName = clickAppInfo.getApkName();
            apkPackageName = clickAppInfo.getApkPackageName();
            if (position == 0 || position == userAppInfos.size() + 1) {
                return;
            } else {
                Intent intent = new Intent(this, AppOperationActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("appname", appName);
                intent.putExtra("apkpackagename", apkPackageName);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.tran_in, R.anim.tran_out);

            }
        }

    }

}
