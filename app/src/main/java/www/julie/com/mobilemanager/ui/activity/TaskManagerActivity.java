package www.julie.com.mobilemanager.ui.activity;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import www.julie.com.mobilemanager.R;
import www.julie.com.mobilemanager.bean.TaskInfo;
import www.julie.com.mobilemanager.engine.TaskInfoParser;
import www.julie.com.mobilemanager.ui.adapter.SimpleItemTouchHelperCallback;
import www.julie.com.mobilemanager.ui.adapter.TaskAdapter;
import www.julie.com.mobilemanager.view.DividerItemDecoration;

/**
 * Created by liuxu on 16/6/6.
 */
public class TaskManagerActivity extends BaseActivity {

    @BindView(R.id.tv_clear)
    TextView clearAll;

    private List<TaskInfo> taskInfos;
    @BindView(R.id.icon_top_back)
    ImageView iconTopBack;
    @BindView(R.id.rv_taskInfos)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private ItemTouchHelper mItemTouchHelper;
    private ActivityManager activityManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_taskmanager);
        unbinder = ButterKnife.bind(this);
        init();
        initDate();
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

    }

    @OnClick({R.id.icon_top_back, R.id.tv_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_top_back:
                showPreviousPage();
                break;
            case R.id.tv_clear:    //一键清理事件处理
                killAllProcess();
                handler.sendEmptyMessage(1);  //发送消息，更新界面
                break;

        }
    }


    @Override
    void showPreviousPage() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    void showNextPage() {

    }

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            TaskAdapter adapter = new TaskAdapter(TaskManagerActivity.this, taskInfos);
            recyclerView.setAdapter(adapter);
            if (msg.what == 0) {
                ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
                mItemTouchHelper = new ItemTouchHelper(callback);
                mItemTouchHelper.attachToRecyclerView(recyclerView);
            }
            if (msg.what == 1) {
                adapter.notifyDataSetChanged();

            }
        }
    };

    /**
     * 初始化进程数据
     */
    private void initDate() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                taskInfos = TaskInfoParser.getTaskInfos(TaskManagerActivity.this);
                Log.i("julie","size + " + taskInfos.size());
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    /**
     * 结束所用进程
     */
    private void killAllProcess() {
        List<TaskInfo> killList = new ArrayList<TaskInfo>();
        int count = 0;
        int killMem = 0;
        for (TaskInfo taskInfo : taskInfos) {
            if (taskInfo.getPackageName().equals(getPackageName())) {
                continue;
            } else {
                killList.add(taskInfo);
            }
        }
        for (TaskInfo taskInfo : killList) {
            killMem += taskInfo.getMemorySize();
            count++;
            taskInfos.remove(taskInfo);
            activityManager.killBackgroundProcesses(taskInfo.getPackageName());
        }
        Toast.makeText(this, "共杀死" + count + "内存" + "为您清理" +
                Formatter.formatFileSize(TaskManagerActivity.this, killMem) + "内存", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }


}
