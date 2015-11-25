package net.hoyoung.weibospider.api.common;

import net.hoyoung.weibospider.api.vo.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2015/11/25.
 */
@Component
public class MessageQueue {
    private BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
    public List<Message> pollAll(int size){
        List<Message> list = new ArrayList<Message>();
        for (int i = 0;i < size; i++){
            Message message = queue.poll();
            if(message==null) break;
            list.add(message);
        }
        return list;
    }
    public void put(Message message){
        queue.add(message);
    }
}
