//package com.k.multithread.chapter06.quarter04;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
///**
// * 使用ThreadLocal实现线程安全示例代码
// */
//public class ServletWithThreadLocal extends HttpServlet {
//    final static ThreadLocal<SimpleDateFormat> SDF = new ThreadLocal<SimpleDateFormat>() {
//        @Override
//                protected SimpleDateFormat initialValue() {
//            return new SimpleDateFormat("yyyy-MM-dd");
//        }
//    };
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        final SimpleDateFormat sdf = SDF.get();
//        String strExpiryDate = req.getParameter("expiryDate");
//        try (PrintWriter pwr = resp.getWriter()) {
//            sdf.parse(strExpiryDate);
//            //省略其他代码
//            pwr.printf("[%s]expirtyDate:%s", Thread.currentThread().getName(),strExpiryDate);
//        } catch (ParseException e) {
//            throw new ServletException(e);
//        }// try 结束
//    }
//}
