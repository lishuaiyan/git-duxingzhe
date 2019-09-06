package com.k.multithread.chapter01;

@Slf4j
public class FileDownloaderApp {

    static class FileDownloader implements Runnable {
        private final String fileURL;
        public FileDownloader(String fileURL) {
            this.fileURL = fileURL;
        }
        @Override
        public void run() {
            log.info("Downloading from" + fileURL);
            String fileBaseName = fileURL.substring(fileURL)
        }
    }
}
