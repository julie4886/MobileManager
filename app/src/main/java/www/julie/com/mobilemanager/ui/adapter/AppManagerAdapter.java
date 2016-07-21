package www.julie.com.mobilemanager.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.bean.AppInfo;
import www.julie.com.mobilemanager.ui.activity.AppManagerActivity;

/**
 * Created by liuxu on 16/6/5.
 */
public class AppManagerAdapter extends BaseAdapter {
    private Context mContext;
    private List<AppInfo> systemAppInfos;
    private List<AppInfo> userAppInfos;
    private AppInfo appInfo;
    private LayoutInflater mInflater;

    public AppManagerAdapter(Context mContext, List<AppInfo> systemAppInfos, List<AppInfo> userAppInfos) {
        this.mContext = mContext;
        this.systemAppInfos = systemAppInfos;
        this.userAppInfos = userAppInfos;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return systemAppInfos.size() + 1 + userAppInfos.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        if (0 == position) {
            return null;
        } else if (position == userAppInfos.size() + 1) {
            return null;
        } else if (position < userAppInfos.size() + 1) {
            appInfo = userAppInfos.get(position - 1);
        } else {
            appInfo = systemAppInfos.get(position - userAppInfos.size() - 2);
        }
        return appInfo;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (0 == position) {
            TextView mUserBar = new TextView(mContext);
            mUserBar.setTextColor(Color.WHITE);
            mUserBar.setBackgroundColor(Color.GRAY);
            mUserBar.setText("用户程序(" + userAppInfos.size() + ")");
            return mUserBar;
        }
        if (userAppInfos.size() + 1 == position) {
            TextView mSysBar = new TextView(mContext);
            mSysBar.setTextColor(Color.WHITE);
            mSysBar.setBackgroundColor(Color.GRAY);
            mSysBar.setText("系统程序（" + systemAppInfos.size() + ")");
            return mSysBar;
        }


        AppInfo mAppInfo;
        if (position < userAppInfos.size() + 1) {
            mAppInfo = userAppInfos.get(position - 1);
            Log.i("julie", "M"+ mAppInfo.getApkName() + mAppInfo.getIcon());
        } else {
            mAppInfo = systemAppInfos.get(position - userAppInfos.size() - 2);
        }

        ViewHolder viewHolder = null;
        View view = null;
        if (null != convertView && convertView instanceof RelativeLayout) {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        } else {
            view = mInflater.inflate(R.layout.layout_appmanager_items, null);
            viewHolder = new ViewHolder();
            viewHolder.icon_app = (ImageView) view.findViewById(R.id.icon_app);
            viewHolder.tv_app = (TextView) view.findViewById(R.id.tv_appname);
            view.setTag(viewHolder);
        }
        viewHolder.icon_app.setImageDrawable(mAppInfo.getIcon());
        viewHolder.tv_app.setText(mAppInfo.getApkName());
        return view;
    }

    final class ViewHolder {
        ImageView icon_app;
        TextView tv_app;

    }
}



