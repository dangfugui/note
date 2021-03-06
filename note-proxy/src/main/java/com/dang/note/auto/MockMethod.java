package com.dang.note.auto;

import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MockMethod implements MethodInterceptor {

    private Object targetObject;
    private String path;

    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        MethodContext context = MethodContextUtil.find(path, method, args);
        if (context != null) {    // 如果存在之前存储的执行结果 就重现结果
            if (context.getThrowable() != null) {
                throw context.getThrowable();
            }
            return JSON.parseObject(JSON.toJSONString(context.getResult()), context.getResultClass());
        }
        // 创建一个新的方法执行上下文
        context = new MethodContext(method, args);
        try {
            Object result = methodProxy.invoke(targetObject, args);
            context.setResult(result);
            context.setResultClass(result.getClass());
        } catch (Throwable throwable) {
            context.setThrowable(throwable);
        }
        // 保存方法执行上下文
        MethodContextUtil.save(path, context);
        return context.getResult();
    }

    //创建一个方法来完成创建代理对象
    public Object createInstance(Object targetObject) {
        this.targetObject = targetObject;   //  => 设置代理对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        // enhancer.setClassLoader(   targetObject.getClass().getClassLoader()  );
        enhancer.setCallback(this);
        return enhancer.create();  //创建代理类对象.
    }

    public void setPath(String path) {
        this.path = path;
    }
}
