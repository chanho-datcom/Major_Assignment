package ch07_2;

public class Boat {
    private int length;
    public int getLength() {
        return length;
    }

    public void setLength(int len) {
        length = len;
    }

    public void move() {
        System.out.print("drift! ");
    }
}
