package com.chenxyz.language.thread.base.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 重写interrupt方法时需要调用super.interrupt()
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-16
 */
public class OverrideInterrupt extends Thread {
    private final Socket socket;
    private final InputStream in;

    public OverrideInterrupt(Socket socket, InputStream in) {
        this.socket = socket;
        this.in = in;
    }

    private void t(){
    }

    @Override
    public void interrupt() {
        try {
            //关闭底层的套接字
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            //.....
        }finally {
            //同时中断线程
            super.interrupt();
        }

    }
}
