package www.julie.com.mobilemanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.bean.HomeInfo;

/**
 * Created by liuxu on 16/6/4
 */
public class HomeAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<HomeInfo> homeInfoList = null;

    public HomeAdapter(Context mContext, List<HomeInfo> homeInfoList) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.homeInfoList = homeInfoList;
    }

    @Override
    public int getCount() {
        return homeInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return homeInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.layout_home_gridview_items, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.imageView.setImageResource(homeInfoList.get(i).getIconId());
        holder.textView.setText(homeInfoList.get(i).getTitle());
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.icon_item)
        ImageView imageView;
        @BindView(R.id.tv_item)
        TextView textView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
//
//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
    }
}
