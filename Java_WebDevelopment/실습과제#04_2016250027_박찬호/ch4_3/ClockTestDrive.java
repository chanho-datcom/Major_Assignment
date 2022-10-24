package ch4_3;

public class ClockTestDrive {
    public static void main(String[] args) {

        Clock clock = new Clock();

        clock.setTime("1245");
        String time = clock.getTime();
        System.out.print("Time: " + time + "\n2016250027 박찬호");
    }
}

class Clock {

    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}