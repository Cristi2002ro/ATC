public class Passenger extends Thread {
    int number;
    int planeNr;
    boolean embarking;

    @Override
    public void run() {
        if (embarking)
            System.out.println("Passenger " + number + " from plane "+planeNr+" :I'm embarking now in plane "+planeNr);
        else System.out.println("Passenger " + number + " from plane "+planeNr+" :I'm disembarking now from plane "+planeNr);

    }

    Passenger(int number, boolean embarking, int planeNr) {
        this.number = number;
        this.embarking = embarking;
        this.planeNr=planeNr;
        start();
    }

}
