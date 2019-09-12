package com.k.multithread.chapter04;

import com.k.multithread.util.Debug;
import com.k.multithread.util.Tools;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicLong;

public class Storage implements Closeable, AutoCloseable {
    private final RandomAccessFile storeFile;
    private final FileChannel storeChannel;
    protected final AtomicLong totalWrites = new AtomicLong(0);

    public Storage(long fileSize, String fileShortName) throws IOException {
        String fullFileName = System.getProperty("java.io.tmpdir") + "/" + fileShortName;
        String localFileName;
        localFileName = createStoreFile(fileSize, fullFileName);
        storeFile = new RandomAccessFile(localFileName, "rw");
        storeChannel = storeFile.getChannel();
    }
    /**
     * 将data中的指定数据写入文件
     *
     * @param offset 写入数据在整个文件中的起始偏移位置
     *
     * @param byteBuf bteBuf必须在方法调用执行 byteBuf.flip()
     *
     * @throws java.io.IOException
     *
     * @return 写入文件多额数据长度
     */
    public int store(long offset, ByteBuffer byteBuf) throws IOException {
        int length;
        storeChannel.write(byteBuf, offset);
        length = byteBuf.limit();
        totalWrites.addAndGet(length);
        return length;
    }
    public long getTotalWrites() {
        return totalWrites.get();
    }
    private String createStoreFile(final long fileSize, String fullFileName) throws IOException {
        File file = new File(fullFileName);
        Debug.info("create local file:%s", fullFileName);
        RandomAccessFile raf;
        raf = new RandomAccessFile(file, "rw");
        try {
            raf.setLength(fileSize);
        } finally {
            Tools.silentClose(raf);
        }
        return fullFileName;
    }
    @Override
    public synchronized void close() throws IOException {
        if (storeChannel.isOpen()) {
            Tools.silentClose(storeChannel, storeFile);
        }
    }
}
