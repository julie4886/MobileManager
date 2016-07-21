package www.julie.com.mobilemanager.ui.activity;

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
import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.view.MyBlackView;

/**
 * Created by liuxu on 16/7/4.
 */
public class MessageBlackActivity extends BaseActivity implements MyBlackView {


    @BindView(R.id.icon_top_back)
    ImageView iconTopBack;
    @BindView(R.id.tv_appmanager_title)
    TextView tvAppmanagerTitle;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.list_view)
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_black);
        ButterKnife.bind(this);
    }

    @Override
    public void initView(String title, String subTitle) {
        tvAppmanagerTitle.setText(title);
        tvClear.setText(subTitle);

    }

    @Override
    public void setAdapter(BaseAdapter adapter) {

    }

    @Override
    void showPreviousPage() {

    }

    @Override
    void showNextPage() {

    }

    @OnClick({R.id.icon_top_back, R.id.tv_clear, R.id.img_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_top_back:
                break;
            case R.id.tv_clear:
                break;
            case R.id.img_add:
                break;
        }
    }


}
