package net.hoyoung.weibospider.api;

/**
 * Created by Administrator on 2015/11/24.
 */
public class BaseStatus {
    protected boolean success;
    protected String info;

    public BaseStatus(boolean success, String info) {
        this.success = success;
        this.info = info;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
