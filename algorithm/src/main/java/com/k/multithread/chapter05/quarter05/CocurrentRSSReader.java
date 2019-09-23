package com.k.multithread.chapter05.quarter05;

import javax.lang.model.element.Element;
import java.io.InputStream;

/**
 * 边下载边解析的RSS阅读器
 */
public class CocurrentRSSReader {
    public static void main(String[] args) {
        final int argc = args.length;
        String url = argc > 0 ? args[0] : "http://lorem-rss.herokuapp.com/feed";
        //从网络加载RSS数据
        InputStream in = loadRSS(url);
        //从输入流中解析XML数据
        Document document= parseXML(in);

        //读取XML中的数据
        Element eleRSS = (Element) document.getFirstChild();
        Element eleChannel = (Element) eleRSS.getElementsByTagName("channel").item(0);
    }
}
