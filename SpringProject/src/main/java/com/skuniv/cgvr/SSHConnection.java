package com.skuniv.cgvr;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.util.function.Consumer;

/*
    SSH Tunneling Code
    Application 시작 시 생성자를 통해 openSSH func() 수행
    연결 후 SSH는 꼭 종료하여야 함
 */
public class SSHConnection {

    private static JSch jsch = new JSch();
    private String HOST = "210.110.39.106";     // SSH_HOST
    private int PORT = 22;                      // SSH_PORT
    private String SSH_USER = "researcher3";    // SSH_ID
    private String SSH_PW = "15161516";         // SSH password
    private int iPort = 9999;                   // 가상으로 포워딩할 포트
    private int rPort = 3306;                   // 실제 사용 DB PORT
    private Session session;

    public SSHConnection openSSH(Consumer<Boolean> arg) {
        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session = jsch.getSession(SSH_USER, HOST, PORT);
            session.setPassword(SSH_PW);
            session.connect();
            session.setPortForwardingL(iPort, "127.0.0.1", rPort);
            arg.accept(true);
        } catch (Exception e) {
            e.printStackTrace();
            arg.accept(false);
        }
        return this;
    }
    public void closeSSH() throws Exception {
        session.disconnect();
    }
}