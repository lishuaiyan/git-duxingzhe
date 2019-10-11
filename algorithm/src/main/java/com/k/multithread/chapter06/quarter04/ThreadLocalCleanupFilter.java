//package com.k.multithread.chapter06.quarter04;
//
//import com.sun.net.httpserver.Filter;
//
//import javax.xml.ws.WebFault;
//import java.io.IOException;
//
//@WebFilter("memoryLeak")
//public class ThreadLocalCleanupFilter implements Filter {
//    @Override
//    public void doFilter(ServletTRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        chain.doFilter(request, response);
//        ThreadLocalMemoryLeak.counterHolder.remove();
//    }
//    //省略其他代码
//}
