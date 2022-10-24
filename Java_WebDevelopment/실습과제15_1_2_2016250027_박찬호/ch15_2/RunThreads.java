package ch15_2;

public class RunThreads implements Runnable {
    public static  void main(String[] args){
        RunThreads runner = new RunThreads();
        Thread alhpa = new Thread(runner);
        Thread beta = new Thread(runner);
        alhpa.setName("Alpha thread");
        beta.setName("Beta thread");
        alhpa.start();
        beta.start();
    }

    public void run(){
        for(int i = 0; i< 25;i++){
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "is running");
            if(i==24){
                System.out.println("2016250027 박찬호");
            }
        }
    }
}
