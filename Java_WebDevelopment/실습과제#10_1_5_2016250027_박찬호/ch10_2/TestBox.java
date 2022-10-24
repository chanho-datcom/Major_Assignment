package ch10_2;

public class TestBox {
    Integer i;
    int j;
    Integer iWrap = new Integer(j);

    public static void main (String[] args){
        TestBox t = new TestBox();
        t.go();
    }

    public void go(){
        iWrap=i;
        System.out.println(iWrap);
        System.out.println(i+"\n2016250027 박찬호");
    }
}
