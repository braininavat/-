package ThreadTest;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

public class Client extends Thread{
    private Socket sc;
    private SendThread sendThread;
    private BufferedReader in;
    public static void main(String[] args){
        Client client = new Client();
        client.start();
    }
    public Client(){

    }
    public void start(){
        try {
            sc = new Socket("localhost", 8000);
            System.out.println("서버와 연결됨");
            String name = "user" + (int)(Math.random()*10);
            sendThread = new SendThread(sc, name);
            sendThread.start();

            in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            while(in != null){
                String inputMsg = in.readLine();
                if((name + "님이 퇴장하셨습니다").equals(inputMsg)) break;
                System.out.println("From:" + inputMsg);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("서버 연결 에러");
        }
        finally{
            try{
                sc.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        System.out.println("서버 접속 종료");
    }

    public class SendThread extends Thread {
        private Socket sc;
        private String name;
        private Scanner scn;
        private PrintStream out;
        public SendThread(Socket sc, String name){
            this.sc = sc;
            this.name = name;
        }
        public void init() {
            try {
                scn = new Scanner(System.in);
                out = new PrintStream(sc.getOutputStream());
                out.println(name);
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            init();
            while (true) {
                String outputMsg = scn.nextLine();
                out.println(outputMsg);
                out.flush();
                if("quit".equals(outputMsg)) break;
            }
        }
    }

}
