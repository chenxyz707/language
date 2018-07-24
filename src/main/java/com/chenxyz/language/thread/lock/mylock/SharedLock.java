package com.chenxyz.language.thread.lock.mylock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 共享锁
 * 可以被两个线程共享
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-24
 */
public class SharedLock implements Lock {

    private final Sync sync = new Sync(2);

    private static class Sync extends AbstractQueuedSynchronizer {
        protected Sync(int num) {
            if (num <= 0) {
                throw new IllegalArgumentException("num must lager than 0");
            }
            setState(num);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            for(;;) {
                int current = getState();
                int update = current - arg;
                if (update < 0 || compareAndSetState(current, update)) {
                    return update;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for(;;) {
                int current = getState();
                int update  = current + arg;
                if (compareAndSetState(current, update)) {
                    return true;
                }
            }
        }

        protected Condition newCondition() {
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
