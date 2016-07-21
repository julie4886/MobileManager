package www.julie.com.mobilemanager.ui.adapter;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.bean.TaskInfo;
import www.julie.com.mobilemanager.ui.activity.TaskManagerActivity;

/**
 * Created by liuxu on 16/6/7.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> implements onMoveAndSwipedListener {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<TaskInfo> taskInfos;
    private ActivityManager activityManager;

    public TaskAdapter(Context mContext, List<TaskInfo> taskInfos) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.taskInfos = taskInfos;
        activityManager = (ActivityManager) this.mContext.getSystemService(Context.ACTIVITY_SERVICE);

    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(taskInfos, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {

        if (taskInfos.get(position).getPackageName().equals(mContext.getPackageName())) {
            Toast.makeText(mContext, "不能清理自己", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        } else if (position == taskInfos.size() - 1) {
            Toast.makeText(mContext, "系统引用不能清理 ", Toast.LENGTH_LONG).show();
            notifyDataSetChanged();
        } else {
            //杀死应用进程
            activityManager.killBackgroundProcesses(taskInfos.get(position).getPackageName());
            //删除mItems数据
            taskInfos.remove(position);
            Toast.makeText(mContext, "为您清理" + Formatter.formatFileSize(mContext,
                    taskInfos.get(position).getMemorySize()) + "内存", Toast.LENGTH_SHORT).show();
            //删除RecyclerView列表对应item
            notifyItemRemoved(position);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recylerview, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvAppmanagerTitle.setText(taskInfos.get(position).getAppName());
        holder.iconTopBack.setImageDrawable(taskInfos.get(position).getIcon());


    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return taskInfos == null ? 0 : taskInfos.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon_top_back)
        ImageView iconTopBack;
        @BindView(R.id.tv_appmanager_title)
        TextView tvAppmanagerTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
