package ThreadTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Server {
    private ServerSocket serversc;
    private Socket sc;

    public void start() {
        try {
            serversc = new ServerSocket(8000);
            while (true) {
                System.out.println("클라이언트 연결 대기중");
                sc = serversc.accept();

                RcvThread rcvThread = new RcvThread(sc);
                rcvThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serversc != null) {
                try {
                    serversc.close();
                    System.out.println("서버종료");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("서버 소켓 통신 에러 발생");
                }
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public class RcvThread extends Thread {

        static private List<PrintWriter> list = Collections.synchronizedList(new ArrayList<>());
        private Socket sc;
        private BufferedReader in = null;
        private PrintWriter out = null;
        private Scanner scn;
        private String name;

        public RcvThread(Socket sc) {
            this.sc = sc;
            try {
                out = new PrintWriter(sc.getOutputStream());
                in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
                list.add(out);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(this.getClass().toString() + "초기화 에러");
            }
            System.out.println("클라이언트 연결 완료");
        }

        public void init() {
            name = "";
            try {
                name = in.readLine();
                System.out.println(name + " 과 연결됨.");
                sendAll(name + " 님이 입장하셨습니다.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            init();
                try {
                    while(in!= null) {
                        String msg = in.readLine();
                        if ("quit".equals(msg)) break;
                        sendAll(name + " : " + msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("접속 에러");
                } finally {
                    sendAll(name + "님이 퇴장하셨습니다");
                    list.remove(out);
                    try {
                        sc.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name + ": 연결 종료");
        }

        private void sendAll(String s) {
            for (PrintWriter out : list) {
                out.println(s);
                out.flush();
            }

        }
    }
}

