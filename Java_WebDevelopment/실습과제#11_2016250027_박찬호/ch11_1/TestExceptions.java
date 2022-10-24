package ch11_1;

class ScaryException extends Exception { }

public class TestExceptions {
    public static void main(String[] args){
        System.out.println("2016250027 박찬호");
        String test ="yes";
        try{
            System.out.println("Start try");
            doRisky(test);
            System.out.println("End try");
        } catch (ScaryException se){
            System.out.println("ScaryException");
        } finally {
            System.out.println("finally");
        }
        System.out.println("end of main");
    }

    static void doRisky(String test) throws ScaryException{
        System.out.println("Start risky");
        if("yes".equals(test)){
            throw new ScaryException();
        }
        System.out.println("end risky");
        return;
    }
}
