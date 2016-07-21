package www.julie.com.mobilemanager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by liuxu on 16/6/6.
 */
public class SwipeListView extends ListView {
    private Boolean horizontal;
    private View mPreItemView;
    private View mCurrentItemView;

    private float mFirstX;
    private float mFirstY;
    private int mRightView = 300;

    private final int mDuration = 100;
    private final int mDurationStep = 10;
    private boolean Shown;

    public SwipeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeListView(Context context) {
        super(context);

    }
}
