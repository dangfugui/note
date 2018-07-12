package com.dang.java.设计模式.old;

import java.util.ArrayList;
import java.util.List;

//过滤器
interface Filter {
    void doFilter(Request request, Response response, FilterChain filterChain);
}

//
public class 责任链 {
    public static void main(String[] args) {
        Request request = new Request();
        Response response = new Response();
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new HTMLFilter())
                .addFilter(new SesitiveFilter());
        filterChain.doFilter(request, response, filterChain);
        System.out.println(request.requestStr);
        System.out.println(response.responseStr);

    }
}

class Request {
    public String requestStr = "";
}

class Response {
    public String responseStr = "";
}

//过滤器链
class FilterChain implements Filter {
    List<Filter> filters = new ArrayList<Filter>();
    int index = 0;

    public FilterChain addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    public void doFilter(Request request, Response response, FilterChain filterChain) {
        if (index == filters.size()) {
            return;
        }
        Filter filter = filters.get(index++);
        filter.doFilter(request, response, filterChain);
    }

}

//html 过滤器
class HTMLFilter implements Filter {
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.requestStr += "HTMLFilter>>";
        filterChain.doFilter(request, response, filterChain);
        response.responseStr += "HTMLFilter>>";
    }
}

class SesitiveFilter implements Filter {
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.requestStr += "SesitiveFilter>>";
        filterChain.doFilter(request, response, filterChain);
        response.responseStr += "SesitiveFilter>>";
    }

}
