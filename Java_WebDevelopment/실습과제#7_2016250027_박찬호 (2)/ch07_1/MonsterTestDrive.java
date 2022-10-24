package ch07_1;

public class MonsterTestDrive {
    public static void main(String[] args) {
        Monster[] monsters = new Monster[3];
        monsters[0] = new Vampire();
        monsters[1] = new Dragon();
        monsters[2] = new Monster();
        for (int x = 0; x < 3; x++) {
            monsters[x].frighten(x);
        }
        System.out.println("2016250027 박찬호");
    }
}

class Monster {

    boolean frighten(int d) {
        System.out.println("arrrgh!");
        return true;
    }
}

class Vampire extends Monster {

    boolean frighten(int x) {
        System.out.println("a bite?");
        return false;
    }
}

class Dragon extends Monster {

    boolean frighten(int degree) {
        System.out.println("breath Fire!");
        return true;
    }
}