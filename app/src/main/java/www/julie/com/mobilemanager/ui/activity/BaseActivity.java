package www.julie.com.mobilemanager.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liuxu on 16/6/4.
 */
public abstract class BaseActivity extends Activity {
    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1.getRawX() - e2.getRawX() > 200) {
                    showNextPage();
                    return true;
                } else if (e2.getRawX() - e1.getRawX() > 200) {
                    showPreviousPage();
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }

        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    //跳转
    abstract void showPreviousPage();

    abstract void showNextPage();
}
