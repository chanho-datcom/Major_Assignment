public class DrumKit {
    boolean topHat  = true;
    boolean snare = true ;
    void playsnare(){
        System.out.println("band bang ba-bang");
    }
    void playTopHat(){
        System.out.println("ding ding da-ding\n2016250027 박찬호");
    }
}

class DrumKitTestDrive{
    public static void main(String [] args){
        DrumKit d = new DrumKit();
        d.playsnare();
        d.snare = false;
        if(d.snare == true){
            d.playsnare();

        }
        d.playTopHat();
    }
}

