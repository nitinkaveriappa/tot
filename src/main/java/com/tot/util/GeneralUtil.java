package com.tot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

public class GeneralUtil {

    private final static Logger logger = LoggerFactory.getLogger(GeneralUtil.class);

    public static synchronized Long getUniqueID() {
        AtomicLong counter = new AtomicLong(System.currentTimeMillis());
        return counter.incrementAndGet();
    }
}
