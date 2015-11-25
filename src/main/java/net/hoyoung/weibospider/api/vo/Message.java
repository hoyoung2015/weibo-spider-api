package net.hoyoung.weibospider.api.vo;

/**
 * Created by Administrator on 2015/11/25.
 */
public class Message {
    private String type;
    private String code;
    private String info;

    public Message(String type, String code, String info) {
        this.type = type;
        this.code = code;
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
