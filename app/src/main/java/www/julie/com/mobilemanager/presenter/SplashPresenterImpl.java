package www.julie.com.mobilemanager.presenter;

import android.content.Context;
import android.view.animation.Animation;

import www.julie.com.mobilemanager.interator.SplashInteractor;
import www.julie.com.mobilemanager.interator.Impl.SplashInteratorImpl;
import www.julie.com.mobilemanager.view.SplashView;

/**
 * Created by liuxu on 16/6/4.
 */
public class SplashPresenterImpl implements Presenter {

    private Context mContext;
    private SplashInteractor mSplashInteractor;
    private SplashView mSplashView;

    public SplashPresenterImpl(Context context, SplashView splashView) {
        if (null == splashView) {
            throw new IllegalArgumentException("Constructor's parameters must not be Null");
        }
        mContext = context;
        mSplashView = splashView;
        mSplashInteractor = new SplashInteratorImpl();
    }


    @Override
    public void initialized() {
        mSplashView.initViews(mSplashInteractor.getVersionName(mContext),
                mSplashInteractor.getCopyright(mContext),
                mSplashInteractor.getBackgroundImageResID());
        Animation animation = mSplashInteractor.getBackgroundImageAnimation(mContext);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mSplashView.navigateToHomePage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mSplashView.animateBackgroundImage(animation);

    }
}
