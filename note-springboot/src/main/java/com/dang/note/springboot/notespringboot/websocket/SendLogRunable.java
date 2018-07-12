package com.dang.note.springboot.notespringboot.websocket;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import javax.websocket.Session;

import com.dang.utils.io.LogRead;

/**
 * @Date Create in 2017/12/28
 */
public class SendLogRunable implements Runnable {

    private DelayQueue<Message> delayQueue = new DelayQueue<>();

    @Override
    public void run() {
        try {
            LogRead logRead = new LogRead("D:\\data\\peixun_live_chat.log");
            List<LogRead.LogLine> logLineList = logRead.read();
            final int[] id = {0};
            logLineList.stream().filter((l -> l.getMessage().equals("omMessage"))).forEach(l ->
                    {
                        l.getDate();
                        String message = l.getMessage().substring(l.getMessage().indexOf("{"));
                        delayQueue.add(new Message(id[0]++, message));
                    }
            );
            Message message = null;
            while ((message = delayQueue.take()) != null) {
                for (Session session : CaptionWebSocket.getSeesionQueue()) {
                    session.getAsyncRemote().sendText(message.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
    }

    class Message implements Delayed {
        private String message;
        private int id;
        private long excuteTime;    // 发送时间

        public Message(int id, String message) {
            this.id = id;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void delayed(long delayTime) {
            this.excuteTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.excuteTime - System.nanoTime(), TimeUnit.NANOSECONDS);

        }

        @Override
        public int compareTo(Delayed delayed) {
            Message msg = (Message) delayed;
            return Integer.valueOf(this.id) > Integer.valueOf(msg.id) ? 1 :
                    (Integer.valueOf(this.id) < Integer.valueOf(msg.id) ? -1 : 0);
        }
    }
}
