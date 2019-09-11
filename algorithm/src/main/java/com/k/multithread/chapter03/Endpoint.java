package com.k.multithread.chapter03;

/**
 * 表示下游部件的节点
 */
public class Endpoint {
    public final String host;
    public final int port;
    public final int weight;
    private volatile boolean online = true;
    public Endpoint(String host, int port, int weight) {
        this.host = host;
        this.port = port;
        this.weight = weight;
    }
    public boolean isOnline() {
        return this.online;
    }
    public void setOnline(boolean online) {
        this.online = online;
    }
@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((host == null) ? 0 : host.hashCode());
        result = prime * result + port;
        result = prime * result + weight;
        return result;
}
@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Endpoint other = (Endpoint) obj;
        if (this.host == null) {
            if (other.host != null) {
                return false;
            }
        } else if (!this.host.equals(other.host)) {
            return false;
        }
        if (this.port != other.port) {
            return false;
        }
    return this.weight == other.weight;
}
@Override
    public String toString() {
    return "Endpoint [host=" + host + ", port=" + port + ", weight=" + weight
            + ", online=" + online + "]\n";
}
}


