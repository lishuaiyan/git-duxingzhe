package com.k.multithread.chapter04;

import com.k.multithread.util.Debug;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 下载子任务
 */
public class DownloadTask implements Runnable {
    private final long lowerBound;
    private final long upperBound;
    private final DownloadBuffer xbuf;
    private final URL requestURL;
    private final AtomicBoolean cancelFlag;

    public DownloadTask(long lowerBound, long upperBound, URL requestURL, Storage storage, AtomicBoolean cancelFlag) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.requestURL = requestURL;
        this.xbuf = new DownloadBuffer(lowerBound, upperBound, storage);
        this.cancelFlag = cancelFlag;
    }

    //对指定的URL发起HTTP分段下载请求
    private static InputStream issueRequest(URL requestURL, long lowerBound, long upperBound) {
        Thread me = Thread.currentThread();
        Debug.info(me + "->[" + lowerBound + "," + upperBound + "]");
        final HttpURLConnection conn;
        InputStream in = null;
        conn = (HttpURLConnection) requestURL.openConnection();
        String strConnTimeout = System.getProperty("x.dt.conn.timeout");
        int connTimeout = null == strConnTimeout ? 60000 : Integer.valueOf(strConnTimeout);
        conn.setConnectTimeout(connTimeout);

        String strReadTimeout = System.getProperty("x.dt.read.timeout");
        int readTimeout = null == strReadTimeout ? 60000 : Integer.valueOf(strReadTimeout);

        conn.setReadTimeout(readTimeout);

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Connection", "keep-alive");
        //Range： bytes= 1-1024
        conn.setRequestProperty("Range", "bytes=" + lowerBound + "-" + upperBound);
        conn.setDoInput(true);
        conn.connect();

        int statusCode = conn.getResponseCode();
        if (HttpURLConnection.HTTP_PARTIAL != statusCode) {
            conn.disconnect();
            throw new IOException("Server exception, status code:" + statusCode);
        }
        Debug.info(me + "-Content-Range:" + conn.getHeaderField("Content-Range") + ",connection:" +
                conn.getHeaderField("connection"));

        in = new BufferedInputStream(conn.getInputStream()) {
            @Override
            public void close() throws
        }
    }
}
