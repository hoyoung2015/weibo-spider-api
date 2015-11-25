package net.hoyoung.weibospider.api.vo;

/**
 * Created by Administrator on 2015/11/24.
 */
public class BaseStatus {
    private String code;
    protected boolean success;
    protected String info;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BaseStatus(boolean success, String info) {
        this.success = success;
        this.info = info;
    }

    public BaseStatus(String code, boolean success, String info) {
        this.code = code;
        this.success = success;
        this.info = info;
    }

    public BaseStatus() {
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
