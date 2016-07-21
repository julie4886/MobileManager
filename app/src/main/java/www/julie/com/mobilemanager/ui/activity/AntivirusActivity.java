package www.julie.com.mobilemanager.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.presenter.AntivirusPresenter;
import www.julie.com.mobilemanager.presenter.AntivirusPresenterImpl;
import www.julie.com.mobilemanager.view.AntivirusView;

/**
 * Created by liuxu on 16/6/8.
 */
public class AntivirusActivity extends BaseActivity implements AntivirusView {
    @BindView(R.id.icon_top_back)
    ImageView iconTopBack;
    @BindView(R.id.tv_appmanager_title)
    TextView tvAppmanagerTitle;
    @BindView(R.id.img_scanner)
    ImageView imgScanner;
    @BindView(R.id.proBar)
    ProgressBar proBar;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    private RotateAnimation rotateAnimation;
    private Unbinder unbinder;
    AntivirusPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_antivirus);
        unbinder = ButterKnife.bind(this);
        presenter = new AntivirusPresenterImpl(this, this);
        presenter.initialized();
        presenter.initData();


    }


    @OnClick(R.id.icon_top_back)
    void OnClick() {
        showPreviousPage();
    }


    @Override
    void showPreviousPage() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
        overridePendingTransition(R.anim.tran_previous_in, R.anim.tran_previous_out);


    }

    @Override
    void showNextPage() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void animateBackgroundImage(Animation animation) {
        imgScanner.startAnimation(animation);
    }

    @Override
    public void setProgressValue(int i) {
        if (proBar != null)
            proBar.setProgress(i);
    }


    @Override
    public void initView(int i) {
        proBar.setMax(i);

    }

    @Override
    public void setProText(String string) {
        if (tvMessage != null)
            tvMessage.setText(string);

    }

    @Override
    public void showSelectDialog(int count) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(AntivirusActivity.this);
        dialog.setTitle("扫描结果");
        if (count == 0) {
            dialog.setMessage("扫描结束，手机安全");
            dialog.setCancelable(false);
            dialog.setPositiveButton("重新扫描", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    presenter.initData();
                    dialog.dismiss();


                }
            });
            dialog.setNegativeButton("返回", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        } else {
            dialog.setMessage("扫描结束，存在病毒，是否清除")
                    .setCancelable(false)
                    .setPositiveButton("清理", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            presenter.clearTrojia();
                            dialogInterface.dismiss();
                        }
                    });
            dialog.setNegativeButton("返回", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

        }
        dialog.show();

    }

    @Override
    public void addChileView(TextView textView) {
        if (llContent != null) {
            llContent.addView(textView);
        }
        if (scrollView != null) {
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.fullScroll(scrollView.FOCUS_DOWN);
                }
            });
        }

    }


    @Override
    public void StopAnimateImage() {
        if (imgScanner != null) {
            imgScanner.clearAnimation();
            imgScanner.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        showPreviousPage();
        return super.onKeyDown(keyCode, event);

    }
}
