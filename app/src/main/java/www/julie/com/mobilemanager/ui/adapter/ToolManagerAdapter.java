package www.julie.com.mobilemanager.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.julie.com.mobilemanager.R;

/**
 * Created by liuxu on 16/6/7.
 */
public class ToolManagerAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private String[] data;
    private int[] iconToolId;

    public ToolManagerAdapter(Context mContext, String[] data, int[] iconToolId) {
        this.iconToolId = iconToolId;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.length;
    }

    @Override
    public Object getItem(int i) {
        return data[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_appmanager_items, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
          holder = (ViewHolder) convertView.getTag();
        }
        holder.iconTool.setImageResource(iconToolId[i]);
        holder.tv_tool.setText(data[i]);
        return convertView;


    }

    static class ViewHolder {
        @BindView(R.id.icon_app)
        ImageView iconTool;
        @BindView(R.id.tv_appname)
        TextView tv_tool;
        @BindView(R.id.icon_pop)
        ImageView iconPop;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
