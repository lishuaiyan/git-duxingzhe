package com.k.multithread.chapter01;

import com.k.multithread.util.Tools;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

@Slf4j
public class FileDownloaderAPP {
    public static void main(String[] args) {
        Thread downloaderThread = null;
        for (String url : args) {
            //创建文件下载器线程
            downloaderThread = new Thread(new FileDownloader(url));
            //启动文件下载器线程
            downloaderThread.start();
        }
    }
//文件下载器
    static class FileDownloader implements Runnable {
        private final String fileURL;
        public FileDownloader(String fileURL) {
            this.fileURL = fileURL;
        }
        @Override
        public void run() {
            log.debug("Downloading from " + fileURL);
            String fileBaseName = fileURL.substring(fileURL.lastIndexOf('/') + 1);
            URL url = null;
            try {
                url = new URL(fileURL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            String localFileName = System.getProperty("java.io.tmpdir")
                    + "viscent-"
                    + fileBaseName;
            log.debug("Saving to: " + localFileName);
            try {
                downloadFile(url, new FileOutputStream(localFileName), 1024);
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.debug("Done downloading from " + fileURL);
        }
        //从指定的URL下载文件，并将其保存到指定的输出流中
        private void downloadFile(URL url, OutputStream outputStream, int bufsize) throws IOException {
            //建立HTTP连接
            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            ReadableByteChannel inChannel = null;
            WritableByteChannel outChannel = null;
            try {
                //获取HTTP响应码
                int responseCode = httpURLConnection.getResponseCode();
                //HTTP响应非正常：响应码不为2开头
                if (2 != responseCode / 100) {
                    throw new IOException("Error: HTTP " + responseCode);
                }
                if (0 == httpURLConnection.getContentLength()) {
                    log.debug("NOthing to be downloaded {}" + fileURL);
                }
                inChannel = Channels.newChannel(new BufferedInputStream(httpURLConnection.getInputStream()));
                outChannel = Channels.newChannel(new BufferedOutputStream(outputStream));
                ByteBuffer buf = ByteBuffer.allocate(bufsize);
                while (-1 != inChannel.read(buf)) {
                    buf.flip();
                    outChannel.write(buf);
                    buf.clear();
                }
            } finally {
                //关闭指定的Channel以及HttpURLConnection
                Tools.silentClose(inChannel, outChannel);
                httpURLConnection.disconnect();
            }
        }
    }

}
