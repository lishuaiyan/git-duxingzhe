package com.k.multithread.chapter06.quarter04;

import com.sun.org.apache.xml.internal.security.algorithms.implementations.SignatureECDSA;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 使用ThreadLocal避免锁的争用
 */
public enum ThreadSpecificSecureRandom {
    INSTANCE;
    final static ThreadLocal<SecureRandom> SECURE_RANDOM = new ThreadLocal<SecureRandom>(){
        @Override
        protected SecureRandom initialValue() {
            SecureRandom srnd;
            try {
                srnd = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException e) {
                srnd = new SecureRandom();
                e.printStackTrace();
            }
            //通过以下调用来初始化种子
            srnd.nextBytes(new byte[20]);
            return srnd;
        }
    };
    //生成随机数
    public int nextInt(int upperBound) {
        SecureRandom secureRnd = SECURE_RANDOM.get();
        return secureRnd.nextInt(upperBound);
    }
    public void setSeed(long seed) {
        SecureRandom secureRnd = SECURE_RANDOM.get();
        secureRnd.setSeed(seed);
    }
}
