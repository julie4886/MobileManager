package www.julie.com.mobilemanager.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.bean.BlackNumberInfo;
import www.julie.com.mobilemanager.db.BlackPhoneDao;

/**
 * Created by liuxu on 16/7/3.
 */
public class BlackAdapter extends BaseAdapter {
    private Context context;
    private List<BlackNumberInfo> blackNumberInfoList;
    private LayoutInflater inflater;
    private BlackPhoneDao dao;

    public BlackAdapter(Context context, List<BlackNumberInfo> blackNumberInfoList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.blackNumberInfoList = blackNumberInfoList;
        dao = new BlackPhoneDao(context);
    }

    @Override
    public int getCount() {
        return blackNumberInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return blackNumberInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        view = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_listview_black, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final BlackNumberInfo info = blackNumberInfoList.get(i);
        viewHolder.tvNum.setText(info.getNumber());
        String mode = info.getMode();
        switch (mode) {
            case "1":
                viewHolder.tvMode.setText("电话" + "短信拦截");
                break;
            case "2":
                viewHolder.tvMode.setText("电话拦截");
                break;
            case "3":
                viewHolder.tvMode.setText("短信拦截");
                break;
            default:
                break;
        }

        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = info.getNumber();
                boolean result = dao.delete(number);
                if (result) {
                    Toast.makeText(context, "删除成功", Toast.LENGTH_LONG).show();
                    blackNumberInfoList.remove(info);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "删除失败", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_mode)
        TextView tvMode;
        @BindView(R.id.img_delete)
        ImageView imgDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
