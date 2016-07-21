package www.julie.com.mobilemanager.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.presenter.Presenter;
import www.julie.com.mobilemanager.presenter.SplashPresenterImpl;
import www.julie.com.mobilemanager.view.SplashView;

/**
 * Created by liuxu on 16/6/4.
 */
public class SplashActivity extends Activity implements SplashView {


    @BindView(R.id.splash_app_name)
    TextView splashAppName;
    @BindView(R.id.splash_slogan)
    TextView splashSlogan;
    @BindView(R.id.splash_image)
    ImageView splashImage;
    @BindView(R.id.splash_version_name)
    TextView splashVersionName;
    @BindView(R.id.splash_copyright)
    TextView splashCopyright;

    private Unbinder unbinder;
    private Presenter splashPresenter;
    private Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//       final  View view = View.inflate(this, R.layout.layout_splash,null);
        setContentView(R.layout.layout_splash);
        unbinder = ButterKnife.bind(this);
        Log.i("julie", "oncreated");
        splashPresenter = new SplashPresenterImpl(this, this);
        splashPresenter.initialized();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void animateBackgroundImage(Animation animation) {
        splashImage.startAnimation(animation);
    }

    @Override
    public void initViews(String versionName, String copyright, int backgroundResId) {
        splashVersionName.setText(versionName);
        splashCopyright.setText(copyright);
        splashImage.setImageResource(backgroundResId);
    }


    @Override
    public void navigateToHomePage() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        Log.i("julie", "ondestory");
    }
}
