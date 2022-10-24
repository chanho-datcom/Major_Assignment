package ch4_4;

public class Puzzle4TestDrive {
    public static void main(String[] args) {
        Puzzle4[] puzzleFours = new Puzzle4[6];
        int y = 1;
        int x = 0;
        int result = 0;

        while (x < 6) {
            puzzleFours[x] = new Puzzle4();
            puzzleFours[x].ivar = y;
            y = y * 10;
            x = x + 1;
        }

        x = 6;

        while (x > 0) {
            x = x - 1;
            result = result + puzzleFours[x].doStuff(x);
        }

        System.out.println("Result: " + result + "\n2016250027 박찬호");
    }
}
