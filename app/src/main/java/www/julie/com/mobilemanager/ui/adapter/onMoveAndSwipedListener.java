package www.julie.com.mobilemanager.ui.adapter;

/**
 * Created by liuxu on 16/6/7.
 */
public interface onMoveAndSwipedListener {
    boolean onItemMove(int fromPosition , int toPosition);
    void onItemDismiss(int position);
}
