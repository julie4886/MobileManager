package www.julie.com.mobilemanager.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import www.julie.com.mobilemanager.bean.ScanInfo;
import www.julie.com.mobilemanager.interator.AntivirusInterator;
import www.julie.com.mobilemanager.interator.Impl.AntivirusInteratorImpl;
import www.julie.com.mobilemanager.ui.activity.AntivirusActivity;
import www.julie.com.mobilemanager.utils.MD5Utils;
import www.julie.com.mobilemanager.view.AntivirusView;

/**
 * Created by liuxu on 16/6/28.
 */
public class AntivirusPresenterImpl implements AntivirusPresenter {
    private Context context;
    private AntivirusInterator antivirusInterator;
    private AntivirusView antivirusView;
    private List<PackageInfo> packageInfos;
    private final static int START = 0x01;
    private final static int RUNNING = 0X02;
    private final static int END = 0X03;
    private int process;
    private int count;
    private Message message;
    private List<ScanInfo> scanInfos;
    private MyHandler myHandler;

    public AntivirusPresenterImpl(Context context, AntivirusView antivirusView) {
        if (null == antivirusView) {
            throw new IllegalArgumentException("Constructor's parameters must not be Null");
        }
        this.context = context;
        this.antivirusView = antivirusView;
        antivirusInterator = new AntivirusInteratorImpl();
        myHandler = new MyHandler(antivirusView);
    }


    static class MyHandler extends Handler {
        WeakReference<AntivirusView> viewWeakReference;

        MyHandler(AntivirusView view) {
            viewWeakReference = new WeakReference<AntivirusView>(view);
        }

        @Override
        public void handleMessage(Message msg) {
            final AntivirusView view = viewWeakReference.get();
            if (view != null) {
                switch (msg.what) {
                    case START:
                        view.setProText("引擎准备中...");
                        break;
                    case RUNNING:
                        TextView textView = new TextView((AntivirusActivity) view);
                        ScanInfo info = (ScanInfo) msg.obj;
                        if (info.isDesc()) {
                            textView.setTextColor(Color.RED);
                            textView.setText(info.getAppName() + "有病毒");
                        } else {
                            textView.setTextColor(Color.GRAY);
                            textView.setText(info.getAppName() + "扫描安全");
                        }
                        view.setProText("病毒扫描中...");
                        view.setProgressValue(msg.arg2);
                        view.addChileView(textView);
                        break;
                    case END:
                        view.setProText("扫描完成");
                        view.StopAnimateImage();
                        view.showSelectDialog(msg.arg1);
                        break;
                }
            }
        }
    }

    @Override
    public void initialized() {
        packageInfos = antivirusInterator.getPackageInfo(context);
        antivirusView.initView(antivirusInterator.setMaxProValue());
        Animation animation = antivirusInterator.getImageAnimation();
        antivirusView.animateBackgroundImage(animation);


    }

    @Override
    public void clearTrojia() {
        for (ScanInfo info : scanInfos) {
            String packageName = info.getPackageName();
            Intent uninstall_localIntent = new Intent("android.intent.action.DELETE",
                    Uri.parse("package:" + packageName));
            context.startActivity(uninstall_localIntent);
        }
    }

    @Override
    public void initData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                process = 0;
                myHandler.sendEmptyMessage(START);
                for (PackageInfo info : packageInfos) {
                    ScanInfo scanInfo = new ScanInfo();
                    scanInfo.setAppName(info.applicationInfo.loadLabel(context.getPackageManager()).toString());
                    scanInfo.setPackageName(info.applicationInfo.packageName);
                    // 首先需要获取到每个应用程序的目录
                    String sourceDir = info.applicationInfo.sourceDir;
                    // 获取到文件的md5
                    String md5 = MD5Utils.getFileMd5(sourceDir);
                    // 判断当前的文件是否是病毒数据库里面
                    String desc = null;
//                            = AntivirusDao.checkFileVirus(md5);
                    if (desc == null) {
                        scanInfo.setDesc(false);
                    } else {
                        scanInfo.setDesc(true);
                        count++;
                        scanInfos.add(scanInfo);
                    }
                    process++;
                    Log.i("julie", "scanner" + scanInfo.isDesc());
                    message = Message.obtain();
                    message.what = RUNNING;
                    message.obj = scanInfo;
                    message.arg1 = count;
                    message.arg2 = process;
                    myHandler.sendMessage(message);
                }
                message = Message.obtain();
                message.what = END;
                myHandler.sendMessage(message);
            }

        }.start();
    }
}
