package com.myself.tank.filterChain;

import java.util.ArrayList;
import java.util.List;

public interface Filter {

     boolean doFilter(Request request,Response response,FilterChain doFilter);
}

class Test{
    public static void main(String[] args){
        Request request = new Request("request");
        Response response = new Response("response");

        FilterChain chain = new FilterChain();
        chain.add(new HTMLFilter());
        chain.add(new SensetiveFilter());
        chain.chain(request,response,chain);
        System.out.println(request.getStr());
        System.out.println(response.getStr());
    }
}

class HTMLFilter implements Filter{

    @Override
    public boolean doFilter(Request request, Response response,FilterChain chain) {
        request.setStr(request.getStr() + "------HTMLFilter");
        chain.chain(request,response,chain);
        response.setStr(response.getStr() + "------HTMLFilter");
        return true;
    }
}

class SensetiveFilter implements Filter{

    @Override
    public boolean doFilter(Request request, Response response,FilterChain chain) {
        request.setStr(request.getStr() + "------SensetiveFilter");
        chain.chain(request,response,chain);
        response.setStr(response.getStr() + "------SensetiveFilter");
        return true;
    }
}

class FilterChain{

    List<Filter> filters = new ArrayList<>();
    int index = 0;

    public void add(Filter filter){
        filters.add(filter);
    }

    public void chain(Request request,Response response,FilterChain chain){
        if (index == filters.size()){
            return;
        }
        Filter f = filters.get(index);
        index ++;
         f.doFilter(request,response,this);
    }
}

