package com.dang.note.auto;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.dang.utils.io.FileUtil;

public class MethodContextUtil {

    public static MethodContext find(String path, Method method, Object args) {
        File file = new File(path);
        if (file == null) {
            return null;
        }
        try {
            List<String> list = FileUtil.readAsList(file);
            for (String line : list) {
                MethodContext context = JSON.parseObject(line, MethodContext.class);
                if (context.getMethod().equals(method.getName()) &&
                        context.getClassName().equals(method.getDeclaringClass().getName())) {
                    return context;
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    public static void save(String path, MethodContext context) throws IOException {
        File file = new File(path);
        if (file == null) {
            FileUtil.createFile(file);
        }
        FileUtil.write(file, true, JSON.toJSONString(context));
    }
}
