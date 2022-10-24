package ch10_3;

class StaticSuper{

    static {
        System.out.println("super static block");
    }

    StaticSuper(){
        System.out.println("super constructor");
    }
}

public class StaticTests extends StaticSuper{
    static int rand;

    static{
        rand = (int) (Math.random() * 6);
        System.out.println("static block" + rand);
    }

    StaticTests(){
        System.out.println("constructor\n2016250027 박찬호");
    }

    public static void main(String[] args){
        System.out.println("in main");
        StaticTests st = new StaticTests();
    }
}


