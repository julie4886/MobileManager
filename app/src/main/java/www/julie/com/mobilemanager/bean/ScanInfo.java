package www.julie.com.mobilemanager.bean;

/**
 * Created by liuxu on 15/6/28.
 */
public class ScanInfo {
    boolean desc;
    String packageName;
    String appName;

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
