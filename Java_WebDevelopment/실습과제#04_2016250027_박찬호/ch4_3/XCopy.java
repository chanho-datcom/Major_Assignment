package ch4_3;

public class XCopy {
    public static void main(String[] args) {
        int orig = 42;
        XCopy xCopy = new XCopy();
        int y = xCopy.go(orig);
        System.out.println(orig + " " + y + "\n2016250027 박찬호");
    }

    int go(int arg) {
        arg = arg * 2;
        return arg;
    }
}
