package ch07_2;

public class TestBoats {
    public static void main(String[] args) {

        Boat boat = new Boat();
        Sailboat sailboat = new Sailboat();
        Rowboat rowboat = new Rowboat();

        sailboat.setLength(32);
        boat.move();
        rowboat.move();
        sailboat.move();
        System.out.println("\n2016250027 박찬호");
    }
}