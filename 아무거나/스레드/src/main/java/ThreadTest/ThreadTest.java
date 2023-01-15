package ThreadTest;

import java.util.ArrayList;

public class ThreadTest extends Thread{
    //Thread 통신 테스트 및 양방향 소켓통신 구현
    //참고 소스코드 : https://kadosholy.tistory.com/126
    int num;

    public ThreadTest(int num){
        this.num = num;
    }

    @Override
    public void run(){
        System.out.println(num + "thread started" );
        try{
            Thread.sleep(1000);
        }
        catch(Exception e){
        }
        System.out.println(num + "thread end");

    }

    public static void main(String[] args){

        ArrayList<ThreadTest> threads = new ArrayList<>();

        for(int i = 0 ; i < 10 ; i++) {
            ThreadTest threadtest = new ThreadTest(i);
            threadtest.start();
            threads.add(threadtest);
        }

        for(int i = 0 ; i<threads.size(); i++){
            ThreadTest threadtest = threads.get(i);
            try{
                threadtest.join();
            }catch(Exception e){
            }

        }
        System.out.println("main end");
    }

}
