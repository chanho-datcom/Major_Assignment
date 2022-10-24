package ch4_1;

class Dog {
    int size;
    String name;

    void bark() {
        if (size > 60) {
            System.out.println("Woof! Woof!");
        } else if (size > 14) {
            System.out.println("Ruff! Ruff!\n2016250027 박찬호");
        } else {
            System.out.println("Yip! Yip!");
        }
    }
}
