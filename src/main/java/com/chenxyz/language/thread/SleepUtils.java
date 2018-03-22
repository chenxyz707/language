package com.chenxyz.language.thread;

import java.util.concurrent.TimeUnit;

/**
 * Description
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-20
 */
public class SleepUtils {

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
