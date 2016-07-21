package www.julie.com.mobilemanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.presenter.CallSafePresenter;
import www.julie.com.mobilemanager.presenter.Presenter;
import www.julie.com.mobilemanager.view.CallSafeView;

/**
 * Created by liuxu on 16/7/2.
 */
public class CallSafeActivity extends BaseActivity implements CallSafeView {
    @BindView(R.id.icon_top_back)
    ImageView iconTopBack;
    @BindView(R.id.tv_appmanager_title)
    TextView tvAppmanagerTitle;
    @BindView(R.id.tv_black)
    TextView tvBlack;
    @BindView(R.id.img_pull_arrow)
    ImageView imgPullArrow;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_safe);
        ButterKnife.bind(this);
        presenter = new CallSafePresenter(this, this);
        presenter.initialized();

    }


    @Override
    void showPreviousPage() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();

    }

    @Override
    void showNextPage() {

    }

    @Override
    public void initView(String title, String messageTitle) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_text_01);
        TextView textView = (TextView) linearLayout.findViewById(R.id.tv_black);
        tvAppmanagerTitle.setText(title);
        textView.setText(messageTitle);


    }

    @OnClick({R.id.icon_top_back, R.id.layout_text, R.id.layout_text_01})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_top_back:
                showPreviousPage();
                break;
            case R.id.layout_text:
                startActivity(new Intent(this, MyBlackActivity.class));
                finish();
                overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
                break;
            case R.id.layout_text_01:
                break;
        }
    }
}
