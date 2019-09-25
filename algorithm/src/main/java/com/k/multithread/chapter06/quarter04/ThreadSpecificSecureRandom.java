package com.k.multithread.chapter06.quarter04;

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
                
            }
        }
    }
}
