package www.julie.com.mobilemanager.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.julie.com.mobilemanager.R;

/**
 * Created by liuxu on 16/6/5.
 */
public class AppOperationAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private String[] operationInfos;

    public AppOperationAdapter(Context mContext, String[] operationInfos) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.operationInfos = operationInfos;
    }

    @Override
    public int getCount() {
        return operationInfos.length;
    }

    @Override
    public Object getItem(int i) {
        return operationInfos[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = mInflater.inflate(R.layout.layout_text_items, null);
            holder = new ViewHolder();
            holder.textView = (TextView) view.findViewById(R.id.tv_operation);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(operationInfos[position]);
        return view;
    }

    final class ViewHolder {
        TextView textView;
    }
}
