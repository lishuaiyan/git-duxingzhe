package com.k.multithread.chapter02;

public class AtomicityExample {
    private HostInfo hostInfo;
    public void updateHostInfo(String ip, int port) {
        //以下操作不是原子操作
        hostInfo.setIp(ip);//语句1
        hostInfo.setPort(port);//语句2
    }
    public void connectToHost() {
        String ip = hostInfo.getIp();
        int port = hostInfo.getPort();
        connectToHost(ip, port);
    }
    private void connectToHost(String ip, int port) {
        //...
    }
    public static class HostInfo {
        private String ip;
        private int port;
        public HostInfo() {}

        public HostInfo(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        @Override
        public String toString() {
            return "HostInfo{" +
                    "ip='" + ip + '\'' +
                    ", port=" + port +
                    '}';
        }
    }
}
