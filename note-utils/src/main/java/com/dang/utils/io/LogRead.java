package com.dang.utils.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dang.utils.DateUtils;

/**
 * @Date Create in 2017/12/26
 */
public class LogRead {
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    private File logFile;

    public LogRead(String path) {
        logFile = new File(path);
    }

    public List<LogLine> read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(logFile));
        List<LogLine> logLines = new ArrayList<LogLine>();
        String line = "";
        while ((line = reader.readLine()) != null) {
            try {
                logLines.add(parser(line));
            } catch (Exception e) {

            }
        }
        return logLines;
    }

    public static LogLine parser(String line) throws ParseException {
        String[] splitArray = line.trim().split(" ");
        LogLine logLine = new LogLine();
        logLine.date = DateUtils.toDate(splitArray[0] + " " + splitArray[1]);
        logLine.level = splitArray[3];
        logLine.lineNumber = Integer.valueOf(splitArray[4]);
        logLine.name = splitArray[6];
        logLine.className = splitArray[7];
        logLine.message = line.substring(line.indexOf(":", line.lastIndexOf(logLine.getClassName())) + 1).trim();
        return logLine;
    }

    public static class LogLine {
        //        private String line;
        private Date date;
        private String level;
        private int lineNumber;
        private String name;
        private String className;
        private String message;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(int lineNumber) {
            this.lineNumber = lineNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        LogRead logRead = new LogRead("D:\\data\\logs\\peixun_live_chat.log");
        List<LogLine> loglist = logRead.read();
        System.out.println(loglist.size());
        loglist.stream().filter(logLine -> logLine.getMessage().contains("onMessage")).forEach(l -> System.out
                .println(l
                        .getMessage()));
        parser("2017-12-19 16:07:56.436  INFO 15 --- [WebSocketContainer@1260133416-249] c.b.live.chat.service"
                + ".caption.Client     : close client: org.eclipse.jetty.websocket.jsr356.JsrBasicRemote@52d4fbfd");
    }
}
