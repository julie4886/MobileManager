package www.julie.com.mobilemanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.bean.BlackNumberInfo;
import www.julie.com.mobilemanager.presenter.Presenter;
import www.julie.com.mobilemanager.presenter.SetBlackNumberPresenter;
import www.julie.com.mobilemanager.presenter.SetBlackNumberPresenterImpl;
import www.julie.com.mobilemanager.view.SetBlackNumberView;

/**
 * Created by liuxu on 16/7/4.
 */
public class SetBlackNumberActivity extends BaseActivity implements SetBlackNumberView {
    @BindView(R.id.icon_top_back)
    ImageView iconTopBack;
    @BindView(R.id.tv_appmanager_title)
    TextView tvAppmanagerTitle;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.ed_number)
    EditText edNumber;
    @BindView(R.id.img_add)
    ImageView imgAdd;
    private SetBlackNumberPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_black_number);
        ButterKnife.bind(this);
        presenter = new SetBlackNumberPresenterImpl(this, this);
        presenter.initialized();
    }

    @Override
    void showPreviousPage() {
        startActivity(new Intent(this, MyBlackActivity.class));
        finish();
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);

    }

    @Override
    void showNextPage() {

    }

    @Override
    public void initView(String title, String subTitle) {
        tvAppmanagerTitle.setText(title);
        tvClear.setText(subTitle);
    }

    @Override
    public BlackNumberInfo saveInfo() {
        BlackNumberInfo info = new BlackNumberInfo();
        info.setNumber(getString());
        info.setMode("2");
        return info;
    }

    @Override
    public String getString() {
        return edNumber.getText().toString();
    }

    @OnClick({R.id.icon_top_back, R.id.tv_clear, R.id.img_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_top_back:
                showPreviousPage();
                break;
            case R.id.tv_clear:
                if (TextUtils.isEmpty(getString())) {
                    Toast.makeText(SetBlackNumberActivity.this, "请输入电话号码", Toast.LENGTH_LONG).show();
                } else {
                    presenter.saveData();
                    showPreviousPage();
                }

                break;
            case R.id.img_add:
                break;
        }
    }
}
