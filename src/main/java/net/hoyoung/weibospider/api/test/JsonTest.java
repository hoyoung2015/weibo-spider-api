package net.hoyoung.weibospider.api.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/11/24.
 */
public class JsonTest {
    public static void main(String[] args) {
        String tmp ="{\"coordinates\":[25.02038,102.72069],\"type\":\"Point\"}";
        Map<String,Object> map = JSON.parseObject("{\"coordinates\":[25.02038,102.72069],\"type\":\"Point\"}", new TypeReference<Map<String, Object>>() {
        });
        System.out.println(map);
        tmp = (String)map.get("coordinates");

        Matcher m = Pattern.compile("(\\d+\\.\\d+)").matcher(tmp);
    }
}
