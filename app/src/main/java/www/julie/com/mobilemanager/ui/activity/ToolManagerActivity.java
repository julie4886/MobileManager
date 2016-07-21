package www.julie.com.mobilemanager.ui.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.ui.adapter.ToolManagerAdapter;

/**
 * Created by liuxu on 16/6/7.
 */
public class ToolManagerActivity extends BaseActivity {
    @BindView(R.id.icon_top_back)
    ImageView iconTopBack;
    @BindView(R.id.lv_tools)
    ListView lvTools;
    @BindView(R.id.tv_appmanager_title)
    TextView tvAppmanagerTitle;
    private Unbinder unbinder;
    private String[] tools = null;
    private int[] iconIds = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolmanager);
        unbinder = ButterKnife.bind(this);
        initData();
        initView();
    }

    @Override
    void showPreviousPage() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    private void initView() {
        ToolManagerAdapter adapter = new ToolManagerAdapter(this, tools, iconIds);
        lvTools.setAdapter(adapter);
    }

    private void initData() {
        tools = this.getResources().getStringArray(R.array.tool_strings);
        //获取图片资源的ID
        TypedArray tr = this.getResources().obtainTypedArray(R.array.home_icon_array);
        int length = tr.length();
        iconIds = new int[length];
        for (int i = 0; i < length; i++) {
            iconIds[i] = tr.getResourceId(i, 0);
        }
        tvAppmanagerTitle.setText(this.getString(R.string.title_tool_manager));

    }

    @OnItemClick(R.id.lv_tools)
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(this,AntivirusActivity.class));
                finish();
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }

    }

    @Override
    void showNextPage() {

    }

    @OnClick(R.id.icon_top_back)
    public void onClick() {
        showPreviousPage();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
