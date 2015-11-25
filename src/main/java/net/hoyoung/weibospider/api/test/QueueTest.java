package net.hoyoung.weibospider.api.test;

import net.hoyoung.weibospider.api.vo.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2015/11/25.
 */
public class QueueTest {
    public static void main(String[] args) {
        BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
        System.out.println(queue.poll());
    }
}
