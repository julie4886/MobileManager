package www.julie.com.mobilemanager.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.ui.adapter.AppManagerAdapter;
import www.julie.com.mobilemanager.ui.adapter.AppOperationAdapter;

/**
 * Created by liuxu on 16/6/5.
 */
public class AppOperationActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView mListView;
    private String[] mList;
    private TextView title;
    private int position;
    private String appName;
    private ImageView iconBack;
    private String apkPackageName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_app_operation);
        initIntent();
        initView();

    }

    @Override
    void showPreviousPage() {
        startActivity(new Intent(this, AppManagerActivity.class));
        finish();
        overridePendingTransition(R.anim.tran_previous_in, R.anim.tran_previous_out);
    }

    @Override
    void showNextPage() {

    }

    /**
     * 获取Intent
     */
    private void initIntent() {
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 1);
        appName = intent.getStringExtra("appname");
        apkPackageName = intent.getStringExtra("apkpackagename");
    }

    /**
     * 初始化界面的组件
     */
    private void initView() {
        mListView = (ListView) findViewById(R.id.lv_app_operation);
        View subLayout = (View) findViewById(R.id.operation_title);
        title = (TextView) subLayout.findViewById(R.id.tv_appmanager_title);
        iconBack = (ImageView) subLayout.findViewById(R.id.icon_top_back);

        mList = this.getResources().getStringArray(R.array.operation_array);
        AppOperationAdapter mAdapter = new AppOperationAdapter(AppOperationActivity.this, mList);
        mListView.setAdapter(mAdapter);

        title.setText(appName);
        iconBack.setOnClickListener(this);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_top_back:
                startActivity(new Intent(this, AppManagerActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        switch (position) {
            //启动应用程序
            case 0:
                Intent startIntent = this.getPackageManager().getLaunchIntentForPackage(apkPackageName);
                this.startActivity(startIntent);
                overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
                break;
            //卸载应用程序
            case 1:
                Intent uninstallIntent = new Intent("android.intent.action.DELETE", Uri.parse("package:" + apkPackageName));
                this.startActivity(uninstallIntent);
                break;
            // 分享应用程序
            case 2:
                Intent shareIntent = new Intent("android.intent.action.SEND");
                shareIntent.setType("text/plain");
                shareIntent.putExtra("android.intent.extra.SUBJECT", "分享");
                shareIntent.putExtra("android.intent.extra.TEXT", "Hi,推荐你使用软件：" + appName +
                        "下载地址：" + "https://play.google.com/store/apps/details?id=" + apkPackageName);
                this.startActivity(Intent.createChooser(shareIntent, "分享"));
                break;
            //应用程序的详细信息
            case 3:
                Intent detailIntent = new Intent();
                detailIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                detailIntent.addCategory(Intent.CATEGORY_DEFAULT);
                detailIntent.setData(Uri.parse("package:" + apkPackageName));
                this.startActivity(detailIntent);
                overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
                break;
            default:
                break;

        }
    }
}
